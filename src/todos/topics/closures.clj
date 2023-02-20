(ns todos.topics.closures)

;; x - "свободная" переменная в анонимной функции (fn), 
;;     т.к. это не параметр и не локальная переменная
(defn adder [num1 num2]
  (let [x (+ num1 num2)]
    (fn [y]
      (+ x y))))

(def add-5 (adder 2 3))

(add-5 10)


(defn try-catch [the-try the-catch]
  (try
    (the-try)
    (catch Exception e (the-catch e))))

(let [x 1
      y 0]
  (try-catch #(/ x y) #(println (.getMessage %))))


(defn new-user [login password email]
  (fn [a & args]
    (case a
      :login login
      :password password
      :email email
      :authenticate (= password (first args))
      nil)))

(def usr-1 (new-user "login-1" "pass-1" "email@1.1"))
(usr-1 :login)
(usr-1 :authenticate "pass-1")


