#!/usr/bin/env inlein

'{:dependencies [[progrock "0.1.2"][org.clojure/clojure "1.8.0"][origami/origami "4.3.0-6"]]}

(require '[opencv4.core :refer :all])
(require '[opencv4.utils :as u])
(require '[progrock.core :as pr])

(defn mean-average-bgr [mat]
  (let [_mean (new-matofdouble)]
  (-> mat clone
  (median-blur! 3)
  (mean-std-dev _mean (new-matofdouble)))
	_mean))

(defn collect-pictures
  ([top-folder] (collect-pictures top-folder "jpg"))
  ([top-folder ext]
  (->>
    top-folder
    clojure.java.io/as-file
    file-seq
    (filter #(.endsWith (.getName %) ext))
    (map #(.getPath %)))))

(defn indexing [files for-size]
  (zipmap files (map #(-> % imread (resize! for-size) mean-average-bgr) files)))

(defn apply-to-vals [m f]
  (into {} (for [[k v] m] [k (f v)])))

(defn find-closest [ target indexed ]
  (let [mean-bgr-target (mean-average-bgr target)]
     (first (sort-by val < (apply-to-vals indexed #(norm mean-bgr-target %))))))

(defn get-cache-image[cache path width height]
  (let[ hit (.get cache path) ]
  (if (not (nil? hit))
  hit
  (do
    (let [new-e (-> path
      imread
      (resize! (new-size width height)))]
      (.put cache path new-e)
      new-e)))))

(defn tile [org indexed ^long grid-x ^long grid-y]
  (let[
    dst (u/mat-from org)
    width (/ (.cols dst) grid-x)
    height (/ (.rows dst) grid-y)
    total (* grid-x grid-y)
    cache (java.util.HashMap.)
    ]
    (loop [i 0 bar (pr/progress-bar total)]
     (if (< i grid-y)
      (do
      (loop [j 0 _bar bar]
      (if (< j grid-x)
      (do
      (pr/print bar)
      (let [
        square (submat org (new-rect (* j width) (* i height) width height ))
        best (first (find-closest square indexed))
        img  (get-cache-image cache best  width height)
        sub (submat dst (new-rect (* j width) (* i height) width height ))
        ]
         (copy-to img sub))
         (recur (inc j) (pr/tick _bar)))))
         (recur (inc i) (pr/tick bar)))))
    dst))
;
; PHOTOMOSAIC
;

(defn photomosaic
  [images-folder target-image grid-x grid-y ]
  (let [files   (collect-pictures images-folder)
        indexed (indexing (collect-pictures images-folder) (new-size grid-x grid-y))
        target  (imread target-image )]
    (tile target indexed grid-x grid-y)))

;
; SCRIPT
;

(if (< (count *command-line-args*) 2) 
 (println "Usage: photomosaic.clj <in-folder> <in-image>")
 (imwrite 
 (photomosaic (first *command-line-args*) (second *command-line-args*) 100 100) 
 (str "photomosaic_" (java.util.Date.) ".jpg")))