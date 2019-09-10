#!/usr/bin/env inlein

'{:dependencies [[origami-dnn "0.1.5"]]}

(require '[origami-dnn.net.mobilenet :refer [find-objects]]
          '[origami-dnn.core :refer [read-net-from-folder]]
          '[origami-dnn.draw :as d]
          '[opencv4.utils :refer [simple-cam-window]])

(let [ [net opts labels] (read-net-from-folder"squeezenet_2018_04_27") ]
  (simple-cam-window
  {:frame {:fps true}}
   (fn [buffer]
     (-> buffer 
      (find-objects net opts) 
      (d/red-boxes! labels)))))