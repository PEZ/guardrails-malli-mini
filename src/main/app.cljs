(ns main.app
  (:require #_[main.guarded :as guarded]
            [main.guarded2 :as guarded]))

(defn ^:dev/after-load start! []
  (js/console.log "start")
  (let [replace (js/document.getElementById "app")]
    (set! (.-innerHTML replace) (guarded/hello-friend {:nom "World" :mood 42}))))

(defn ^:export init! []
  (js/console.log "init")
  (start!))
