#!/usr/bin/env inlein

'{:dependencies [[origami-dnn "0.1.4"]]}

(ns demo.yolo.cam
  (:require   [origami-dnn.net.yolo :as yolo]
                     [origami-dnn.draw :as d]
                     [origami-dnn.core :as origami-dnn]
                     [opencv4.utils :as u]))

   (let [[net _ labels] (origami-dnn/read-net-from-repo "networks.yolo:yolov3-tiny:1.0.0")]
			  (u/simple-cam-window
			  {:frame {:fps true}}
			   (fn [buffer]
			     (-> buffer 
						     ; (u/resize-by 0.5)
						     (yolo/find-objects net) 
						     (d/blue-boxes! labels) ))))