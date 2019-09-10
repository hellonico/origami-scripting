#!/usr/bin/env inlein

'{:dependencies [[origami-dnn "0.1.5"]]}

(ns dnn.picture
 (:require 
    [origami-dnn.net.yolo :as yolo]
    [origami-dnn.draw :as d]
    [opencv4.core :refer [put-text! new-scalar FONT_HERSHEY_PLAIN rectangle new-point min-max-loc new-size new-scalar imread imwrite]]
    [origami-dnn.core :as origami-dnn]))

(defn blue-boxes! [result labels]
  (let [img (first result) detected (second result)]
  (doseq [{confidence :confidence label :label box :box} detected]
    (put-text! img 
      (str (nth labels label) "[" confidence " %]") 
    		(new-point (double (.-x box)) (double (.-y box))) FONT_HERSHEY_PLAIN 2 (new-scalar 255 0 0))
    (rectangle img box (new-scalar 255 0 0) 2))
  img))

(defn run-yolo [ input output]
(let [[net opts labels] (origami-dnn/read-net-from-repo "networks.yolo:yolov2-tiny:1.0.0")]
    (println "Running yolo on image:" input " > " output)
    (-> input
        (imread)
        (yolo/find-objects net)
        ; (d/blue-boxes! labels)
        (blue-boxes! labels)
        (imwrite output))))

 (run-yolo 
  (or   (first *command-line-args*)  "marcel.jpg")
  (or   (second *command-line-args*) "yolo_v3_output.jpg"))