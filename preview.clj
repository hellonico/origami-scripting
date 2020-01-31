#!/usr/bin/env inlein
'{:dependencies [[org.clojure/clojure "1.10.0"][origami "4.2.0-1"]]}

(require '[opencv4.utils :as u]) 
(-> *command-line-args* first  opencv4.core/imread (#(u/resize-by  % 0.15)) u/imshow)