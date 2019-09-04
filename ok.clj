#!/usr/bin/env inlein
'{:dependencies [[origami/origami "4.1.1-4"]]}

(require '[opencv4.core :refer [VERSION]]) 
(println "Using OpenCV Version: " VERSION "...")