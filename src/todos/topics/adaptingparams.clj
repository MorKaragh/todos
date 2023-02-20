(ns todos.topics.adaptingparams
  (:require [todos.highlevfunc :refer [compute-across]]))


(defn price-with-taxes [tax-rate amount]
  (->> (/ tax-rate 100)
       (+ 1)
       (* amount)))

(defn price-calculator-with-tax [state-tax]
  (fn [price]
    (price-with-taxes state-tax price)))

(def prices-for-mordor (price-calculator-with-tax 17M))
(prices-for-mordor 10000)


(defn select-into-if [container pred elements]
  (compute-across #(if (pred %2) (conj %1 %2) %1) elements container))

(select-into-if () #(> 7 %) [1 4 6 7 9 22 23])

(def select-up (partial select-into-if []))

(select-up #(> 7 %) [1 4 6 7 9 22 23])

(def plus-two (partial + 2))
(def two-minus (partial - 2))



