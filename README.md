# Browser Automation with Clojurescript

## Run example

- Run `npm install` to install [Playwright](https://playwright.dev/).

## Develop

To develop you can start `nbb nrepl-server` and connect from your [favorite
editor](https://github.com/borkdude/nbb#nrepl).

[Calva](https://calva.io/) is widely used if you're using VS Code.

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
