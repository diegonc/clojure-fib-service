(ns fib-service.fibonacci)

(defn- Ymem
  "Caching Y combinator
   ref: http://matt.might.net/articles/implementation-of-recursive-fixed-point-y-combinator-in-javascript-for-memoization/"
  ([F] (Ymem F (atom {})))
  ([F cache]
   (fn [& args]
     (if-let [cached (get-in @cache args)]
       cached
       (let [helper
             (fn [& args]
               (apply (Ymem F cache) args))
             val (apply (F helper) args)]

         (do
           (swap! cache assoc-in args val)
           val))))))

(defn-
  fib-functional
  "Functional whose fixed point is the 'fibonacci' function"
  [g]
  (fn [n]
    (condp = n
      ; the base cases need to return BigInt to make the
      ; result an arbitrary precision integer
      0 (bigint 0)
      1 (bigint 1)
      (+ (g (- n 2)) (g (- n 1))))))

(def fib (Ymem fib-functional))

(defn fib-seq
  "Compute (fib n); cache contains all the sequence till n"
  [n]
  (let [cache (atom {})]
    (do
      ((Ymem fib-functional cache) n)
      (for [x (range (inc n))]
        (get @cache x)))))
