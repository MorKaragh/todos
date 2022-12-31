(ns todos.problems)

(defn nice-rev [v] 
  (let [i (count v)]
    (loop [index i vect []]
      (println vect)
     (if (zero? index) 
       vect
       (recur (dec index) (conj vect (get v (dec index))))))))
(nice-rev [1 2 3 4 5 6 7 8 9])


(defn sublist? [test whole] 
    (let [test-len (count test)]
      (println test-len " test: " test " whole: " whole)
      (loop [remaining whole
             found false]
        (if (or found (> test-len (count remaining)))
          found
          (recur (rest remaining)
                 (= test (vec (take test-len remaining))))))))
(sublist? [2 4 1] [2 3 4 3 2 4 1 2 5])


(defn is-sublist-normal [test whole]
  (some #{test} (partition (count test) 1 whole)))

(is-sublist-normal [2 4 1] [2 3 4 3 2 4 1 2 5])



(partition 2 [1 2 3 4 5 6 7 8 9])


  

