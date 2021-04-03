#!/usr/bin/env inlein

'{:dependencies [[org.clojure/clojure "1.8.0"][origami/origami "4.5.1-3"]]}

(ns cat-detector
  (:require
    [opencv4.colors.rgb :as rgb]
    [opencv4.core :refer :all]))

(def detector
  (->
   "haarcascade_frontalcatface.xml"
   (new-cascadeclassifier)))

(defn add-label! [buffer rects]
  (put-text! buffer (str (count (.toArray rects) ) " cat(s) " )
     (new-point 30 100) FONT_HERSHEY_PLAIN 2 rgb/magenta-2 2))

(defn draw-rects! [buffer rects]
   (doseq [r (.toArray rects)]
     (-> buffer
      (submat r)
      (apply-color-map! COLORMAP_WINTER)
      (copy-to (submat buffer r))))
      buffer)

(defn detect-cats! [mat]
  (let [  rects (new-matofrect) ]
   (.detectMultiScale detector mat rects)
   (-> mat
     (draw-rects! rects)
     (add-label! rects))))

 (-> (or (first *command-line-args*)  "cat.jpg")
   (imread)
   (detect-cats!)
   (imwrite (or (second *command-line-args*) "detected_cat.jpg" )))
