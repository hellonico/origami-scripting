#!/usr/bin/env inlein

'{:dependencies [[org.clojure/clojure "1.10.0"][origami/origami "4.3.0-6"]]}
(require '[opencv4.utils :as u])
(require '[opencv4.core :refer [imwrite] ])

(def interval (* 1000 (or (first *command-line-args*)  3)))
(def output (or (second *command-line-args*) "out"))
(.mkdirs (clojure.java.io/as-file output))

(println "Timelapse Interval > " interval " ms")
(println "Folder > " output)

(defn take-one-wait [buffer]
 (imwrite buffer (str output "/" (java.util.Date.) ".png") )
	(Thread/sleep interval)	
	buffer)

(u/simple-cam-window 
 take-one-wait)

(Thread/sleep Long/MAX_VALUE)