(ns todos.topics.memoize)

(defn slow-calc [x y]
  (Thread/sleep 1000)
  (* x y))

(def fast-calc (memoize slow-calc))

(fast-calc 2 5)