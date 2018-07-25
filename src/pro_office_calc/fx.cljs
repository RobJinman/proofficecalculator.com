(ns pro-office-calc.fx
  (:require
   [re-frame.core :refer [reg-fx]]))

(reg-fx
 :analytics
 (fn [{:keys [category action label]}]
   (let [ga (aget js/window "ga")]
     (ga "send" #js{:hitType "event"
                    :eventCategory category
                    :eventAction action
                    :eventLabel label}))))
