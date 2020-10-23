#!/usr/bin/env inlein
'{:dependencies [[origami/sources "1.0.2"][origami/origami "4.3.0-8"][org.clojure/clojure "1.10.0"]]}

(require '[opencv4.core :refer :all]
         '[origami.lazyseqs :as lazy]
         '[clojure.java.io :as io])

(load-file "_mycartoons.clj")

(def in (or (first *command-line-args*) "."))
(def out (or (second *command-line-args*) "out"))
(.mkdirs (clojure.java.io/as-file out))
(def ext (or (nth *command-line-args* 2) "jpg"))

(defmacro doseq-indexed
  "loops over a set of values, binding index-sym to the 0-based index of each value"
  ([[val-sym values index-sym] & code]
   `(loop [vals# (seq ~values)
           ~index-sym (long 0)]
      (if vals#
        (let [~val-sym (first vals#)]
          ~@code
          (recur (next vals#) (inc ~index-sym)))
        nil))))

(let [zip (lazy/zip-seq in)]
  (doseq-indexed [z zip i]
    (print "Processing :" i )
    (print ".")
    (-> z clone cartoon-1 (imwrite (str out "/" i "_cartoon1_" (System/currentTimeMillis) "." ext)))
    (print ".")
    (-> z clone cartoon-2 (imwrite (str out "/" i "_cartoon2_" (System/currentTimeMillis) "." ext)))
    (print ".")
    (-> z clone cartoon-3 (imwrite (str out "/" i "_cartoon3_" (System/currentTimeMillis) "." ext)))
    (print ".")
    (-> z clone cartoon-4 (imwrite (str out "/" i "_cartoon4_" (System/currentTimeMillis) "." ext)))
    (print ".")
    (-> z clone cartoon-5 (imwrite (str out "/" i "_cartoon5_" (System/currentTimeMillis) "." ext)))
    (println ".")))