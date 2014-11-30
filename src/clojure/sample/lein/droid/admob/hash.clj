(ns sample.lein.droid.admob.hash
  (:import
    [java.security MessageDigest]))

(def algorithms
  #{:SHA-1 :SHA-256 :SHA-384 :SHA-512
    :MD5})

(defn get-hash
  "Create hashed byte-array.
  data-bytes: data-bytes you want to hash.
  [optional] algorithm: keyword of algorithm-name you want to use.
  default, use SHA-512 algorithm.

  ex) (get-hash \"non-hashed\")
  (get-hash \"non-hashed\" :MD5)"
  [data & algorithm]
  (let [algorithm (name (or (algorithms (first algorithm))
                            :SHA-512))]
    (.digest (java.security.MessageDigest/getInstance algorithm)
             (.getBytes data))))

(defn get-hash-str
  "Create hashed-string.
  data-bytes: data-bytes you want to hash.
  [optional] algorithm: keyword of algorithm-name you want to use.
  default, use SHA-512 algorithm.

  ex) (get-hash-str \"non-hashed\")
  (get-hash-str \"non-hashed\" :MD5)"
  [data-bytes & algorithm]
  (->> (get-hash data-bytes (first algorithm))
       (map (comp byte int))
       (byte-array)
       (bytes)
       (map #(.substring
               (Integer/toString
                 (+ (bit-and % 0xff) 0x100) 16) 1))
       (clojure.string/join)))

