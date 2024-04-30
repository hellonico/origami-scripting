#!/usr/bin/env inlein

'{:dependencies [
  [org.clojure/clojure "1.10.0"]
  [origami/origami "4.9.0-0"]
  [origami/filters "1.43"]]}

(ns opencv4.webcam
  (:require
    [opencv4.utils :as u]))

(defn java-filter [klass]
    (let [fi (.newInstance klass)]
      (fn [mat] (.apply fi mat))))

(def f 
  (java-filter origami.filters.Cartoon))
; (def f 
;   (java-filter origami.filters.SunGlasses$Red))

(u/simple-cam-window 
    (comp (java-filter origami.filters.FPS) f ))