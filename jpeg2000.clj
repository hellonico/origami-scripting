#!/usr/bin/env inlein

'{:dependencies [[origami/origami "4.3.0-4"]]}
(require '[opencv4.core :refer [imread imwrite]])

(-> (first *command-line-args*) 
      (imread)
      (imwrite "output.hdp"))