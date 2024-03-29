(ns main.server
  (:require #_[main.guarded :as guarded]
            [main.guarded2 :as guarded])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& _args]
  (println (guarded/hello-friend {:nom "World" :mood 42})))

(comment
  (-main)
  :rcf)
