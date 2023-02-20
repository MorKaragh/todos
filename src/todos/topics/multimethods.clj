(ns todos.topics.multimethods)


(def example-person {:login    "Bob"
                     :referrer "lol.com"
                     :salary   100000})

(defn fee-amount [percentage user]
  (with-precision 16 :rounding HALF_EVEN
                  (* 0.01M percentage (:salary user))))

(defn affiliate-fee [user]
  (case (:referrer user)
    "google.com" (fee-amount 0.01M user)
    "lol.com" (fee-amount 0.03M user)
    (fee-amount 0.02M user)))

(affiliate-fee example-person)

(defmulti affiliate-fee-2
  (fn [user] (:referrer user)))
(defmethod affiliate-fee-2 "lol.com" [user]
  (fee-amount 0.03M user))
(defmethod affiliate-fee-2 "google.com" [user]
  (fee-amount 0.01M user))
(defmethod affiliate-fee-2 :default [user]
  (fee-amount 0.02M user))


(affiliate-fee-2 example-person)

(def operations {'+ "+"
                 '- "-"})

(defmulti calculator
  (fn [oper x y] (operations oper))
  :default "*")
(defmethod calculator "+" [oper x y]
  (+ x y))
(defmethod calculator "-" [oper x y]
  (- x y))
(defmethod calculator "*" [oper x y]
  (* x y))

(methods calculator)
(get-method calculator "+")

(def user-1 {:login    "rob"
             :referrer "mint.com"
             :salary   100000
             :rating   :rating/bronze})
(def user-2 {:login    "gordon"
             :referrer "mint.com"
             :salary   80000
             :rating   :rating/silver})
(def user-3 {:login    "kyle"
             :referrer "google.com"
             :salary   90000
             :rating   :rating/gold})
(def user-4 {:login    "celeste"
             :referrer "yahoo.com"
             :salary   70000
             :rating   :rating/platinum})

(defn fee-category [user]
  [(:referrer user) (:rating user)])

(map fee-category [user-1 user-2 user-3 user-4])

(defmulti profi-based-affiliate-fee fee-category)
(defmethod profi-based-affiliate-fee ["mint.com" :rating/bronze]
  [user] (fee-amount 0.03M user))
(defmethod profi-based-affiliate-fee :default
  [user] (fee-amount 0.02M user))

(map profi-based-affiliate-fee [user-1 user-2 user-3 user-4])

(defmulti greet-multi :rating)
(defmethod greet-multi :rating/basic [user]
  (str "Hello " (:login user) "."))
(defmethod greet-multi :rating/premium [user]
  (str "Hello, platinum " (:login user) "."))
(defmethod greet-multi :default [user]
  (str "Get out, " (:login user) "! You are a moron!"))