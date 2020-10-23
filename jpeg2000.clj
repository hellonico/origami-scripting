#!/usr/bin/env inlein

'{:dependencies [[origami/origami "4.5.0"]]}
(require '[opencv4.core :refer [imread imwrite]])

(-> (first *command-line-args*) 
      (imread)
      (imwrite "output.hdp"))