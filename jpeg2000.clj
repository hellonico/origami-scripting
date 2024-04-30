#!/usr/bin/env inlein

'{:dependencies [[origami/origami "4.9.0-0"]]}
(require '[opencv4.core :refer [imread imwrite]])

(-> (or (first *command-line-args*) "cat.jpg")
      (imread)
      (imwrite "output.hdp"))