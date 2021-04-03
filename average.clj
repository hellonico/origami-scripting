#!/usr/bin/env inlein

'{:dependencies [[org.clojure/clojure "1.8.0"][origami/origami "4.5.1-3"]]}

(require '[opencv4.core :refer :all])
(require '[opencv4.utils :as u])

(defn average-color[src]
  (let [mean_  (-> src u/mat-from (set-to! (mean src))) ]
    (-> src
        (u/mat-from)
        (set-to! (mean src))
        (cons [src])
        (vconcat!)  )))

(-> 
  (first *command-line-args*) 
  (u/mat-from-url)
  (u/resize-by 0.25)
  average-color 
  (imwrite "average.jpg"))
