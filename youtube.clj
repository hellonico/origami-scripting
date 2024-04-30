#!/usr/bin/env inlein

'{:dependencies [[org.clojure/clojure "1.8.0"][origami/youtube "1.0.0"][origami/origami "4.7.0-18"]]}

(ns opencv4.webcam
  (:require
    [opencv4.core :refer :all]
    [opencv4.utils :as u]))
; (import '[origami.video YouTubeHandler])
; (YouTubeHandler/main (into-array String []))

; (import '[origami.utils Downloader])
; (Downloader/debug)

(Class/forName "origami.video.YouTubeHandler")
(u/simple-cam-window 
 {:video {:device (str "youtube://" (or (first *command-line-args*)  "6t24nX_sak8")) }} identity)
