#!/usr/bin/env inlein
'{:jvm-opts ["-XX:+TieredCompilation" "-XX:TieredStopAtLevel=1" "-Xverify:none"]
  :dependencies [[org.clojure/clojure "1.10.0"][origami "4.3.0-6"]]}

(require '[opencv4.core :refer [VERSION]]) 
(println "Using OpenCV Version: " VERSION "...")