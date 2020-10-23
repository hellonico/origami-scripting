#!/usr/bin/env inlein
'{:dependencies [[org.clojure/clojure "1.8.0"][origami/origami "4.5.0"]]}

(require '[opencv4.core :refer :all])
(require '[opencv4.utils :as u])


(let [
  in   (first *command-line-args*)  
  fd (clojure.java.io/as-file in)
  out (str "bw_" (.getName fd))]
  (println in ">" out)
  (-> in
  	 imread 
  	 (cvt-color! COLOR_RGB2GRAY)
  	 (gaussian-blur! (new-size 7 7) 1.5 1.5)
  	 (threshold! 100 255 THRESH_BINARY)
	 (imwrite out)))