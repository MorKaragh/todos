(ns todos.topics.spec
  (:require [clojure.spec.alpha :as s]))

(s/conform even? 1000)

(s/valid? even? 1001)

