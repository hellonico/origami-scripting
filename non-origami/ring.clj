#!/usr/bin/env inlein

'{:dependencies [[org.clojure/clojure "1.10.1"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-json "0.5.0"]
                 [ring/ring-jetty-adapter "1.6.3"]]}

(require '[ring.adapter.jetty :refer [run-jetty]])
(require '[ring.middleware.json :refer [wrap-json-response]])
(use 'ring.middleware.resource
     'ring.middleware.content-type
     'ring.middleware.not-modified)

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/json"}
   :body {:time (java.util.Date.)}})

(def app
  (-> handler
      (wrap-json-response)
      (wrap-resource (or (first *command-line-args*) "."))
      (wrap-content-type)
      (wrap-not-modified)))

(run-jetty  app {:port 3000})