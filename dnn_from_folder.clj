#!/usr/bin/env inlein

'{:dependencies [[org.clojure/clojure "1.10.0"][environ "1.1.0"][origami-dnn "0.1.8"]]}

(require '[origami-dnn.net.mobilenet :refer [find-objects]]
          '[opencv4.dnn.core :as dnn]
          '[origami-dnn.draw :as d]
          '[environ.core :refer [env]]
          '[opencv4.utils :refer [simple-cam-window]])

; make sure the network is downloaded before running
; (def network "networks.tensorflow:tf-ssdmobilenet:1.0.0")
(def network "networks.caffe:mobilenet:1.0.0")
(dnn/read-net-from-repo network)

(let [ [net opts labels] (dnn/read-net-from-folder (str (env :user-home) "/.origami/mobilenet-1.0.0.zip")) ]
  (simple-cam-window
  {:frame {:fps true}}
   (fn [buffer]
     (-> buffer 
      (find-objects net opts) 
      (d/red-boxes! labels)))))