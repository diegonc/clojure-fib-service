(ns fib-service.fibonacci)

(defn- nth-fib [n prev]
  (condp = n
    ; the base cases need to return BigInt to make the
    ; result an arbitrary precision integer
    0 (bigint 0)
    1 (bigint 1)
    (+ (prev (- n 2)) (prev (- n 1)))))

(defn fib-seq
  "Compute the prefix sub-sequence of length n of the Fibonacci sequence"
  [n]
  (loop [i 0
         n n
         s []]
    (if (> i n)
      s
      (recur (inc i)
             n
             (conj s (nth-fib i s))))))

(defn fib
  "Compute the nth Fibonacci number"
  [n]
  (-> n fib-seq last))
