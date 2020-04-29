#!/usr/bin/env inlein

'{:dependencies [[origami-dnn "0.1.9"]]}

(require  '[opencv4.dnn.core :as dnn]
          '[opencv4.colors.rgb :as rgb]
          '[origami-dnn.net.core :as net]
          '[opencv4.core :refer [new-point put-text! FONT_HERSHEY_PLAIN]]
          '[opencv4.utils :refer [simple-cam-window]])

(defn guess-gender [result labels h]
   (let [
     img (first result) 
     detected (second result) 
     {confidence :confidence label :label} detected 
     text (str (nth labels label) "[" (int (* 100 confidence)) " %]")]
     (put-text! img text (new-point 100 h) FONT_HERSHEY_PLAIN 4 rgb/white 4)))

(let [ 
  [net_ opts_ labels_] (dnn/read-net-from-repo "networks.caffe:convnet-age:1.0.0")
  [net opts labels] (dnn/read-net-from-repo "networks.caffe:convnet-gender:1.0.0")
  ]
  (simple-cam-window
   (fn [buffer]
     (-> buffer 
      (net/classify net opts)
      (guess-gender labels 100)
      (net/classify net_ opts_)
      (guess-gender labels_ 200 )))))
