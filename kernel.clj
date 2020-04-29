#!/usr/bin/env inlein
'{:dependencies [[origami/origami "4.3.0-6"]]}

(require '[opencv4.core :refer :all]) 

(def kernel (get-structuring-element MORPH_RECT (new-size 11 11)))

(-> (or  (first *command-line-args*)  "cat.jpg")
  (imread CV_8UC1)
  (dilate! kernel)
  (imwrite "kernel.png"))