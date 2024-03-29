(ns main.guarded
  (:require [com.fulcrologic.guardrails.malli.registry :as gr.reg]
            [com.fulcrologic.guardrails.malli.core :refer [>def >defn =>]]
            [malli.util :as mu]))

;; Guardrails with Malli support
;; https://github.com/fulcrologic/guardrails#malli-support

;; Guardrails' registry already contains the malli core schemas
;; This is for adding the declarative versions of malli's schema transforming utilities
;; https://github.com/metosin/malli#declarative-schema-transformation
(gr.reg/merge-schemas! (mu/schemas))

;; Register a schema in the Guardrails registry
(>def :hello/contact [:map [:nom :string]])

;; Use the schema to guard a function
(>defn hello [{:keys [nom]}]
  [:hello/contact => :string]
  (str "Hello " nom "!"))

;; Use a declarative schema transformation
(>defn hello-friend [{:keys [mood] :as props}]
  [[:merge :hello/contact [:map [:mood :int]]] => :string]
  (str (hello props) " Feeling " (if (= 42 mood)
                                   " perfect"
                                   " not so perfect") " today, huh?"))

(comment
  (hello-friend {:nom "World" :mood 42})

  ;; To use the Guardrails' registry with Malli, one way is to specify it as a `:registry` option
  ;; NB: This currently only works if Guardrails is enabled.
  ;;     You may want to manage any schemas that you want to be able to
  ;;     validate using Malli directly in production builds eparately.
  (require '[malli.core :as m])
  (m/validate [:merge :hello/contact [:map [:mood :int]]]
                       {:nom "World" :mood 42}
                       {:registry gr.reg/registry}) ;; => true

  (do
    (def explanation (m/explain [:merge :hello/contact [:map [:mood :int]]]
                                         {:nom "World"}
                                         {:registry gr.reg/registry}))
    explanation)

  (require '[malli.error :as me])
  (me/humanize explanation) ;; => {:mood ["missing required key"]}

  (-> (m/explain [:merge :hello/contact [:map [:mood :int]]]
                          {:nom "World" :mood :42}
                          {:registry gr.reg/registry})
      (me/humanize)) ;; => {:mood ["should be an integer"]}

  :rcf)