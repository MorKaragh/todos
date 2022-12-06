(ns todos.problems)

(defn nice-rev [v] 
  (let [i (count v)]
    (loop [index i vect []]
      (println vect)
     (if (zero? index) 
       vect
       (recur (dec index) (conj vect (get v (dec index))))))) 
  )

(nice-rev [1 2 3 4 5 6 7 8 9])
