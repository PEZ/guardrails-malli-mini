(ns main.registry
  (:require [com.fulcrologic.guardrails.malli.registry :as gr.reg]
            [malli.core :as m]
            [malli.registry :as mr]
            [malli.util :as mu]))

;; To share the registry between regular Malli validations and Guardrails
;; we use a mutable Malli registry.
(def !registry
  (atom (merge (-> m/default-registry (mr/schemas))
               (mu/schemas) ; To get the declarative versions of Malli's
                            ; schema transforming utilities
               )))

(mr/set-default-registry!
 (mr/mutable-registry !registry))

;; Guardrails' registry already contains the malli core schemas
;; But we need it to use our registry so we merge it here, and
;; whenever a new schema is registered.

(gr.reg/merge-schemas! @!registry)

(defn register! [type ?schema]
  (swap! !registry assoc type ?schema)
  (gr.reg/merge-schemas! @!registry))

;; We can now use the `>def` convenience macros to register schemas
;; for both regular Malli validations and Guardrails.
#?(:clj (defmacro >def [type schema]
          `(register! ~type ~schema)))