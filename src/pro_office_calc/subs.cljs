(ns pro-office-calc.subs
  (:require
   [re-frame.core :refer [reg-sub]]))

(reg-sub
 :msg
 (fn [state _]
   (get-in state [:msg])))
