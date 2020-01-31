#!/usr/bin/env inlein

'{:dependencies [[org.clojure/clojure "1.8.0"][origami/origami "4.2.0-1"]]}
  
 (require '[opencv4.core :refer :all])
 (import '[org.opencv.core Mat])

(dump (Mat/eye 3 3 CV_8UC1))