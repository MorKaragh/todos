(ns todos.topics.macro)

(def a-ref (ref 0))

(dosync
 (ref-set a-ref 1))

(defmacro sync-set [r v]
  (list 'dosync
        (list 'ref-set r v)))

(sync-set a-ref 10)
@a-ref

(defn exhibits-oddity? [x]
  (if (odd? x)
    (println "Very odd!")))

(defn unless-func [test then]
  (if (not test)
    then))

(defn exhibits-oddity-2? [x]
  (unless-func (even? x)
               (println "Very odd indeed!")))

(exhibits-oddity-2? 10)
  ;; работает не корректно, т.к. функция unless выполняет параметры прежде всего

(defmacro unless [test then]
  (list 'if (list 'not test)
        then))

(defmacro unless-comm [test then]
  `(if (not ~test)
     ~then))

(defn exhibits-oddity-correct? [x]
  (unless (even? x)
          (println "Very odd indeed!")))

(exhibits-oddity-correct? 10)

(macroexpand
 '(when-not x))

(macroexpand
 '(unless (even? x) (println "Very odd, indeed!")))

(defn exhibits-oddity-again [x]
  (unless (even? x)
          (do
            (println "Odd")
            (println "Yes, Odd"))))
(exhibits-oddity-again 11)

(defmacro unless-multi-corr [test & exprs]
  `(if (not ~test)
     (do ~@exprs)))

(defmacro unless-multi-wrong [test & then]
  `(if (not ~test)
     (do ~then)))

  ;; развернется в (if (clojure.core/not 11) (do (str 1) (str 2)))
(macroexpand
 '(unless-multi-corr 11 (str 1) (str 2)))

  ;; а тут будут лишние скобки (if (clojure.core/not 11) (do ((str 1) (str 2))))
(macroexpand
 '(unless-multi-wrong 11 (str 1) (str 2)))

(defn exhibits-oddity-multi-wrong [x]
  (unless-multi-wrong (even? x)
                      (println "Odd")
                      (println "Yezz Odd")))
(exhibits-oddity-multi-wrong 11) ;; NPE изза лишних скобок


  ;; now# надо использовать чтобы не путаться в именах
  ;; это свойственно для лиспов, проблема "variable capture"
(defmacro def-with-name [fn-name args & body]
  `(defn ~fn-name ~args
     (let [now# (System/currentTimeMillis)]
       (println "[" now# "] call to " (str (var ~fn-name)))
       ~@body)))

(declare hello-named)

#_{:clj-kondo/ignore [:unresolved-symbol]}
(def-with-name hello-named [x]
  (println "named is " x))

(hello-named 12)

(macroexpand '(def-with-name saysome [x]
                (println "some is " x)))

(macroexpand '(declare add multiply subtract divide))
(macroexpand '(defonce x 15))
(macroexpand '(and x y z))
(macroexpand '(time (* 1 2)))

(defmacro infix [expr]
  (let [[left op right] expr]
    (list op left right)))

(infix #_{:clj-kondo/ignore [:not-a-function]}
 (2 - 3))

(defmacro randomly [& exprs]
  (let [len        (count exprs)
        index      (rand-int len)
        conditions (map #(list '= index %) (range len))]
    `(cond ~@(interleave conditions exprs))))

(macroexpand-1 '(randomly (println "hello")
                          (println "my")
                          (println "world")))

(clojure.core/cond (= 0 0) (println "hello")
                   (= 0 1) (println "my")
                   (= 0 2) (println "world"))

(defmacro randomly-2 [& exprs]
  (let [c (count exprs)]
    `(case (rand-int ~c) ~@(interleave (range c) exprs))))


(macroexpand-1 '(randomly-2 (println "hello")
                            (println "my")
                            (println "world")))

(clojure.core/case (clojure.core/rand-int 3)
  0 (println "hello")
  1 (println "my")
  2 (println "world"))

(defn check-auth [usr pass]
  true)

(defn login-user-pre [request]
  (let [username (:username request)
        password (:password request)]
    (if (check-auth username password)
      (str "Hello, " username)
      (str "Login failed!"))))

(def requezt {:username "Stepan"
              :password "123"})

(login-user-pre requezt)

(defmacro defwebmethod [name args & exprs]
  `(defn ~name [{:keys ~args}]
     ~@exprs))

#_{:clj-kondo/ignore [:unresolved-symbol]}
(defwebmethod login-user [username password]
  (if (check-auth username password)
    (str "Welcome, " username ", " password " is still correct!")
    (str "Login failed!")))

#_{:clj-kondo/ignore [:unresolved-symbol]}
(login-user requezt)

(defmacro defnn [fname [& names] & body]
  (let [ks {:keys (vec names)}]
    `(defn ~fname [& {:as arg-map#}]
       (let [~ks arg-map#]
         ~@body))))

(defmacro try-catch [to-try how-catch]
  `(try
     (~to-try)
     (catch Exception e# (~how-catch (.getMessage e#)))))

(macroexpand-1 '(try-catch #(/ 1 0) println))

(try-catch #(/ 1 1) println)
(try-catch #(/ 1 0) println)

(try
  (fn* [] (/ 1 0))
  (catch java.lang.Exception e__8123__auto__ (println (.getMessage e__8123__auto__))))


(defmacro single-arg-fn [binding-form & body]
  `((fn [~(first binding-form)] ~@body) ~(second binding-form)))

(defmacro my-let [lettings & body]
  (if (empty? lettings)
    `(do ~@body)
    `(single-arg-fn ~(take 2 lettings)
                    (my-let ~(drop 2 lettings) ~@body))))

#_{:clj-kondo/ignore [:unresolved-symbol]}
(clojure.walk/macroexpand-all '(my-let [x 10
                                        y x
                                        z (+ x y)]
                                       (* x y z)))

((fn* ([x] ((fn* ([y] ((fn* ([z] (do (* x y z)))) (+ x y)))) x))) 10)


;; anaphoric macro
(defn some-computation [x]
  (if (even? x) false (inc x)))

(if (some-computation 11)
  (* 2 (some-computation 11)))

;; better way
(if-let [computation (some-computation 11)]
  (* 2 computation))

(defmacro anaphoric-if [test-form then-form]
  `(if-let [~'it ~test-form]
     ~then-form))

;; best way
#_{:clj-kondo/ignore [:unresolved-symbol]}
(anaphoric-if (some-computation 11)
              (* 2 it))

(defmacro with-it [operator test-form & exprs]
  `(let [~'it ~test-form]
     (~operator ~'it ~@exprs)))

(macroexpand '(with-it if (some-computation 12)
                (* 2 it)))

(defn surface-area-cylinder [r h]
  (-> r
      (+ h)
      (* 2 Math/PI r)))

(defmacro thread-it [& [first-expr & rest-expr]]
  (if (empty? rest-expr)
    first-expr
    `(let [~'it ~first-expr]
       (thread-it ~@rest-expr))))

#_{:clj-kondo/ignore [:unresolved-symbol]}
(thread-it (* 10 20)
           (inc it)
           (- it 8)
           (* 10 it)
           (/ it 5))