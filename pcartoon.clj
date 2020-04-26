#!/usr/bin/env inlein
'{:dependencies [[origami/origami "4.3.0-4"]]}

(require '[opencv4.core :refer :all]
         '[opencv4.process :as p]
         '[clojure.string :as str]
         '[clojure.java.io :as io])

(defn cartoon-1
  [buffer]
  (-> buffer
    (cvt-color! COLOR_BGR2GRAY)
    (bilateral-filter! 10 250 50)
    (adaptive-threshold! 255 ADAPTIVE_THRESH_MEAN_C THRESH_BINARY 9 3)
    (cvt-color! COLOR_GRAY2BGR)))

(defn cartoon-2
   [buffer]
   (-> buffer
     (cvt-color! COLOR_BGR2GRAY)
     (bilateral-filter! 9 17 7)
     (adaptive-threshold! 255 ADAPTIVE_THRESH_MEAN_C THRESH_BINARY 9 3)
     (cvt-color! COLOR_GRAY2BGR)))

(defn cartoon-3 [in]
  (-> in
  (cvt-color! COLOR_BGR2GRAY)
  (gaussian-blur! (new-size 3 3) 1 1)
  (canny! 100.0 250.0 3 true)
  (bitwise-not!)
  (cvt-color! COLOR_GRAY2BGR)))


(defn cartoon-4 [in]
  (-> in
  (cvt-color! COLOR_BGR2GRAY)
  (gaussian-blur! (new-size 3 3) 1 1)
  (canny! 50.0 150.0 3 true)
  (bitwise-not!)
  (cvt-color! COLOR_GRAY2BGR)))

(defn process [_fn ind out file]
    (println file)
    (-> file 
        (imread) 
        _fn
        (imwrite (str out "/cartoon" ind "_" (.getName (io/as-file file))))))

(def in (or (first *command-line-args*) "."))
(def out (or (second *command-line-args*) "out"))

; (.start (Thread. (fn[] (p/sequential in out (partial process cartoon-1 1)))))
(.start (Thread. (fn[] (p/sequential in out (partial process cartoon-2 2)))))
; (.start (Thread. (fn[] (p/sequential in out (partial process cartoon-3 3)))))
; (.start (Thread. (fn[] (p/sequential in out (partial process cartoon-4 4)))))
