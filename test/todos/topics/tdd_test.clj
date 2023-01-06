(ns todos.topics.tdd-test
  (:require [clojure.test :refer :all]
            [todos.topics.tdd :refer :all]))

(deftest test-simple-parsing
  (let [d (date "2011-01-22")]
    (is (= 22 (day-from d)))
    (is (= 1 (month-from d)))
    (is (= 2011 (year-from d)))))

(deftest test-as-string
  (let [d (date "2011-05-13")]
    (is (= (as-string d) "2011-05-13"))))

(deftest test-increment-date
    (let [d (date "2011-05-13")
          nth-day (increment-day d)]
      (is (= (as-string d) "2011-05-14"))))
