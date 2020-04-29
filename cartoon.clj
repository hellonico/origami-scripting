#!/usr/bin/env inlein

'{:dependencies [[org.clojure/clojure "1.8.0"][origami/origami "4.3.0-6"]]}

(require '[opencv4.core :refer :all])
(require '[opencv4.utils :as u])

(def in (first *command-line-args*) )
(println in)
(-> in
  imread
  (cvt-color! COLOR_BGR2GRAY)
  (gaussian-blur! (new-size 1 1) 1 1)
  (canny! 100.0 220.0 3 true)
  (bitwise-not!)
  (imwrite (str "cartoon_" in)))