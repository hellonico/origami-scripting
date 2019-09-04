#!/usr/bin/env inlein
'{:dependencies [[origami/origami "4.1.1-4-SNAPSHOT"]]}

(require '[opencv4.core :refer :all]) 

(def kernel (get-structuring-element MORPH_RECT (new-size 11 11)))

(-> "cat.jpg"
  (imread CV_8UC1)
  (dilate! kernel)
  (imwrite "kernel.png"))