(ns pro-office-calc.events
  (:require
   [re-frame.core :refer [reg-event-db reg-event-fx]]))

(reg-event-fx
 :initialise
 (fn [cofx _]
   {:db {:msg "Hello, World!"}}))
