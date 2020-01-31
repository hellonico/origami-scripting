#!/usr/bin/env inlein

'{:dependencies [[origami/origami "4.2.0-1"]]}
(require '[opencv4.core :refer [imread imwrite]])

(-> (first *command-line-args*) 
      (imread)
      (imwrite "output.hdp"))