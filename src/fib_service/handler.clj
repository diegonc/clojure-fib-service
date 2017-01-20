(ns fib-service.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :refer [wrap-json-response]]
            [fib-service.fibonacci :refer [fib fib-seq]]))

(defroutes app-routes
  (GET "/fib/:n" [n] {:body {:data (fib (Integer/valueOf n))}})
  (GET "/fib-seq/:n" [n] {:body {:data (fib-seq (Integer/valueOf n))}})
  (route/not-found {:body {:error "Not Found"}}))

(def app
  (-> app-routes
      wrap-json-response
      (wrap-defaults api-defaults)))
