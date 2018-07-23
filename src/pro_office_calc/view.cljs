(ns pro-office-calc.view
  (:require
   [reagent.core :as reagent]
   [re-frame.core :refer [dispatch
                          dispatch-sync
                          subscribe]]
   [pro-office-calc.config :refer [app-config]]))

(defn span
  [class-name content]
  [:span {:class-name class-name} content])

(defn code-block
  [& lines]
  (span "code" (for [line lines]
                 (span "line" line))))

(defn sub-view
  []
  (let [msg (subscribe [:msg])]
    [:div (str @msg)]))

(defn footer-view
  []
  [:div {:class "footer"}
   "Copyright Rob Jinman 2018. All rights reserved."])

(defn main-view
  [config]
  (fn [config]
    [:div {:class "page"}
     [:div {:class "main-content"}
      [:h2 {:class "heading"} "Pro Office Calculator"]
      [:p "A completely normal office calculator."]
      [:img {:class-name "screenshot"
             :src "img/screen01.png"}]
      [:h3 "Windows 10"]
      [:p [:a {:href "downloads/procalc.msi"} "Download for Windows 10 64-bit"]]
      [:h3 "Mac OS X"]
      [:p [:a {:href "downloads/procalc.app.zip"} "Download for OSX 64-bit"]]
      [:h3 "Linux"]
      [:div [:p "Ubuntu users can install Pro Office Calculator via the PPA."]
       (code-block
        "sudo add-apt-repository ppa:rjinman/ppa"
        "sudo add-apt-repository ppa:rjinman/snapshots # Optional"
        "sudo apt-get update"
        "sudo apt-get install procalc")
       [:p "Alternatively, the debian binary packages can be "
        [:a {:href "https://launchpad.net/~rjinman/+archive/ubuntu/snapshots/+packages"} "downloaded directly"] " from launchpad."]
       [:p "To build from source, clone the " [:a {:href "https://github.com/RobJinman/pro_office_calc"} "repo"]
        " and follow the instructions in the README."]]]
     [footer-view]]))
