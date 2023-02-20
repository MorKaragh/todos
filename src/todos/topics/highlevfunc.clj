(ns todos.topics.highlevfunc)


(defn total-of [numbers]
  (loop [nums numbers
         sum  0]
    (if (empty? nums)
      sum
      (recur (rest nums) (+ sum (first nums))))))

(total-of [1 2 3 4])

(defn larger-of [x y]
  (if (> x y) x y))

(defn largest-of [numbers]
  (loop [nums      numbers
         candidate (first numbers)]
    (if (empty? nums)
      candidate
      (recur (rest nums) (larger-of candidate (first nums))))))

(largest-of [1 2 3])

(defn compute-across [func elements value]
  (if (empty? elements)
    value
    (recur func (rest elements) (func value (first elements)))))

(defn total-of-2 [numbers]
  (compute-across + numbers 0))
(defn max-of [numbers]
  (compute-across larger-of numbers (first numbers)))

(max-of [1 2 3])
(total-of-2 [1 2 3])

(defn all-greater-than-my [threshold numbers]
  (compute-across #(if (> %2 threshold) (conj %1 %2) %1) numbers []))

(defn all-greater-then [threshold numbers]
  (reduce #(if (> %2 threshold) (conj %1 %2) %1) [] numbers))

(all-greater-then 5 [1 2 4 5 6 7 8])
(all-greater-then 5 [])

(defn all-less-than-my [threshold numbers]
  (compute-across #(if (< %2 threshold) (conj %1 %2) %1) numbers []))

(all-less-than-my 5 [1 3 5 6 7])

(defn select-if [pred elements]
  (compute-across #(if (pred %2) (conj %1 %2) %1) elements []))

(select-if odd? [1 2 3 4 5 6])
