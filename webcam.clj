#!/usr/bin/env inlein

'{:dependencies [[org.clojure/clojure "1.8.0"][origami/origami "4.9.0-0"]]}

(ns opencv4.webcam
  (:require
    [opencv4.core :refer :all]
    [opencv4.utils :as u]))

 (u/simple-cam-window identity)