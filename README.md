# Browser Automation with ClojureScript

## Run example

- Run `npm install` to install [Playwright](https://playwright.dev/).

## Develop

### [Calva](https://calva.io/)

The Calva VS Code extension is widely used for Clojure(Script) development.

- Calva's [Jack-in command](https://calva.io/connect/#jack-in-let-calva-start-the-repl-for-you) is useful for starting a development REPL.
- Once a REPL has been started, interactive development can be done through [Code Evaluation](https://calva.io/eval-tips/).

The following macro is quite helpful for REPL development:

```clojure
(defmacro defp
    "Define var when promise is resolved"
    [binding expr]
    `(-> ~expr (.then (fn [val]
                        (def ~binding val)))))
```

You can use it like this:

```clojure
(require '["playwright$default" :as pw])
(defp browser (.launch pw/chromium #js {:headless false}))
(defp context (.newContext browser))
(defp page (.newPage context))
(.goto page "https://weather.gov")
```
