(ns pro-office-calc.config
  (:require [pro-office-calc.localisation :refer [get-l10n!]]))

(def app-config {:l10n (get-l10n!)})
