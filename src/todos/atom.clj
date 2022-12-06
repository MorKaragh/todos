(ns todos.atom
  (:require [clojure.string :as str])
  (:gen-class))

(def randVar 10)
(def aString "foo bar baz")
(def ranDouble 10.01)

(defn atom-ex
  [x]
  
  (def atomEx (atom x))
  
  (add-watch atomEx :watcher
       (fn [key atom old-state new-state]
         (println "atomEx changed from " old-state " to " new-state) ))
  
  (println "1st x" @atomEx) 
  (reset! atomEx 10)
  (println "2st x" @atomEx)
  (swap! atomEx inc)
  (println "Increment" @atomEx)
  )

(defn -main
  "Hello atom"
  [& args]
  (println "what the fuck")
  (atom-ex 10))