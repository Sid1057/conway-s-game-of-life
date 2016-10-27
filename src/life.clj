(ns life)
(use 'clojure.test)

(def alive-set (atom 
  #{
    {:x 0, :y 0, :z -1}, {:x 0, :y 0, :z 0}, {:x 0, :y 0, :z 1}
  }))

(defn get-neis-count
  [cell]
  {:pre [(map? cell), (contains? cell :x), (contains? cell :y), (contains? cell :z)]}
  (let [alives (atom 0)]
    (doseq [i [-1, 0, 1]
            j [-1, 0, 1]
            k [-1, 0, 1]
            :when (or (not= i 0) (not= j 0) (not= k 0))
            :let [new-x (+ i (:x cell))
                  new-y (+ j (:y cell))
                  new-z (+ k (:z cell))]]
      (if (contains? @alive-set {:x new-x, :y new-y, :z new-z})
        (swap! alives inc)))
    @alives))

(defn should-be-alive?
  [cell]
  {:pre [(map? cell), (contains? cell :x), (contains? cell :y), (contains? cell :z)]}
  (let [alive-neis (get-neis-count cell)]
    (cond ;alive-neis
      (= alive-neis 3) true
      (= alive-neis 2) (contains? @alive-set cell)
      :else false)))

(defn get-neis-set
  [some-set]
  {:pre [(set? some-set), (every? map? some-set)]}
  (set (flatten 
         (for [cell (vec some-set)]
           (for [i [-1, 0, 1]
                 j [-1, 0, 1]
                 k [-1, 0, 1]
                 :let [new-x (+ i (:x cell))
                       new-y (+ j (:y cell))
                       new-z (+ k (:z cell))]]
             {:x new-x, :y new-y, :z new-z})))))

(defn update-set
  [some-set]
  (set (filter should-be-alive? (get-neis-set some-set))))

(defn iterate-field
  []
  (let [next-set (update-set @alive-set)]
    (reset! alive-set next-set)))

(defn -main[& args]
  (println @alive-set)
  (iterate-field)
  (println @alive-set))
