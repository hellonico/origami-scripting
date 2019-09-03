#!/usr/bin/env inlein

'{:dependencies [[org.clojure/clojure "1.8.0"]]}
  
  (def fib-seq-seq
  ((fn fib [a b] 
     (lazy-seq (cons a (fib b (+' a b)))))
   0 1))

(-> *command-line-args*
       first
       (#(Integer/parseInt %))
      (take fib-seq-seq)
      (last)
      (println))