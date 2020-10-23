
(require '[opencv4.core :refer :all])


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
  (gaussian-blur! (new-size 5 5) 1 1)
  (canny! 150.0 255.0 3 true)
  (bitwise-not!)
  (cvt-color! COLOR_GRAY2BGR)))


(defn cartoon-4 [in]
  (-> in
  (cvt-color! COLOR_BGR2GRAY)
  (gaussian-blur! (new-size 3 3) 1 1)
  (canny! 150.0 200.0 3 true)
  (bitwise-not!)
  (cvt-color! COLOR_GRAY2BGR)))

(defn cartoon-5[in]
  (-> (pencil-sketch! in (new-mat))))
