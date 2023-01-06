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
  (doto d
      (.add Calendar/DAY_OF_MONTH 1)))


