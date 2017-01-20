(ns fib-service.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.coercions :refer [as-int]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.middleware.cors :refer [wrap-cors]]
            [fib-service.fibonacci :refer [fib fib-seq]]))

(defn- do-fib-seq
  "Call fib-seq and apply the optional modulo operation"
  [n modulo]
  (if (nil? modulo)
    (fib-seq n)
    (map #(mod % modulo) (fib-seq n))))

(defroutes app-routes
  (GET "/fib/:n"
       [n :<< as-int]
       {:body {:data (fib n)}})
  (GET "/fib-seq/:n"
       [n :<< as-int :as request]
       (let [modulo (-> request
                        :params
                        :modulo
                        as-int)]
         {:body {:data (do-fib-seq n modulo)}}))
  (route/not-found {:body {:error "Not Found"}}))

(def app
  (-> app-routes
      wrap-json-response
      (wrap-cors :access-control-allow-origin [#".*"]
                 :access-control-allow-methods [:get])
      (wrap-defaults api-defaults)))
