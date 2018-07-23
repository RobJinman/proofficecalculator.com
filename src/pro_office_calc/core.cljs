(ns pro-office-calc.core
  (:require-macros
   [pro-office-calc.macros :refer [code-critic]])
  (:require
   [goog.dom :as gdom]
   [reagent.core :as reagent]
   [re-frame.core :refer [dispatch-sync]]
   [pro-office-calc.config :refer [app-config]]
   [pro-office-calc.view :as view]))

(defn ^:export run
  []
  (dispatch-sync [:initialise])
  (reagent/render [view/main-view app-config]
                  (gdom/getElement "pro-office-calc-root")))
