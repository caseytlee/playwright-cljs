(ns browser
  {:clj-kondo/config '{:lint-as {promesa.core/let clojure.core/let
                                 browser/defp clojure.core/def}}}
  (:require ["playwright$default" :as pw]
            [promesa.core :as p]))

(defn open-browser [browser-type]
  (p/let [browser (.launch browser-type #js {:headless false})]
    browser))

(defn create-context [browser]
  (p/let [context (.newContext browser)]
    context))

(defn create-page [context]
  (p/let [page (.newPage context)]
    page))

(comment
  (defmacro defp
    "Define a var when promise is resolved"
    [binding expr]
    `(-> ~expr (.then (fn [val]
                        (def ~binding val)))))

  (def browser-type pw/chromium)
  (def browser-type pw/firefox)
  (defp browser (open-browser browser-type))
  (defp context (create-context browser))
  (defp page (create-page context))


  (.goto page "https://www.weather.gov")

  (def search-input (.getByLabel page "Local forecast by"))
  (.fill search-input "Denver, CO")
  (.press search-input "Enter")

  (.goBack page)

  (.url page)

  (.close browser))