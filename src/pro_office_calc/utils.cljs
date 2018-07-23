(ns pro-office-calc.utils)

(defn posix-time!
  []
  (/ (.getTime (js/Date.)) 1000))
