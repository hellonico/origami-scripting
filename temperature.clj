#!/usr/bin/env inlein

'{:dependencies [[org.clojure/clojure "1.10.1"]]}

(defn to-fahrenheit [k] 
  (+ (* k 1.8) 32))

(-> *command-line-args* first (#(Integer/parseInt %)) to-fahrenheit println)