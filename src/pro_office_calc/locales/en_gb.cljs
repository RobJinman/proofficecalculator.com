(ns pro-office-calc.locales.en-gb)

(defn span
  [class-name content]
  [:span {:class-name class-name} content])

(defn code-block
  [& lines]
  (span "code" (for [line lines]
                 (span "line" line))))

(def en-gb-copy
  {:title "Pro Office Calculator"
   :description "A completely normal office calculator."
   :linux {:heading "Linux"
           :instructions [:div [:p "Ubuntu users can install Pro Office Calculator via the PPA."]
                          (code-block
                           "sudo add-apt-repository ppa:rjinman/ppa"
                           "sudo add-apt-repository ppa:rjinman/snapshots # Optional"
                           "sudo apt-get update"
                           "sudo apt-get install procalc")
                          [:p "Alternatively, the debian binary packages can be "
                           [:a {:href "https://launchpad.net/~rjinman/+archive/ubuntu/snapshots/+packages"} "downloaded directly"] " from launchpad."]
                          [:p "To build from source, clone the " [:a {:href "https://github.com/RobJinman/pro_office_calc"} "repo"]
                           " and follow the instructions in the README."]]}
   :windows {:heading "Windows 10"
             :instructions [:a {:href "downloads/procalc.msi"} "Download for Windows 10 64-bit"]}
   :mac {:heading "Mac OS X"
         :instructions [:a {:href "downloads/procalc.app.zip"} "Download for OSX 64-bit"]}
   :footer {:copyright "Copyright Rob Jinman 2018. All rights reserved."}})
