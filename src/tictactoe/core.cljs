(ns tictactoe.core
    (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(defn new-board [n]
  (vec (repeat n (vec (repeat n 0)))))

(defonce app-state 
  (atom {:text "Welcome to tic tac toe"
         :board (new-board 3)}))

(prn (new-board 3))
(prn (count (:board @app-state)))

(defn tictactoe []
  [:center
    [:h1 (:text @app-state)]
    (into
      [:svg
        {:view-box "0 0 3 3"
        :width 500
        :height 500}]
      (for [i (range (count (:board @app-state)))
            j (range (count (:board @app-state)))]
        [:rect {:width 0.9
                :height 0.9
                :fill (if (zero? (get-in @app-state [:board j i])) "green" "yellow")
                :x i
                :y j
                :on-click (fn [e] 
                              (prn "You clicked me !" i j)
                              (prn (swap! app-state update-in [:board j i] inc)))}]))])

(reagent/render-component [tictactoe]
                          (. js/document (getElementById "app")))

(defn on-js-reload [])
