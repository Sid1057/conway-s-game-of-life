(ns life.core-test
  (:require [clojure.test :refer :all]
            [life.core :refer :all]
            ))
(require `life)


(deftest get-neis-test 
  (is (= (life/get-neis-count {:x 0, :y 0, :z 0}) 2))
  (is (= (life/get-neis-count {:x 120, :y 220, :z 3230}) 0))
  (is (= (life/get-neis-count {:x 0, :y 1, :z 0}) 3))
  (is (= (life/get-neis-count {:x 1, :y 1, :z 1}) 2))
  (is (= (life/get-neis-count {:x 0, :y 1, :z 1}) 2)))

(deftest alive-test
  (is (life/should-be-alive? {:x 0, :y 0, :z 0}))
  (is (life/should-be-alive? {:x 0, :y 1, :z 0}))
  (is (not (life/should-be-alive? {:x 0, :y 1, :z 1})))
  (is (not (life/should-be-alive? {:x 1, :y 1, :z 1})))
  (is (not (life/should-be-alive? {:x 120, :y 1, :z 0}))))
  
(deftest get-neis-set-test
  (is (= (life/get-neis-set #{{:x 0, :y 0, :z 0}}) 
     #{
        {:x -1, :y -1, :z -1}
        {:x -1, :y -1, :z 0}
        {:x -1, :y -1, :z 1}
        {:x -1, :y 0, :z -1}
        {:x -1, :y 0, :z 0}
        {:x -1, :y 0, :z 1}
        {:x -1, :y 1, :z -1}
        {:x -1, :y 1, :z 0}
        {:x -1, :y 1, :z 1}
        {:x 0, :y -1, :z -1}
        {:x 0, :y -1, :z 0}
        {:x 0, :y -1, :z 1}
        {:x 0, :y 0, :z -1}
        {:x 0, :y 0, :z 0}
        {:x 0, :y 0, :z 1}
        {:x 0, :y 1, :z -1}
        {:x 0, :y 1, :z 0}
        {:x 0, :y 1, :z 1}
        {:x 1, :y -1, :z -1}
        {:x 1, :y -1, :z 0}
        {:x 1, :y -1, :z 1}
        {:x 1, :y 0, :z -1}
        {:x 1, :y 0, :z 0}
        {:x 1, :y 0, :z 1}
        {:x 1, :y 1, :z -1}
        {:x 1, :y 1, :z 0}
        {:x 1, :y 1, :z 1}})))
