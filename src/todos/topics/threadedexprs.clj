(ns todos.topics.threadedexprs)

(def val "a")

(-> val
    (str "b" "-") 
    (str "c" "_"))

(->> val
     (str "b" "-")
     (str "c" "_"))