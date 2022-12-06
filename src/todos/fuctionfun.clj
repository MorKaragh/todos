(ns todos.fuctionfun
  (:gen-class))

(defn addi [x y]
  (+ x y))

(def addinew 
  (fn [x y & more]
    (+ x y)))

(addi 1 2)
(addinew 1 2 3 4 5)

(def users [1 2 2 3])
(defn average-pets []
  (/ (apply + (map :number-pets (vals users))) (count users)))

(defn aver-pets []
  (let [user-data (vals users)
       pet-counts (map :number-pets user-data)
       total (apply + pet-counts)]
  (/ total (count users))))


