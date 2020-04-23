#!/usr/bin/env inlein

'{:dependencies [[org.clojure/clojure "1.10.0"][progrock "0.1.2"][origami/origami "4.3.0-4"]]}
(require '[opencv4.core :as cv ])
(require '[opencv4.video :as cvv ])
(require '[progrock.core :as pr])

(def output-file (or (first *command-line-args*)  "timelapse.mp4"))
(def input (or (first *command-line-args*)  "out"))

(def files  
  (sort-by #(.lastModified %) (file-seq (clojure.java.io/as-file input))))


(def  fourcc 1196444237)
(def w (cvv/new-videowriter))

(println "Video Start > " (java.util.Date.))

(let [pic (cv/imread (.getPath (first files))) _size (cv/new-size (.width pic ) (.height pic) )]
(.open w output-file  fourcc 12 _size))

(loop [files files bar (pr/progress-bar (count files))]
(if (empty? files) 
(do (.release w) (pr/print (pr/done bar)))
(let [path (.getPath (first files)) img (cv/imread path)]
	(.write w img)
	(pr/print bar)
	(.release img)
	(recur (rest files) (pr/tick bar)))))

(println "Video Finished > " (java.util.Date.))