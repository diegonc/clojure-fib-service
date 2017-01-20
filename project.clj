(defproject fib-service "0.1.0-SNAPSHOT"
  :description "Fibonacci Sequence as a Service"
  :url "http://github.com/diegonc/clojure-fib-service"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-json "0.4.0"]
                 [ring-cors "0.1.9"]
                 [proto-repl "0.3.1"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler fib-service.handler/app}
  :profiles
  {:dev {:source-paths ["dev" "src" "test"]
         :dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
