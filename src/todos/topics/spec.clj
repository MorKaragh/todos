(ns todos.topics.spec
  (:require [clojure.spec.alpha :as s]
            [clojure.repl :refer [doc]]))

(s/conform even? 1000)

(s/valid? even? 1001)

(s/def :speca/even even?)
(s/def :speca/str string?)
(s/def :speca/combi (s/and string? #(> (count %) 10)))

(s/conform :speca/combi "assssssssssssss")

(doc :speca/even)

(s/explain :speca/combi "hi")
(s/explain-data :speca/combi 100500)

(s/def :speca/name string?)
(s/def :speca/age int?)
(s/def :speca/birth-year int?)

(s/def :speca/person (s/keys :req [:speca/name :speca/age]
                             :opt [:speca/birth-year]))

(s/valid? :speca/person {:speca/name "Name"
                         :speca/age 10})

(s/explain :speca/person {:speca/name "Name"
                          :speca/age 10
                          :speca/birth-year "2000"})

(s/def :speca/person-norm (s/keys :req-un [:speca/name :speca/age]
                                  :opt-un [:speca/birth-year]))

(s/valid?
 :speca/person-norm {:name "Name"
                     :age 10})

(defrecord Person [name age birth-year])

(s/valid? :speca/person-norm (->Person "Bob" 20 "2000"))

(s/def :speca/mark string?)
(s/def :speca/model string?)
(s/def :speca/year int?)

(s/def :speca/vec-car (s/keys* :req [:speca/mark :speca/model]
                           :opt [:speca/year]))

(s/valid? :speca/vec-car [:speca/mark "Ford" 
                      :speca/model "Focus"])

(s/def :speca/car (s/keys :req-un [:speca/mark :speca/model]
                          :opt-un [:speca/year]))

(s/def :speca/used? boolean?)
(s/def :speca/color #{"red" "white" "blue"})

(s/def :speca/sold-car (s/merge :speca/car 
                                (s/keys :req-un [:speca/used? :speca/color])))

(s/valid? :speca/sold-car {:mark "Toyota" 
                           :model "Mark II"  
                           :color "red" 
                           :used? true})


(s/def :event/descr string?)
(s/def :event/status int?)

(defmulti event-type :event/event-type)
(defmethod event-type :event/search [_]
  (s/keys :req [:event/descr]))
(defmethod event-type :event/error [_]
  (s/keys :req [:event/descr :event/status]))

(s/def :event/event (s/multi-spec event-type :event/event-type))

(s/valid? :event/event {:event/event-type :event/search
                        :event/descr "asa"})

(s/valid? :event/event {:event/event-type :event/error
                        :event/descr "asa"
                        :event/status 500})



