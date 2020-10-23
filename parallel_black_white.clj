#!/usr/bin/env inlein
'{:dependencies [[origami/origami "4.5.0"]]}

;
; This scripts converts all pictures of a folder to black and white
; in parallel

(require '[opencv4.core :refer [imread imwrite CV_8UC1]] 
																'[opencv4.process :as p]
         						'[clojure.java.io :as io] )

(defn process  [out file]
        (-> file 
            (imread CV_8UC1) 
            (imwrite (str out "/bw_" (.getName (io/as-file file))))))

(p/parallel 
    (or (first *command-line-args*) ".")
    (or (second *command-line-args*) "out")
    process)