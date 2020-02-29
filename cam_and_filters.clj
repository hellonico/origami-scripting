#!/usr/bin/env inlein

'{:dependencies [
  [org.clojure/clojure "1.8.0"]
  [origami/origami "4.2.0-1"]
  [origami/filters "1.8"]]}

(ns opencv4.webcam
  (:require
    ; [opencv4.core :refer :all]
    [opencv4.utils :as u]))

(defn java-filter [klass]
    (let [fi (.newInstance klass)]
      (fn [mat] (.apply fi mat))))

; (def f 
;   (cv-filter origami.filters.Cartoon))
(def f 
  (java-filter origami.filters.SunGlasses$Red))
(println f)

(u/simple-cam-window 
    (comp (java-filter origami.filters.FPS) f ))