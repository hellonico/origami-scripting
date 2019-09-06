#!/usr/bin/env inlein

'{:dependencies [[origami-dnn "0.1.2"]]}

(require '[origami-dnn.net.mobilenet :refer [find-objects]]
          '[origami-dnn.core :refer [read-net-from-uri]]
          '[origami-dnn.draw :as d]
          '[opencv4.utils :refer [simple-cam-window]])

(let [ [net opts labels] (read-net-from-uri "http://repository.hellonico.info/repository/hellonico/origami-dnn-networks/mobilenet/1.0.0/mobilenet-1.0.0.zip") ]
  (simple-cam-window
   (fn [buffer]
     (-> buffer 
      (find-objects net opts) 
      (d/red-boxes! labels)))))