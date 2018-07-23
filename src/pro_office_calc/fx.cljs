(ns pro-office-calc.fx
  (:require
   [re-frame.core :refer [reg-fx]]))

(reg-fx
 :do-side-effect
 (fn [{:keys [arg1 arg2]}]
   (do)))
