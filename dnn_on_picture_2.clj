#!/usr/bin/env inlein

'{:dependencies [[origami-dnn "0.1.7"]]}

(ns dnn.picture
 (:require 
    [origami-dnn.net.yolo :as yolo]
    [origami-dnn.draw :as d]
    [opencv4.core :refer :all]
    [opencv4.utils :as u]
    [opencv4.dnn.core :as origami-dnn]))

(defn result! [result labels]
  (let [img (first result) detected (second result)]
   (map #(let [{confidence :confidence label :label box :box} %]
      {:label (nth labels label) :confidence confidence })detected)))

(defn run-yolo [ input ]
(let [[net opts labels] (origami-dnn/read-net-from-repo "networks.yolo:yolov2-tiny:1.0.0")]
    (println "Running yolo on image:" input " > ")
    (-> input
        ;(u/mat-from-url)
        imread
        (yolo/find-objects net)
        (result! labels))))

(println (run-yolo  (or (first *command-line-args*) "marcel.jpg" )))