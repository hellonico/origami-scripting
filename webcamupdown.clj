#!/usr/bin/env inlein

'{:dependencies [[org.clojure/clojure "1.8.0"][origami/origami "4.7.0-18"]]}

(ns opencv4.webcam
  (:require
    [opencv4.core :refer :all]
    [opencv4.utils :as u]))

 (u/simple-cam-window 
   (fn [buffer]
   ; (u/resize-by buffer 0.4)
   (let [ output (new-mat) bottom (-> buffer clone (flip! -1)) ]
    (-> buffer (cvt-color! COLOR_RGB2GRAY) (cvt-color! COLOR_GRAY2RGB))
    (put-text buffer (str (java.util.Date.)) (new-point 10 50) FONT_HERSHEY_PLAIN 1 (new-scalar 255 255 0) 1)
    (vconcat [buffer bottom] output)
    output)))