#!/usr/bin/env inlein

'{:jvm-opts ["-Dclojure.compiler.direct-linking=true" "-XX:MaxDirectMemorySize=16g"]
	  :dependencies [ [uncomplicate/neanderthal "0.25.6"][org.slf4j/slf4j-nop "1.7.28"]]}

;
; export DYLD_LIBRARY_PATH=/opt/intel//compilers_and_libraries_2019.4.233/mac/compiler/lib:/opt/intel/compilers_and_libraries_2019.4.233/mac/mkl/lib/
;

 (use '[uncomplicate.neanderthal core native])

(require '[uncomplicate.commons.core :refer [with-release release]]
         							'[uncomplicate.fluokitten.core :refer [fmap!]]
         							 '[uncomplicate.neanderthal
                     [native :refer [fge fv]]
                     [math :refer [sqrt]]] )

; dot 
(def x (dv 1 2 3))
(def y (dv 10 20 30))
(time 
  (println  (dot x y)))

; multiply 
(def a (dge 3 2 [1 2 3 4 5 6]))
(def b (dge 2 3 [10 20 30 40 50 60]))
(time 
  (println (mm a b)))


(def a (dge 2 3 [1 2 3 4 5 6]))
(def b (dge 3 2 [1 3 5 7 9 11]))
;; ... and multiply them
(time 
  (println
    (mm a b)))

; performance

(def n 100000)
(def cvx (vec (range n)))
(def cvy (vec (range n)))
(time  (println (reduce + (map * cvx cvy))))

(time (println (reduce + cvx)))

 (require '[uncomplicate.fluokitten.core :refer [fmap! fmap foldmap fold]])

(def cax (double-array (range n)))
(def cay (double-array (range n)))

(defn p+ ^double [^double x ^double y]
  (+ x y))

(defn p* ^double [^double x ^double y]
  (* x y))

(time (println (foldmap p+ 0.0 p* cax cay)))


(time (println
(let [a (dge 2 2 [4 2 2 3])
      p (dv 1 0)
      q (dv 1 1)
      r (dv 0 1)
      o (dv 0 0)]
  [(mv a p) (mv a q) (mv a r) (mv a o)])))

(time (println 
(let [a (dge 2 2 [4 2 2 3])
      square (dge 2 4 [1 0 1 1 0 1 0 0])]
  (mm a square))))

;
; https://dragan.rocks/articles/18/Neanderthal-vs-ND4J-vol5
; 
(import  '[java.util SplittableRandom])
(let [splittable-random (SplittableRandom.)]
  (defn random ^double [^double _]
    (.nextDouble ^SplittableRandom splittable-random)))

(defn neanderthal-distance
  ([n]
   (with-release [x (fmap! random (fv n))
                  y (fmap! random (fv n))]
     (println (sqrt (dot x y)))))
  ([m n]
   (with-release [x (fmap! random (fge m n))
                  y (fmap! random (fge m n))]
     (println (sqrt (dot x y))))))

(doseq [n [2 10 100]]
	(neanderthal-distance n))

(doseq [ [m n]  [[10 15] [100 100]]]
		(neanderthal-distance m n))
