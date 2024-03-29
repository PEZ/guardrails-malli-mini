# A Guardrails with Malli mini example

* [Guardrails](https://github.com/fulcrologic/guardrails): Efficient, hassle-free function call validation with a concise inline syntax for clojure.spec and Malli
* [Malli](https://github.com/metosin/malli): High-performance data-driven data specification library for Clojure/Script.

This super mini [Clojure](https://clojure.org) + [ClojureScript](https://clojurescript.org) project demonstrates:
* How to use the declarative versions of `malli.util` transformation schemas with Guardrails
* How to use the Guardrails registry with regular Malli validation
  * NB: **This only seems to work if Guardrails is enabled.** See note in the source code.

The relevant code is in [src/main/guarded.cljc](src/main/guarded.cljc). Try the code in both the Clojure and the ClojureScript REPL.

Happy guarded coding! ❤️