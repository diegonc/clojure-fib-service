(ns fib-service.fibonacci-test
  (:require [clojure.test :refer :all]
            [fib-service.fibonacci :refer [fib fib-seq]]))

(defn log [x]
  (do (println x) x))

(deftest test-fib
  (testing "fib of 0 must be 1"
    (let [n (log (fib 0))]
      (is (= n 0))))

  (testing "fib of 1 must be 1"
    (let [n (log (fib 1))]
      (is (= n 1))))

  (testing "fib of 6 must be 8 (recursive case)"
    (let [n (log (fib 6))]
      (is (= n 8))))

  (testing "fib of 100 must be 354224848179261915075 (deep recursion)"
    (let [n (log (fib 100))]
      (is (= n 354224848179261915075))))

  (testing "fib of an absurdly big amount (show runtime)"
    (let [n (log (time (fib 100000)))]
      ; whatever you say is ok
      (is (= n n)))))

(deftest test-fib-seq
  (testing "fib-seq of 0 must be [0]"
    (let [arr (fib-seq 0)]
      (is (= arr [0]))))

  (testing "fib-seq of 6 must be [0 1 1 2 3 5 8]"
    (let [arr (fib-seq 6)]
      (is (= arr [0 1 1 2 3 5 8])))))
