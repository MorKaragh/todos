(ns todos.tryouts
  (:require [clojure.string :as str])
  (:gen-class))

(def randVar 10)
(def aString "foo bar baz")
(def ranDouble 10.01)

(defn -main
  "I don't do a whole lot."
  [& args]

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
  
  (defn slow-calc [x y]
    (Thread/sleep 1000)
    (* x y))
  
  (def fast-calc (memoize slow-calc))
  
  (fast-calc 2 5)
  
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
  
  (def person {:name    "Vasya"
               :surname "Petkin"
               :status  "Winner"
               :money nil
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
  
  (let [x 10 y 20]
    (println (format "x: %d y: %d" x y)))
  
  
  
  
  )



