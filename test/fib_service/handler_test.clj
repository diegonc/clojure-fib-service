(ns fib-service.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [fib-service.handler :refer :all]))

(deftest test-app
  (testing "fib 6"
    (let [response (app (mock/request :get "/fib/6"))]
      (is (= (:status response) 200))
      (is (= (:body response) "{\"data\":8}"))))

  (testing "fib-seq 6"
    (let [response (app (mock/request :get "/fib-seq/6"))]
      (is (= (:status response) 200))
      (is (= (:body response) "{\"data\":[0,1,1,2,3,5,8]}"))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))
