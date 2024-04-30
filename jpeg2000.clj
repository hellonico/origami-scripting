#!/usr/bin/env inlein

'{:dependencies [[origami/origami "4.7.0-18"]]}
(require '[opencv4.core :refer [imread imwrite]])

(-> (or (first *command-line-args*) "cat.jpg")
      (imread)
      (imwrite "output.hdp"))