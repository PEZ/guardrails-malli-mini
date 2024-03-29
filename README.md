# A Guardrails with Malli mini example

* [Guardrails](https://github.com/fulcrologic/guardrails): Efficient, hassle-free function call validation with a concise inline syntax for clojure.spec and Malli
* [Malli](https://github.com/metosin/malli): High-performance data-driven data specification library for Clojure/Script.

This super mini [Clojure](https://clojure.org) + [ClojureScript](https://clojurescript.org) project demonstrates:
* How to use the declarative versions of `malli.util` transformation schemas with Guardrails
* ~~How to use the Guardrails registry with regular Malli validation~~
  * ~~NB: **This only seems to work if Guardrails is enabled.** See note in the source code.~~
* A way to make Guardrails use a custom mutable Malli registry, as a way to share schemas between Guardrails and regular Malli validations.

The relevant code is in ~~[src/main/guarded.cljc](src/main/guarded.cljc)~~ [src/main/registry.cljc](src/main/registry.cljc). And in [src/main/guarded2.cljc](src/main/guarded2.cljc) there's code to try in both the Clojure and the ClojureScript REPL. With or without Guardrails enabled.

NB: Guardrails is enabled via JVM opts in `deps.edn`. To disable it for shadow-cljs you need to remove the `:dev` alias from the `:deps` entry in `shadow-cljs`.

Happy guarded coding! ❤️