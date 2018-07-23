(ns pro-office-calc.cofx
  (:require
   [re-frame.core :refer [reg-cofx]]))

(reg-cofx
 :posix-time
 (fn [cofx]
   (assoc cofx :posix-time (posix-time!))))
