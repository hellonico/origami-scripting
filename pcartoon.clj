#!/usr/bin/env inlein
'{:dependencies [[origami/origami "4.3.0-7"][org.clojure/clojure "1.10.0"][me.raynes/fs "1.4.6"]]}

(require '[opencv4.core :refer :all]
         '[opencv4.process :as p]
         '[clojure.string :as str]
         '[clojure.java.io :as io])

(load-file "_mycartoons.clj")
(load-file "non-origami/_unzip.clj")

(defn process [_fn ind out file]
    (println file)
    (-> file 
        (imread) 
        _fn
        (imwrite (str out "/cartoon" ind "_" (.getName (io/as-file file))))))


(def in (or (first *command-line-args*) "."))

(let [
    iin (if (clojure.string/includes? in "zip") (unzip in) in)
    out (or (second *command-line-args*) iin)
    ts [
        (Thread. (fn[] (p/sequential iin out (partial process cartoon-1 1))))
        (Thread. (fn[] (p/sequential iin out (partial process cartoon-2 2))))
        (Thread. (fn[] (p/sequential iin out (partial process cartoon-3 3))))
        (Thread. (fn[] (p/sequential iin out (partial process cartoon-4 4))))
    ]
]
 (doseq [t ts] (.start t)) (doseq [t ts] (.join t)))
