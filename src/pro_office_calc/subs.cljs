(ns pro-office-calc.subs
  (:require
   [re-frame.core :refer [reg-sub]]))

(reg-sub
 :download-location
 (fn [db [_ platform]]
   (get-in db [:download-locations platform])))
