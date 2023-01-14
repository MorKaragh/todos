(ns todos.tryouts.tryouts
  (:require [clojure.string :as str] :reload)
  (:require [todos.problems :as prob] :reload)
  (:import (java.util Set Date))
  (:gen-class))
(use 'clojure.data.json)
(require '(clojure.data [json :as json-lib]))
(use 'clojure.xml)

(defn -main
  "I don't do a whole lot."
  [& args]


  (def randVar 10)
  (def aString "foo bar baz")
  (def ranDouble 10.01)
  (def aLong 15)
  (def aString "world")
  (neg? aLong)
  (nil? aLong)
  (format "Hello, %s" aString)
  (println aLong "Hello, World!")
  (format "%-8d left justified" aLong)
  (format "%08d left justified" aLong)

  (str/blank? aString)
  (str/includes? aString "foo")
  (str/replace aString "foo" "hell")
  (str/split aString #" ")
  (println aString)

  (println (cons 3 (first (list "Dog", 1 2.3 true))))
  (keys (hash-map 1 "first" 2 "second" 3 "third"))
  (vals (hash-map 1 "first" 2 "second" 3 "third"))
  (. Math PI)
  (. Math abs -3)
  (.toUpperCase "foo")
  (+ (+ 2 2) (- 5 2))
  "go"

  (println *1 *2 *3)

  (+ 2/3 4/5)
  (+ 3 44N)
  (inc 9223372036854775807)
  (name :foo/bar)

  (list 1 2 3 4 5)
  (conj (list 1 2 3 4 5) 6)
  (pop (list 1 2 3 4 5))
  (peek (list 1 2 3 4 5))
  (count (list 1 2 3 4 5))

  (def aVector [11 22 33 44])
  (get aVector 1)
  (nth aVector 0)
  (assoc aVector 0 12)
  (conj aVector 00)
  (pop [1 2])
  (peek [1 2])
  (aVector 0)

  (def aMap {:a 1
             :b 2
             :c 3})
  (hash-map :a 11 :b 22 :c 33)
  (aMap :a)
  (:b aMap)
  (:d aMap 42) ;;same but with default value

  (def person {:name       "Ivan"
               :surname    "Petrov"
               :profession {:company  "Wagner"
                            :position "Kuvaldist"
                            :contract {:from   "12.12.2012"
                                       :to     "12.12.2021"
                                       :salary 1000}}})

  (assoc-in person [:profession :contract :to] "11.11.2021")
  (get-in person [:profession :contract :to])
  (update-in person [:profession :contract :salary] + 500)

  (if true
    (do
      (println "Hello")
      (println "World")))

  (if (> 5 2)
    "yes"
    "no")
  (if-not (> 5 2)
    "yes"
    "no")

  (defn compare [x y]
    (cond
      (x > y) 1
      (x = y) 0
      (x < y) -1))

  (compare 0 -1)

  (when (> 5 2)
    (println "it")
    (println "is")
    (println "greater")
    "done")
  (when-not (< 5 2)
    (println "it")
    (println "is")
    (println "smaller")
    "done")

  (when (and (> 5 2) (> 1 0))
    (println "AND works!")
    "result")

  (when (and 0 1)
    (println "yes")
    "result")

  (< 1 2 3 4 5 6)

  (= 1/2 0.5)
  ;=> false

  (== 1/2 0.5)
  ;=> true

  (defn fact-loop [n]
    (loop [current n
           fact    1]
      (if (= current 1)
        fact
        (recur (dec current) (* fact current)))))


  (defn craz-loop [n]
    (loop [first  1
           second 10]
      (println first second)
      (if (> first second)
        (println "end")
        (recur (inc first) (dec second)))))

  (fact-loop 10)
  (craz-loop 10)

  (defn forich [coll]
    (doseq [itm coll]
      (println itm))
    "ended")

  (forich ["one" "two" "three"])

  (defn timez [n]
    (dotimes [x n]
      (println "X is " x)))

  (timez 5)

  (map + [1 2 3] [1 2 3 4 5])

  (defn clear-zeros [inpt]
    (let [non-zero? (fn [e] (not (zero? e)))]
      (filter non-zero? inpt)))

  (defn rem-all-but-zeros [inpt]
    (let [non-zero? (fn [e] (not (zero? e)))]
      (remove non-zero? inpt)))

  (clear-zeros [-1 -2 0 2 1])
  (rem-all-but-zeros [-1 -2 0 2 1])

  (range 0 5)
  (concat [1 2 3] [4 5 6] [7 8 9])

  (defn factorial [n]
    (let [nums (range (bigint 1) (+ (bigint n) 1))]
      (bigint (reduce * nums))))

  (factorial 5)


  (defn factorials [n]
    (let [nums (range 1 (+ n 1))]
      (reductions * nums)))

  (factorials 10)


  (def forr
    (for [alpha "abcdefgh"
          num   (range 1 9)]
      (println alpha num)))

  (defn prime? [x]
    (let [divisors   (range 2 (inc (int (Math/sqrt x))))
          remainders (map (fn [d] (rem x d)) divisors)]
      (not (some zero? remainders))))

  (defn primes-less-than [n]
    (for [x     (range 2 (inc n))
          :when (prime? x)]
      x))

  (prime? 5)
  (primes-less-than 55)

  (defn pairs-prime-sum [n]
    (let [z (range 2 (inc n))]
      (for [x     z
            y     z
            :when (prime? (+ x y))]
        (list x y))))

  (pairs-prime-sum 5)


  (for [x     [1 2 3]
        y     [1 2 3]
        :when (< 2 (+ x y))]
    (str x y))

  (defn get-amount [principal rate time-periods]
    (-> rate
        (/ 100)
        (+ 1)
        (Math/pow time-periods)
        (* principal)))

  ;; expands to (* (Math/pow (+ (/ rate 100) 1) time-periods) principle)
  ;; moves expression to the second position of the next

  (get-amount 100 20 2)

  (defn factoreal [n]
    (reduce * (range 1 (+ 1 n))))

  (defn factoreal->> [n]
    (->> n
         (+ 1)
         (range 1)
         (reduce *)))
  ;; expands to (reduce * (range 1 (+ 1 n)))
  ;; moves expression to last position of the next

  (meta (var factoreal->>))

  (as-> {"a" [1 2 3 4]} <>
    (<> "a")
    (conj <> 10)
    (map inc <>))

  (let [x 1
        y 2]
    (cond-> "great"
      (odd? x) (str "x is odd")
      (zero? (rem y 3)) (str "y is divisable by 3")
      (even? y) (str "y is even")))

  (let [x 1
        y 2]
    (as-> [] <> (if (odd? x) (conj <> "x is odd") <>)
          (if (zero? (rem y 3)) (conj <> "y is divisible by 3") <>)
          (if (even? y) (conj <> "y is even") <>)))

  (def mapWithMetadata (with-meta {"key" "value"}
                         {:property        "propVal"
                          :anotherproperty "anotherVal"}))

  (def anotherMapWithMetadata ^{:prop "val"} {"key" "value"})

  (meta anotherMapWithMetadata)
  (meta mapWithMetadata)
  (= mapWithMetadata anotherMapWithMetadata)

  (set! *warn-on-reflection* true)

  (defn string-length [x] (.length x))
  (time (reduce + (map string-length (repeat 10000 "12345"))))

  (defn fast-string-length [^String x] (.length x))
  (time (reduce + (map fast-string-length (repeat 10000 "12345"))))

  (meta (first (first (:arglists (meta #'fast-string-length)))))

  (set! *warn-on-reflection* false)

  (defn array-type [clazz]
    (.getName (class (make-array clazz 0))))

  (array-type BigDecimal)

  (def bigdec-arr
    ^"[Ljava.math.BigDecimal;"
    (into-array BigDecimal [1.0M]))

  (defn average-my [numbers]
    (/ (reduce + numbers) (count numbers)))

  (defn average [numbers]
    (let [total (apply + numbers)]
      (/ total (count numbers))))

  (defn safe-average [numbers]
    (let [total (apply + numbers)]
      (try
        (/ total (count numbers))
        (catch ArithmeticException e
          (println "divided by zero")
          0))))

  (average [])
  (safe-average [])

  (try
    (/ 1 0)
    (catch RuntimeException e "Runtime")
    (catch ArithmeticException e "Ari")
    (finally (println "done")))

  (def total-cost-again
    (fn [items-amount price]
      (* items-amount price)))

  (total-cost-again 22 2)


  (defn total-cost
    "does stupid multiplication"
    [items-count price]
    (* items-count price))

  (:doc (meta #'total-cost))

  (defn ^{:first-meta 1} stoopid "returns parameter" {:second-meta 2} [x] x)

  (:doc (meta #'stoopid))

  (defn item-total [price quantity discount]
    {:pre  [(> price 0) (> quantity 0)]
     :post [(< % 1000)]}
    (->> (/ discount 100)
         (- 1)
         (* price quantity)
         float))

  (item-total 100 2 5)

  ;; Assert will fail
  (item-total 0 2 5)

  (defn multiarity
    ([x] (println "one") 1)
    ([x y] (println "two") 2))

  (defn sum-all [& nums]
    (apply + nums))

  (sum-all 0 1 2 3 4 5)

  (defn count-down [x]
    (when-not (zero? x)
      (when (zero? (rem x 100))
        (println "countdown " x))
      (recur (dec x))))

  (count-down 10000)

  (declare hat)

  (defn cat [n]
    (when-not (zero? n)
      (when (zero? (rem n 100))
        (println "cat." n))
      (hat (dec n))))

  (defn hat [n]
    (when-not (zero? n)
      (when (zero? (rem n 100))
        (println "hat." n))
      (cat (dec n))))

  (cat 100000)


  (declare hatt)

  (defn catt [n]
    (when-not (zero? n)
      (when (zero? (rem n 100))
        (println "catt:" n))
      (fn [] (hatt (dec n)))))

  (defn hatt [n]
    (when-not (zero? n)
      (when (zero? (rem n 100))
        (println "hatt:" n))
      (fn [] (catt (dec n)))))

  (trampoline catt 10000)

  (defn tezt [& more]
    (doseq [x more] (println x "; "))
    0)

  (apply tezt [1 2 3 4 5])

  (def vals [1 2 3 4 5])
  (every? even? vals)

  (def names ["Alice" "Bob" "Curt" "Donna"])
  (some (fn [s] (= s "Bob")) names)

  (def const (constantly "CONSTANT"))
  (apply const [1 2 3])

  (defn greater? [x y]
    (> x y))
  (def smaller? (complement greater?))

  (def combi (comp
              (fn [x] (str x "-"))
              (fn [x] (str x "!"))
              (fn [x] (str x "?"))))
  (combi 1)

  (defn above-threshold [threshold number]
    (> number threshold))
  (filter (fn [x] (above-threshold 5 x)) [1 2 3 4 5 6 7 8])
  (def above-five (partial above-threshold 5))
  (filter above-five [1 2 3 4 5 6 7 8])



  (def users
    [{:username     "kyle"
      :firstname    "Kyle"
      :lastname     "Smith"
      :balance      175.00M
; Use BigDecimals for money!
      :member-since "2009-04-16"}
     {:username     "zak"
      :firstname    "Zackary"
      :lastname     "Jones"
      :balance      12.95M
      :member-since "2009-02-01"}
     {:username     "rob"
      :firstname    "Robert"
      :lastname     "Jones"
      :balance      98.50M
      :member-since "2009-03-30"}])

  (defn sorter-using [ordering-fn]
    (fn [collection]
      (sort-by ordering-fn collection)))

  (defn lastname-firstname [user]
    [(user :lastname) (user :firstname)])

  (defn balance [user] (user :balance))

  (defn username [user] (user :username))

  (def poorest-first (sorter-using balance))
  (def alphabetically (sorter-using username))
  (def last-first-name (sorter-using lastname-firstname))

  (poorest-first users)

  (map :lastname users)

  (def anon-fun (fn [x] (str "this is " x)))
  (def another-anon-fun #(str "this is " %))

  (another-anon-fun "sparta")
  (anon-fun "sparta")

  (= (- 10 (* 2 3)) 4)

  (#(vector %&) 1 2 3 4 5)
  (map #(str % "-") [1 2 3 4 5])

  (def person {:name          "Vasya"
               :surname       "Petkin"
               :status        "Winner"
               :money         nil
               'symbol "LOL"})

  (:name person)
  (:money person)
  (:no-such-prop person :not-found) ;; default value to avoid ambiguity 

  (map :member-since users)

  ('symbol person)
  (person 'symbol)


  (def ^:dynamic SOME-VAR)

  (binding [SOME-VAR "ololo"]
    (str SOME-VAR))

  (try (SOME-VAR)
       (catch Exception e "OOPS"))

  (def ^:dynamic *db-host* "localhost")

  (defn expense-report [start-date end-date]
    (println *db-host*))

  (binding [*db-host* "production"]
    (expense-report "2010-01-01" "2010-01-07"))

  (expense-report "2010-01-01" "2010-01-07")

  (def ^:dynamic dynvar 10)
  (defn print-var [label]
    (println label dynvar))
  (print-var "A: ")
  (binding [dynvar 20]
    (print-var "B: ")
    (binding [dynvar 30]
      (print-var "C: "))
    (print-var "D:"))
  (print-var "E: ")


  (defn ^:dynamic twice [x]
    (println "toooowise")
    (* 2 x))
  (defn call-twice [x]
    (twice x))
  (defn with-log [func logmsg]
    (fn [& args]
      (println "logging: " logmsg)
      (apply func args)))
  (call-twice 10)
  (binding [twice (with-log twice "calling TWICE")]
    (call-twice 20))
  (call-twice 30)

  (def ^:dynamic *factor* 10)
  (defn multiply [x]
    (* x *factor*))
  (map multiply [1 2 3 4 5])
  ;; map is lazy and therefore binding does not work
  (binding [*factor* 20]
    (map multiply [1 2 3 4 5]))
  (binding [*factor* 20]
    (doall (map multiply [1 2 3 4 5])))

  (let [x 10
        y 20]
    (println (format "x: %d y: %d" x y)))


  ;; 3.4.2 The let form revisited

  (defn upcased [v]
    (let [upp (fn [x] (.toUpperCase x))]
      (map upp v)))

  (upcased ["one" "two" "three"])

  (def ^:dynamic *factor* 10)
  (binding [*factor* 20]
    (println *factor*)
    (doall (map multiply [1 2 3 4 5])))
  ;=> (20 40 60 80 100)

  (let [*factor* 20]
    (println *factor*)
    (doall (map multiply [1 2 3 4 5])))
  ;=> (10 20 30 40 50)
  ;=> потому что внутренности multiply вне скоупа let, и let не влияет на динамический var

  (defn create-scaler [scale]
    (fn [x] (* x scale)))

  (def percent-scaler (create-scaler 100))

  (percent-scaler 0.59)

  (defn- privv [x]
    (* x x))

  (privv 11)

  (json-lib/json-str users)

  (prob/nice-rev [12 3 4 5])
  (all-ns)

  (defn describe-smth [person]
    (let [first  (:first-name person)
          last   (:last-name person)
          annual (:salary person)]
      (println first last "earns" annual)))

  (defn describe-smth-2 [{first  :first-name
                          last   :last-name
                          annual :salary
                          bonus  :bonus
                          :or    {bonus 5}
                          :as    all}]
    (println first last "earns" annual "and has bonus" bonus)
    (println all))

  (describe-smth {:first-name "Vasya"
                  :last-name  "Sidorov"
                  :salary     400000M})
  (describe-smth-2 {:first-name "Vasya"
                    :last-name  "Sidorov"
                    :salary     400000M})
  (describe-smth-2 {:first-name "Petya"
                    :last-name  "Ivanov"
                    :salary     400000M
                    :bonus      1})

  (defn print-vec [[elem-1 elem-2]]
    (println "printing" elem-1 "and" elem-2))
  (print-vec [1 2])

  (defn print-smth-with-all [[smth-1 smth-2 & remaining :as all]]
    (println "fir" smth-1 "sec" smth-2 "other" remaining)
    (println "also all" all))

  (print-smth-with-all [1 2 3 4])

  (defn print-categs [[[category amount] & _]]
    (println "first category" category)
    (println "first amount" amount))

  (def expenses [[:books 49.95] [:coffee 4.95] [:caltrain 2.25]])
  (print-categs expenses)

  (defn greet-person [{:keys [first-name last-name]}]
    (println "Welcome" first-name last-name))

  (greet-person {:first-name "Petya"
                 :last-name  "Ivanov"
                 :salary     400000M
                 :bonus      1})

  (java.util.UUID/randomUUID)

  (use 'todos.datareaders)
  (todos.datareaders/guid "aaaa1111")


  (defn poly-func [obj]
    (condp = (type obj)
      java.lang.String "String"
      clojure.lang.PersistentVector "Vector"))

  (poly-func ["a" "b"])

  (def typez {java.lang.String              (fn [x] "String")
              clojure.lang.PersistentVector (fn [x] "Vector")})

  (defn poly-func-2 [obj]
    (let [dispatch-value (type obj)]
      (if-let [implementation
               (get typez dispatch-value)]
        (implementation obj)
        (throw (IllegalArgumentException.
                (str "No implementation found for " dispatch-value))))))

  (poly-func-2 "oo")

  (def typez
    (assoc typez
           clojure.lang.PersistentArrayMap (fn [x] "Map")))

  (poly-func-2 {})

  (defn map-type-namer [obj]
    (condp = (type obj)
      clojure.lang.PersistentArrayMap "map"
      clojure.lang.PersistentHashMap  "map"))

  (defn good-map-type-namer [obj]
    (cond
      (instance? clojure.lang.APersistentMap obj) "map"
      :else (throw (IllegalArgumentException. (str "Unknown type" (type obj))))))

  (map-type-namer (hash-map))
  (map-type-namer (array-map))
  (map-type-namer (sorted-map))
  (good-map-type-namer (sorted-map))

  (def example-person {:login    "Bob"
                       :referrer "lol.com"
                       :salary   100000})

  (defn fee-amount [percentage user]
    (with-precision 16 :rounding HALF_EVEN
                    (* 0.01M percentage (:salary user))))

  (defn affiliate-fee [user]
    (case (:referrer user)
      "google.com" (fee-amount 0.01M user)
      "lol.com" (fee-amount 0.03M user)
      (fee-amount 0.02M user)))

  (affiliate-fee example-person)

  (defmulti affiliate-fee-2
    (fn [user] (:referrer user)))
  (defmethod affiliate-fee-2 "lol.com" [user]
    (fee-amount 0.03M user))
  (defmethod affiliate-fee-2 "google.com" [user]
    (fee-amount 0.01M user))
  (defmethod affiliate-fee-2 :default [user]
    (fee-amount 0.02M user))


  (affiliate-fee-2 example-person)

  (def operations {'+ "+"
                   '- "-"})

  (defmulti calculator
    (fn [oper x y] (operations oper))
    :default "*")
  (defmethod calculator "+" [oper x y]
    (+ x y))
  (defmethod calculator "-" [oper x y]
    (- x y))
  (defmethod calculator "*" [oper x y]
    (* x y))

  (methods calculator)
  (get-method calculator "+")

  (def user-1 {:login    "rob"
               :referrer "mint.com"
               :salary   100000
               :rating   :rating/bronze})
  (def user-2 {:login    "gordon"
               :referrer "mint.com"
               :salary   80000
               :rating   :rating/silver})
  (def user-3 {:login    "kyle"
               :referrer "google.com"
               :salary   90000
               :rating   :rating/gold})
  (def user-4 {:login    "celeste"
               :referrer "yahoo.com"
               :salary   70000
               :rating   :rating/platinum})

  (defn fee-category [user]
    [(:referrer user) (:rating user)])

  (map fee-category [user-1 user-2 user-3 user-4])

  (defmulti profi-based-affiliate-fee fee-category)
  (defmethod profi-based-affiliate-fee ["mint.com" :rating/bronze]
    [user] (fee-amount 0.03M user))
  (defmethod profi-based-affiliate-fee :default
    [user] (fee-amount 0.02M user))

  (map profi-based-affiliate-fee [user-1 user-2 user-3 user-4])

  (defmulti greet-multi :rating)
  (defmethod greet-multi :rating/basic [user]
    (str "Hello " (:login user) "."))
  (defmethod greet-multi :rating/premium [user]
    (str "Hello, platinum " (:login user) "."))
  (defmethod greet-multi :default [user]
    (str "Get out, " (:login user) "! You are a moron!"))

  (derive :rating/bronze :rating/basic)
  (derive :rating/silver :rating/basic)
  (derive :rating/gold :rating/premium)
  (derive :rating/platinum :rating/premium)
  (derive :rating/basic :rating/ANY)
  (derive :rating/platinum :rating/ANY)

  (isa? :rating/gold :rating/premium)
  (parents :rating/gold)
  (ancestors :rating/platinum)
  (parents :rating/platinum)
  (descendants :rating/premium)

  (ns-unmap *ns* 'my-multi)

  (map greet-multi [user-1 user-2 user-3 user-4 example-person])

  (remove-method greet-multi :rating/basic)

  (defmulti size-up (fn [observer observed]
                      [(:rating observer) (:rating observed)]))

  (prefer-method size-up [:rating/ANY :rating/platinum]
                 [:rating/paltinum :rating/ANY])

  (defmethod size-up [:rating/premium :rating/ANY] [_ observed]
    (str (:login observed) " seems weak"))

  (defmethod size-up [:rating/ANY :rating/premium] [_ observed]
    (str (:login observed) " seems scary"))

  (size-up {:rating :rating/premium} {:rating :rating/platinum})
  (size-up {:rating :rating/platinum} {:rating :rating/premium})

  (defmulti my-multi (fn [& more] more))
  (defmethod my-multi :default
    ([] "none")
    ([x] "one")
    ([x y] "two")
    ([x y & etc] "many"))

  (my-multi)
  (my-multi "x")
  (my-multi "x" "y")
  (my-multi "x" "y" "z")

  (def myhier (make-hierarchy))

  myhier

  (derive myhier :a :letter)

  (def myhier (-> myhier
                  (derive :a :letter)
                  (derive :b :letter)
                  (derive :c :letter)))

  (isa? myhier :a :letter)
  (parents myhier :a)

  (defmulti letter? identity :hierarchy #'myhier)
  (defmethod letter? :letter [_] true)
  (letter? :a)
  (letter? :d)

  (def myhier (derive myhier :d :letter))

  (import '(java.text SimpleDateFormat))
  (def sdf (new SimpleDateFormat "yyyy-MM-dd"))
  (def sdf-2 (SimpleDateFormat. "yyyy-MM-dd"))

  (defn parse-date [date]
    (let [sdf (SimpleDateFormat. "dd.MM.yyyy")]
      (.parse sdf date)))

  (parse-date "14.01.1987")

  (Long/parseLong "123321")

  (import '(java.util Calendar))
  Calendar/JANUARY

  (. System (getenv "PATH"))
  (. System getenv "PATH")

  (import '(java.util Random))
  (def rnd (Random.))
  (. rnd nextInt 10)

  (. (. (Calendar/getInstance) (getTimeZone)) (getDisplayName))
  (.. (Calendar/getInstance) getTimeZone getDisplayName)

  (import '(java.util Calendar))

  (defn the-past-midnight []
    (let [calendar-obj (Calendar/getInstance)]
      (.set calendar-obj Calendar/AM_PM Calendar/AM)
      (.set calendar-obj Calendar/HOUR 0)
      (.set calendar-obj Calendar/MINUTE 0)
      (.set calendar-obj Calendar/SECOND 0)
      (.set calendar-obj Calendar/MILLISECOND 0)
      (.getTime calendar-obj)))
  (the-past-midnight)

  (defn short-past-midnight []
    (let [calendar-obj (Calendar/getInstance)]
      (doto calendar-obj
        (.set Calendar/AM_PM Calendar/AM)
        (.set Calendar/HOUR 0)
        (.set Calendar/MINUTE 0)
        (.set Calendar/SECOND 0)
        (.set Calendar/MILLISECOND 0))
      (.getTime calendar-obj)))
  (short-past-midnight)

  (set! *warn-on-reflection* true)
  (time (count (map #(.getBytes %) (map #(str %) (range 0 100000)))))
  (time (count (map (memfn ^String getBytes) (map #(str %) (range 0 100000)))))

  ((memfn ^String subSequence ^Long start ^Long end) "Clojure" 2 5)

  (bean (Calendar/getInstance))

  (def tokens (.split "what.the.fuck" "\\."))
  (type tokens)
  (alength tokens)
  (aget tokens 2)
  (aset tokens 2 "lol")

  (def arra (to-array [1 2 3]))
  (aset arra 2 2)
  ;; mutable!

  (def arra-2d (to-array-2d [[1 2 3] [4 5 6] [7 8 9]]))
  #_{:clj-kondo/ignore [:invalid-arity]}
  (aget arra-2d 2 0)

  (def marra (into-array {:a 1
                          :b 2}))

  (import 'java.awt.event.MouseAdapter)
  (proxy [MouseAdapter] []
    (mousePressed [event]
      (println "Hey!")))

  (reify java.io.FileFilter
    (accept [this f]
      (.isDirectory f)))


  (def all-users (ref {}))
  (deref all-users)
  @all-users

  (dosync
   (ref-set all-users {}))

  (defn new-user [id login budget]
    {:id             id
     :login          login
     :budget         budget
     :total-expences 0})

  (defn add-new-user [login budget-amount]
    (dosync
     (let [current-number (count @all-users)
           user           (new-user (inc current-number) login budget-amount)]
       (alter all-users assoc login user))))

  (add-new-user "first" 10000)
  (add-new-user "second" 20000)

  (def total-cpu-time (agent 0))
  @total-cpu-time

  (send total-cpu-time + 100)

  (defn slow-move [x y]
    (println "slowing down..." x y)
    (doseq [x (range 1 99999999)])
    (* 1 y))

  (def agent-one (agent 30))
  (def agent-two (agent 20))
  (def agent-three (agent 10))

  (defn try-agents []
    (let [x 100]
      (send agent-one slow-move x)
      (send agent-two slow-move 50)
      (send agent-three slow-move 20))
    (await-for 500000 agent-one agent-two agent-three))

  (try-agents)
  (slow-move 1 2)

  (def bad-agent (agent 1))
  (send bad-agent / 0)
  @bad-agent
  (send bad-agent / 2)
  (agent-error bad-agent)

  (let [e  (agent-error bad-agent)
        st (.getStackTrace e)]
    (println (.getMessage e))
    (println (clojure.string/join "\n" st)))

  (restart-agent bad-agent 1)

  ;; агент внутри транзакции выполнится один раз, хотя
  ;; сама транзакция может повторяться

  ;; (dosync
  ;;  (send agent-one log-message args-one)
  ;;  (send-off agent-two send-message-on-queue args-two)
  ;;  (alter a-ref ref-function)
  ;;  (some-pure-function args-three))

  (def total-smth (atom {"one" 1
                         "two" 2}))
  @total-smth
  (reset! total-smth "newval")
  (swap! total-smth str "-add")
  (compare-and-set! total-smth "newval-add" "replaced-val")

  (def almost-rand-nums (atom ""))
  (reset! almost-rand-nums "")
  @almost-rand-nums

  (defn add-val-to-atom [x]
    (swap! almost-rand-nums str x))
  (add-val-to-atom 1)

  (map (fn [x] (swap! almost-rand-nums str x)) (range 1 10))


  (def listened-atom (atom ""))
  (add-watch listened-atom :lislistener
             (fn [ke re ol ne] (println (str "ke " ke "re " re "ol " ol "ne " ne))))
  (swap! listened-atom str "op")
  (reset! listened-atom "")
  (remove-watch listened-atom :lislistener)


  (defn long-func [x]
    (Thread/sleep 5000)
    (* x x))

  (defn calc-faster []
    (let [x (future (long-func 2))
          y (future (long-func 4))
          z (future (long-func 8))]
      (str @x @y @z)))

  (time (calc-faster))


  (def prom (promise))
  ;; never run this in REPL! (def prom-val (deref prom))

  (let [p (promise)]
    (future (Thread/sleep 5000)
            (deliver p :done))
    @p)


  #_{:clj-kondo/ignore [:unresolved-symbol]}
  (defnn print-details [name salary start-date]
    (println "Name:" name)
    (println "Salary:" salary)
    (println "Started on:" start-date))


  (defn square [x]
    (* x x))

  (defn square-all [nums]
    (if (empty? nums)
      nil
      (cons (square (first nums))
            (square-all (rest nums)))))

  (square-all [1 2 3 4])

  ;; can throw stackoverflow
  (defn do-to-all-dang [f nums]
    (if (empty? nums)
      nil
      (cons (f (first nums))
            (do-to-all-dang f (rest nums)))))

  (do-to-all-dang square [1 2 3 4])

  ;; lazy, does not throw stackoverflow
  (defn do-to-all [f nums]
    (lazy-seq
     (if (empty? nums)
       nil
       (cons (f (first nums))
             (do-to-all f (rest nums))))))

  (drop 10000 (do-to-all square (range 10010)))
  
  (mapcat str [1 2] [2 1])
  
)



