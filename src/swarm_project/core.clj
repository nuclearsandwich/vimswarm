(ns swarm-project.core
  (:use [clojure.set :only (difference)])
  (:use [clojure.test]))

(def blacklist #{:clojure :clj-config})

(with-test 
  (defn is-a-root? [node]
    (let [deps (difference (get-in node [:name :dependencies]) blacklist)] 
      (empty? deps)))
  (is (is-a-root? {:name {:dependencies blacklist}}))
  (is (is-a-root? {:name {:dependencies #{}}})))  
  (is (not (is-a-root? {:name {:dependencies #{:something}}})))   


(defn find-roots
  [graph]
  (filter is-a-root? graph))

(defn -main
  "I don't do a whole lot."
  [& args]
  ;; Read in the file
  ;; Find roots of the graph
  ;;  ???
  ;; Profit
  (let [graph (read-string (slurp "./melange"))
        roots (find-roots graph)] 
  (println roots)))

