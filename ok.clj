#!/usr/bin/env inlein
'{:jvm-opts ["-XX:+TieredCompilation" "-XX:TieredStopAtLevel=1" "-Xverify:none"]
	 :dependencies [[origami/origami "4.1.1-6"]]}

(require '[opencv4.core :refer [VERSION]]) 
(println "Using OpenCV Version: " VERSION "...")