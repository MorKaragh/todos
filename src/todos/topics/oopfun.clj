(ns todos.topics.oopfun)

(declare ^:dynamic this)
(declare find-method)
(defn new-object [klass]
  (let [state (ref {})]
    (fn thiz [command & args]
      (case command
        :class klass
        :class-name (klass :name)
        :set! (let [[k v] args]
                (dosync (alter state assoc k v))
                nil)
        :get (let [[key] args]
               (@state key))
        (let [method (klass :method command)]
          (if-not method
            (throw (RuntimeException.
                    (str "No Such Method " method))))
          (binding [this thiz]
            (apply method args)))))))
(defn new-class [class-name parent methods]
  (fn klass [command & args]
    (case command
      :name (name class-name)
      :parent parent
      :new (new-object klass)
      :methods methods
      :method (let [[method-name] args]
                (find-method method-name klass)))))
(def OBJECT (new-class :OBJECT nil {}))
#_{:clj-kondo/ignore [:unresolved-symbol]}
(defn find-method [method-name klass]
  (or ((klass :methods) method-name)
      (if-not (= #'OBJECT klass)
        (find-method method-name (klass :parent)))))
(defn method-spec [expr]
  (let [name (keyword (second expr))
        body (next expr)]
    [name (conj body 'fn)]))
;; (method-spec '(method age [] (* 2 10)))
(defn method-specs [sexprs]
  (->> sexprs
       (filter #(= 'method (first %)))
       (mapcat method-spec)
       (apply hash-map)))
;; (method-specs '((method age [] (* 2 10))
;;                 (method greet [visitor] (str "Hello there, " visitor))))
(defn parent-class-spec [sexprs]
  (let [extended-spec (filter #(= 'extends (first %)) sexprs)
        extends (first extended-spec)]
    (if (empty? extends)
      'OBJECT
      (last extends))))
;; (parent-class-spec '((extends Person)
;;                      (method age [] (* 2 9))))
(defmacro defclass [class-name & specs]
  (let [parent-class (parent-class-spec specs)
        fns (or (method-specs specs) {})]
    `(def ~class-name (new-class '~class-name #'~parent-class ~fns))))





#_{:clj-kondo/ignore [:unresolved-symbol]}
(defclass Person
  (method age [] (* 2 10))
  (method about [diff]
          (str "I was born about " (+ diff (this :age)) " years ago")))

#_{:clj-kondo/ignore [:unresolved-symbol]}
(defclass Woman
  (extends Person)
  (method greet [v] (str "Hello, " v))
  (method age [] (* 3 9)))

(Person :name)

(def Lola (Woman :new))
(Lola :greet "Shelly")
(Lola :about 3)

(def someperson (Person :new))
(someperson :class-name)
(someperson :age)
(someperson :about 2)

(def some-class Person)
(some-class :name)

(def someone (new-object Person))
((someone :class) :name)
(someone :class-name)

(def anotherone (Person :new))
(anotherone :class-name)
(anotherone :set! :name "Another One")
(anotherone :get :name)