(ns screenshot
  {:clj-kondo/config '{:lint-as {promesa.core/let clojure.core/let
                                 browser/defp clojure.core/def}}}
  (:require ["playwright$default" :as pw]
            [promesa.core :as p]))


(defn take-screenshot [browser-type, site]
  (p/let [browser (.launch browser-type #js {:headless false})
          context (.newContext browser)
          page (.newPage context)]
    (.goto page site)
    (.screenshot page #js{:path "screenshot.png" :fullPage true})
    (.close browser)))


(comment
  (def chromium pw/chromium)
  (def firefox pw/firefox)

  (def site "https://www.encamp.com")
  (take-screenshot firefox site))