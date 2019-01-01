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
         windows-installer (get-element-text xml-doc "windows-installer")
         windows-bundle (get-element-text xml-doc "windows-bundle")
         osx-bundle (get-element-text xml-doc "osx-bundle")
         linux-installer (get-element-text xml-doc "linux-installer")
         linux-bundle (get-element-text xml-doc "linux-bundle")]
     (assoc-in db [:download-locations] {:windows-installer (str "downloads/" windows-installer)
                                         :windows-bundle (str "downloads/" windows-bundle)
                                         :osx-bundle (str "downloads/" osx-bundle)
                                         :linux-installer (str "downloads/" linux-installer)
                                         :linux-bundle (str "downloads/" linux-bundle)}))))

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
