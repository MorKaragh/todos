(ns todos.datareaders
  (:import java.util.UUID))

(defn guid [flfd]
  (java.util.UUID/fromString (str flfd "-1000-413f-8a7a-f11c6a9c4036")))