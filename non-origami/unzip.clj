#!/usr/bin/env inlein

'{:dependencies [[me.raynes/fs "1.4.6"][org.clojure/clojure "1.10.0"]]}

(require '[me.raynes.fs.compression :as fs])

(def file (first *command-line-args*))
(def out (str file ".extract"))
(.mkdirs (clojure.java.io/as-file out))
(fs/unzip file out )
