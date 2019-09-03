#!/usr/bin/env inlein

'{:dependencies [[org.clojure/clojure "1.8.0"][origami/origami "4.1.1-3"]]}

(require '[opencv4.core :refer :all])
(require '[opencv4.utils :as u])

(->
  (first *command-line-args*) 
  (u/mat-from-url)
  (u/resize-by 0.3)
  (cvt-color! COLOR_BGR2GRAY)
  (gaussian-blur! (new-size 1 1) 1 1)
  (canny! 100.0 220.0 3 true)
  (bitwise-not!)
  (u/show))