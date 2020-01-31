#!/usr/bin/env inlein
'{:dependencies [[neural-style "0.1.0-SNAPSHOT"]]}

; Before using
; clone https://github.com/hellonico/origami-mxnet
; and do lein install 

(require '[neural-style.core :refer [-main] ])
(.mkdirs (clojure.java.io/as-file "output"))
(-main 
	(or (first *command-line-args*)  "cat.jpg")
	(or (second *command-line-args*)  "paul-klee.jpg")
	:cpu
	(-> (Runtime/getRuntime) (.availableProcessors) str))