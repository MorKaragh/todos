(ns todos.topics.tdd
  (:require [clojure.string :as str])
  (:import [java.text SimpleDateFormat]
           [java.util GregorianCalendar]
           [java.util Calendar]))

(defn date [date-string]
  (let [f (SimpleDateFormat. "yyyy-MM-dd")
        d (.parse f date-string)]
    (doto (GregorianCalendar.)
      (.setTime d))))

(defn day-from [d]
  (.get d Calendar/DAY_OF_MONTH))

(defn year-from [d]
  (.get d Calendar/YEAR))

(defn month-from [d]
  (inc (.get d Calendar/MONTH)))

(defn pad [x]
  (format "%02d" x))

(defn as-string [d]
  (let [y (year-from d)
        m (month-from d)
        d (day-from d)]
    (str/join "-" [y (pad m) (pad d)])))

(defn increment-day [d]
  (doto (.clone d)
    (.add Calendar/DAY_OF_MONTH 1)))

(defn increment-month [d]
  (doto (.clone d)
    (.add Calendar/MONTH 1)))

(defn increment-year [d]
  (doto (.clone d)
    (.add Calendar/YEAR 1)))

(defn decrement-day [d]
  (doto (.clone d)
    (.add Calendar/DAY_OF_MONTH -1)))

(defn decrement-month [d]
  (doto (.clone d)
    (.add Calendar/MONTH -1)))

(defn decrement-year [d]
  (doto (.clone d)
    (.add Calendar/YEAR -1)))


;; for mocking


(defn log-call [id & args]
  (println "Audit: called " id " with " (str/join ", " args)))

(defn fetch-all-expenses [username start-date end-date]
  (log-call "fetch all" username start-date end-date)
  ;; querying database
  )

(defn expenses-greater-than [expenses threshold]
  (log-call "expenses-greater-than" threshold)
  (filter #(> (:amount %) threshold) expenses))

(defn fetch-expenses-greater-than [username start-date end-date threshold]
  (let [all (fetch-all-expenses username start-date end-date)]
    (expenses-greater-than all 10)))

