(ns todos.topics.tdd-test
  (:require [clojure.test :refer :all]
            [todos.topics.tdd :refer :all]
            [todos.topics.stubbing :refer :all]))

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
        nth-day (increment-day d)
        nth-month (increment-month d)
        nth-year (increment-year d)]
    (is (= (as-string nth-day) "2011-05-14"))
    (is (= (as-string nth-month) "2011-06-13"))
    (is (= (as-string nth-year) "2012-05-13"))))

(deftest test-decrement-date
  (let [d (date "2011-05-13")
        nth-day (decrement-day d)
        nth-month (decrement-month d)
        nth-year (decrement-year d)]
    (is (= (as-string nth-day) "2011-05-12"))
    (is (= (as-string nth-month) "2011-04-13"))
    (is (= (as-string nth-year) "2010-05-13"))))


(def expensez [{:amount 10.0 :date "2010-02-28"}
               {:amount 20.0 :date "2010-02-25"}
               {:amount 30.0 :date "2010-02-21"}])

(defmocktest test-fetch-expenses-greater-than
  (stubbing [fetch-all-expenses expensez]
            (let [filtered (fetch-expenses-greater-than "" "" "" 15.0)]
              (is (= (count filtered) 2))
              (is (= (:amount (first filtered)) 20.0))
              (is (= (:amount (last filtered)) 30.0)))))

(defmocktest expenses-greater-than-test
  (mocking [log-call]
           (let [expensez [{:amount 10.0 :date "2010-02-28"}
                           {:amount 20.0 :date "2010-02-25"}
                           {:amount 30.0 :date "2010-02-21"}]
                 filtered (expenses-greater-than expensez 15.0)]
             (is (= (count filtered) 2))
             (is (= (:amount (first filtered)) 20.0))
             (is (= (:amount (last filtered)) 30.0)))
           (testing "log-call called correctly"
             (verify-call-times-for log-call 1)
             (verify-first-call-args-for log-call "expenses-greater-than" 15.0)
             (verify-nth-call-args-for 1 log-call "expenses-greater-than" 15.0))))

(deftest simple-upper-test
  (are [l u] (= u (.toUpperCase (str l)))
    "hello" "HELLO"
    "world" "WORLD"))


