(ns pro-office-calc.cofx
  (:require
   [pro-office-calc.utils :as utils]
   [re-frame.core :refer [reg-cofx]]))

(reg-cofx
 :posix-time
 (fn [cofx]
   (assoc cofx :posix-time (utils/posix-time!))))
