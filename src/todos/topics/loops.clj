(ns todos.topics.loops)

 (defn fact-loop [n]
   (loop [current n
          fact    1]
     (if (= current 1)
       fact
       (recur (dec current) (* fact current)))))

(defn craz-loop [n]
  (loop [first  1
         second 10]
    (println first second)
    (if (> first second)
      (println "end")
      (recur (inc first) (dec second)))))

(fact-loop 10)
(craz-loop 10)

(defn forich [coll]
  (doseq [itm coll]
    (println itm))
  "ended")

(forich ["one" "two" "three"])

(defn timez [n]
  (dotimes [x n]
    (println "X is " x)))

(timez 5)