(ns todos.topics.compiletime)

(def ALPHABETS [\a \b \c \d \e \f \g \h \i 
                \j \k \l \m \n \o \p \q \r 
                \s \t \u \v \w \x \y \z])

(def NUM-ALPHABETS (count ALPHABETS))
(def INDICES (range 1 (inc NUM-ALPHABETS)))
(def lookup (zipmap INDICES ALPHABETS))

(defn shift [shift-by index]
  (let [shifted (+ (mod shift-by NUM-ALPHABETS) index)]
    (cond
      (<= shifted 0) (+ shifted NUM-ALPHABETS)
      (> shifted NUM-ALPHABETS) (- shifted NUM-ALPHABETS)
      :default shifted)))

(defn shifted-tableau [shift-by]
  (->> (map #(shift shift-by %) INDICES)
       (map lookup)
       (zipmap ALPHABETS)))

(shifted-tableau 13)

(defn encrypt [shift-by tex]
  (let [shifted (shifted-tableau shift-by)]
    (apply str (map shifted tex))))

(encrypt 12 "lol")
(map {\a 1 \b 2 \c 3} "abbccc")

(defn decrypt [shift-by encrypted]
  (encrypt (- shift-by) encrypted))

(defmacro def-rot-encryptor [name shift-by]
  (let [tableau (shifted-tableau shift-by)]
    `(defn ~name [~'message]
       (apply str (map ~tableau ~'message)))))

