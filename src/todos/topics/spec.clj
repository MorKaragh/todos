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

(s/valid? (s/coll-of string?) ["a" "b"])

(s/def :tuple/tpl (s/tuple double? double? double?))
(s/valid? :tuple/tpl [1.0 2.0 1.1])

(s/def :mapp/map? (s/map-of keyword? number?))
(s/valid? :mapp/map? {:a 1, :b 2})

(s/def :make/me-a-map (s/cat :name string? :value number?))
(s/conform :make/me-a-map ["Bob" 42])

(s/def :seq/keywords (s/* keyword?))
(s/conform :seq/keywords [:a :b :c])

(s/def :seq/strings (s/+ string?))
(s/conform :seq/strings ["a" "b" "c"])

(s/def :seq/strings-opt (s/+ string?))
(s/conform :seq/strings-opt ["a" "b" "c"])

(s/def :map/keys-then-strings (s/cat :keywords (s/+ keyword?)
                                     :strings (s/+ string?)))
(s/conform :map/keys-then-strings [:a :b :c "a" "b" "c"])

(s/def :map/from-pairs (s/* (s/cat :name string? :val number?)))
(s/conform :map/from-pairs ["Bob" 45 "Alice" 41])

(s/def :map/various (s/* (s/cat :name string?
                                :val (s/alt :bool boolean? :num number?))))
(s/conform :map/various ["Alice" 45 "Bob" true])

(s/def :check/whole-coll-also (s/& (s/* string?) #(= 3 (count %))))
(s/valid? :check/whole-coll-also ["a" "b" "c"])

(s/def :map/with-nested-colls (s/cat :name string? 
                                     :coins (s/spec (s/* number?))
                                     :another-name string?
                                     :her-coins (s/spec (s/* number?))))
(s/conform :map/with-nested-colls  ["Bob" [42 45 44] "Alice" [11 22]])


;;usage

(defn get-age-plus-ten
  [person]
  {:pre [(s/valid? (s/keys :req-un [:speca/name :speca/age]
                           :opt-un [:speca/year]) person)]
   :post [(s/valid? #(> % 42) %)]}
  (println person)
  (+ 10 (:age person)))

(get-age-plus-ten {:name "Bob" :age 34})
(get-age-plus-ten {:name "Alice" :age 20})



