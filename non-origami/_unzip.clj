;#!/usr/bin/env inlein

;'{:dependencies [[me.raynes/fs "1.4.6"][org.clojure/clojure "1.10.0"]]}

(require '[me.raynes.fs.compression :as fs])

(defn unzip[file]
     (let[ out (str file ".extract")] 
     (.mkdirs (clojure.java.io/as-file out))
     (fs/unzip file out )
     out
     ))