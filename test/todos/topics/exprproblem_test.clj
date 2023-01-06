(ns todos.topics.exprproblem-test
  (:require [todos.topics.exprproblem :refer :all]
            [clojure.test :refer :all]))

(def clj-expenses [(new-expense "2014-8-20" 22 33 "books" "ozon.ru")
                   (new-expense "2014-11-21" 33 44 "ammo" "ammuninion.ru")
                   (new-expense "2014-6-14" 55 66 "food" "pyaterochka")
                   (new-expense "2014-7-1" 77 88 "courses" "yandex")])

(deftest test-expenses-total
  (is (= 18931 (total-amount clj-expenses))))

(def mixed-expenses (concat clj-expenses java-expenses))

(deftest test-mixed-expenses-total
  (is (= 23386 (total-amount mixed-expenses)))
  (is (= 6688 (total-amount (category-is "books") mixed-expenses))))