(ns pro-office-calc.core
  (:require-macros
   [pro-office-calc.macros :refer [code-critic]])
  (:require
   [day8.re-frame.http-fx]
   [goog.dom :as gdom]
   [pro-office-calc.config :refer [app-config]]
   [pro-office-calc.cofx]
   [pro-office-calc.fx]
   [pro-office-calc.events]
   [pro-office-calc.subs]
   [pro-office-calc.utils]
   [pro-office-calc.view :as view]
   [re-frame.core :refer [dispatch-sync]]
   [reagent.core :as reagent]))

(extend-type js/NodeList
  ISeqable
  (-seq [array] (array-seq array 0)))

(extend-type js/HTMLCollection
  ISeqable
  (-seq [array] (array-seq array 0)))

(defn ^:export run
  []
  (dispatch-sync [:initialise])
  (reagent/render [view/main-view app-config]
                  (gdom/getElement "pro-office-calc-root")))
