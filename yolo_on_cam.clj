#!/usr/bin/env inlein

'{:dependencies [[org.clojure/clojure "1.10.0"][origami-dnn "0.1.8"]]}

(require   '[origami-dnn.net.yolo :as yolo]
		   '[origami-dnn.draw :as d]
           '[opencv4.dnn.core :as dnn]
           '[opencv4.utils :as u])

 (let [[net _ labels] (dnn/read-net-from-repo "networks.yolo:yolov3-tiny:1.0.0")]
	(u/simple-cam-window
	{:frame {:fps true}}
	(fn [buffer]
		(-> buffer 
	(u/resize-by 0.5)
	(yolo/find-objects net) 
	(d/blue-boxes! labels) ))))