#!/usr/bin/env inlein

'{:dependencies [[origami "4.7.0-18"]]}

(require '[opencv4.utils :as u]) 

(-> 
	*command-line-args* 
	first  
	opencv4.core/imread 
	(#(u/resize-by  % 0.15)) 
	u/imshow)