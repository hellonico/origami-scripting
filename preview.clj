#!/usr/bin/env inlein
'{:dependencies [[origami/origami "4.1.1-4"]]}

(require '[opencv4.utils :as u]) 
(-> *command-line-args* first  opencv4.core/imread (#(u/resize-by  % 0.15)) u/imshow)