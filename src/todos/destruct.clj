(ns todos.destruct)

;; выдергивание значений из мапы
(defn print-person [{name :name
                     age :age
                     :or {age 15}}]
  (str "Person " name " is of age " age))

(print-person {:name "Stepan" :age 25})
(print-person {:name "Stepan"})

;; важны названия параметров (-1, -2)
(defn print-vals [[a-1 a-2 & x :as all]]
  (println a-1 a-2)
  (println all))

(print-vals [1 2 3])

;; тут разберет только первый элемент вектора
(defn print-vals-1 [[[first second] & _]]
  (str first second))

(print-vals-1 [["Kick" "Lol"] [:this :wont-be-printed]])
