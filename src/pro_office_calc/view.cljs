(ns pro-office-calc.view
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as rf]
   [pro-office-calc.config :refer [app-config]]))

(defn span
  ([class-name content]
   (span class-name {} content))
  ([class-name props content]
   [:span (merge {:class-name class-name}
                 props)
    content]))

(defn code-block
  [& lines]
  (span "code" (map-indexed (fn [i line]
                              (span "line" {:key i} line))
                            lines)))

(defn download-link
  [name url text]
  [:span {:class "download-link"}
   [:a  {:class "download-link"
         :href url
         :download nil
         :on-click #(rf/dispatch [:download-link-click name url])
         :on-context-menu (fn [e]
                            (.preventDefault e)
                            false)}
    (span "link-text" text)]
   [:span {:class "left-click"} [:br] "left-click"]])

(defn footer-view
  []
  [:div {:class "footer"}
   "Copyright Rob Jinman 2018. All rights reserved."])

(def launchpad-url "https://launchpad.net/~rjinman/+archive/ubuntu/snapshots/+packages")
(def github-url "https://github.com/RobJinman/pro_office_calc")

(defn main-view
  [config]
  (let [osx-uri (rf/subscribe [:download-location :osx])
        win-uri (rf/subscribe [:download-location :windows])]
    (fn [config]
      [:div {:class "page"}
       [:div {:class "main-content"}
        [:h2 {:class "heading"} "Pro Office Calculator"]
        [:p "A completely normal office calculator."]
        [:img {:class-name "screenshot"
               :src "img/screen01.png"}]
        [:h3 "Windows 10"]
        [:p (download-link "procalc_win"
                           @win-uri
                           "Download for Windows 10 64-bit")]
        [:h3 "Mac OS X"]
        [:p (download-link "procalc_osx"
                           @osx-uri
                           "Download for OSX 64-bit")]
        [:h3 "Linux"]
        [:div [:p "Ubuntu users can install Pro Office Calculator via the PPA."]
         (code-block
          "sudo add-apt-repository ppa:rjinman/ppa"
          "sudo add-apt-repository ppa:rjinman/snapshots # Optional"
          "sudo apt-get update"
          "sudo apt-get install procalc")
         [:p "Alternatively, the debian binary packages can be "
          [:a {:href launchpad-url} "downloaded directly"] " from launchpad."]
         [:p "To build from source, clone the " [:a {:href github-url} "repo"]
          " and follow the instructions in the README."]]]
       [footer-view]])))
