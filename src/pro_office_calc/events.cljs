(ns pro-office-calc.events
  (:require
   [ajax.core :as ajax]
   [pro-office-calc.utils]
   [re-frame.core :refer [reg-event-db reg-event-fx]]))

(reg-event-fx
 :initialise
 (fn [cofx _]
   {:dispatch [:read-manifest]}))

(reg-event-fx
 :read-manifest
 (fn [_ _]
   {:http-xhrio {:method :get
                 :uri "downloads/manifest"
                 :timeout 8000
                 :response-format (ajax/text-response-format)
                 :on-success [:read-manifest/success]
                 :on-failure [:read-manifest/failure]}}))

(defn get-element-text
  [xml-doc tag-name]
  (-> xml-doc 
      (.getElementsByTagName tag-name)
      first
      .-childNodes
      first
      .-nodeValue))

(reg-event-db
 :read-manifest/success
 (fn [db [_ result]]
   (let [parser (js/DOMParser.)
         xml-doc (.parseFromString parser result "text/xml")
         windows (get-element-text xml-doc "windows")
         osx (get-element-text xml-doc "osx")]
     (assoc-in db [:download-locations] {:windows (str "downloads/" windows)
                                         :osx (str "downloads/" osx)}))))

(reg-event-db
 :read-manifest/failure
 (fn [_ _]
   (js/console.error "Error reading manifest from server")))

(reg-event-fx
 :download-link-click
 (fn [cofx [_ name url]]
   {:analytics {:category "downloads"
                :action name
                :label url}}))
