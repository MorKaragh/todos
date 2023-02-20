(ns todos.topics.exprproblem
  (:import [java.text SimpleDateFormat]
           [myexpense Expense]))

(defn new-expense [date-string dollars cents category merchant-name]
  {:date (.parse (SimpleDateFormat. "yyyy-MM-dd") date-string)
   :amount-dollars dollars
   :amount-cents cents
   :category category
   :merchant-name merchant-name})

(defmulti total-cents class)
(defmethod total-cents clojure.lang.IPersistentMap [e]
  (-> (:amount-dollars e)
      (* 100)
      (+ (:amount-cents e))))
(defmethod total-cents myexpense.Expense [e]
  (.amountInCents e))

(defn total-amount
  ([expenses-list]
   (total-amount (constantly true) expenses-list))
  ([pred expenses-list]
   (->> expenses-list
        (filter pred)
        (map total-cents)
        (apply +))))

(defmulti is-category? (fn [e category] (class e)))
(defmethod is-category? clojure.lang.IPersistentMap [e some-category]
  (= (:category e) some-category))
(defmethod is-category? myexpense.Expense [e some-category]
  (= (.getCategory e) some-category))

(defn category-is [category]
  #(is-category? % category))

(def java-expenses [(Expense. "2009-8-24" 44 55 "books" "ozon.ru")])

