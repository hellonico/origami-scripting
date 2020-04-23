#!/usr/bin/env inlein

'{:dependencies [[org.clojure/clojure "1.10.0"][origami/origami "4.3.0-4"]]}
(require '[opencv4.core :as cv ])
(require '[opencv4.video :as cvv ])

(def output-file (or (first *command-line-args*)  "timelapse.mp4"))
(def input (or (first *command-line-args*)  "out"))

(def files  
  (sort-by #(.lastModified %) (file-seq (clojure.java.io/as-file input))))
(def  fourcc 1196444237)
(def w (cvv/new-videowriter))

(println "Start Video > " (java.util.Date.))

(let [pic (cv/imread (.getPath (first files))) _size (cv/new-size (.width pic ) (.height pic) )]
(.open w output-file  fourcc 12 _size))
(doseq [f files]
	(.write w (cv/imread (.getPath f))))
(.release w)

(println "Video Finished > " (java.util.Date.))