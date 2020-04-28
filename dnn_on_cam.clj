#!/usr/bin/env inlein

'{:dependencies [[origami-dnn "0.1.8"]]}

(require '[origami-dnn.net.mobilenet :refer [find-objects]]
          '[opencv4.dnn.core :as dnn]
          '[origami-dnn.draw :as d]
          '[opencv4.utils :refer [simple-cam-window]])

(let [ [net opts labels] (dnn/read-net-from-repo "networks.tensorflow:tf-ssdmobilenet:1.0.0") ]
  (simple-cam-window
  {:frame {:fps true}}
   (fn [buffer]
     (-> buffer 
      (find-objects net opts) 
      (d/red-boxes! labels)))))