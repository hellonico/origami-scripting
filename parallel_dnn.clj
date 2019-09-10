#!/usr/bin/env inlein
'{:dependencies [[origami-dnn "0.1.5-SNAPSHOT"]]}

;
; This scripts runs a DNN on all pictures in a folder
; in parallel
(require 
   '[origami-dnn.net.yolo :as yolo]
   '[origami-dnn.draw :as d]
   '[opencv4.process :as p]
   '[opencv4.core :refer :all]
   '[clojure.java.io :as io]
   '[origami-dnn.core :as origami-dnn])

; make sure the network is downloaded 
; before the parallel processing
(def network "networks.yolo:yolov2-tiny:1.0.0")
(origami-dnn/read-net-from-repo network)

(defn my-dnn-fn [ out input ]
(let [[net opts labels] (origami-dnn/read-net-from-repo network)
      output (str out "/net_" (.getName (io/as-file input))) ]
    (-> input
        (imread)
        (yolo/find-objects net)
        (d/blue-boxes! labels)
        (imwrite output))))

(p/parallel 
    (or (first *command-line-args*) ".")
    (or (second *command-line-args*) "out")
    my-dnn-fn)