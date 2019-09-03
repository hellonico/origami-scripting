#!/usr/bin/env inlein
'{:dependencies [[origami/origami "4.1.1-3"]]}

(require '[opencv4.core :refer [VERSION]]) 
(println "Using OpenCV Version: " VERSION "...")