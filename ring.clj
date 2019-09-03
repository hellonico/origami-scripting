#!/usr/bin/env inlein

'{:dependencies [[org.clojure/clojure "1.10.1"]
											                 				[ring/ring-core "1.6.3"]
											                 				[ring/ring-json "0.5.0"]
           											      				[ring/ring-jetty-adapter "1.6.3"]]}

(require '[ring.adapter.jetty :refer [run-jetty]])
(require '[ring.middleware.json :refer [wrap-json-response]])

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/json"}
   :body {:time (java.util.Date.)}})

(def app
  (wrap-json-response handler))

(run-jetty  app {:port 3000})