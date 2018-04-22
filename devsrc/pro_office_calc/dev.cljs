(ns pro-office-calc.dev
  (:require [pro-office-calc.core :as poc]
            [figwheel.client :as fw]))

(fw/start {:on-jsload poc/run
           :websocket-url "ws://localhost:3449/figwheel-ws"})
