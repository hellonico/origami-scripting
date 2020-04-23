#!/usr/bin/env inlein

'{:dependencies [[org.clojure/clojure "1.8.0"][[origami/origami "4.3.0-4"]]]}

(require '[opencv4.core :refer :all])
(require '[opencv4.utils :as u])

(-> (first *command-line-args*) 
      (u/mat-from-url)
      (imwrite "cat.jpg"))