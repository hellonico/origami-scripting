#!/usr/bin/env inlein
'{:dependencies [[org.clojure/clojure "1.10.0"][origami "4.3.0-4"]]}

(require '[opencv4.utils :as u]) 
(-> *command-line-args* first  opencv4.core/imread (#(u/resize-by  % 0.15)) u/imshow)