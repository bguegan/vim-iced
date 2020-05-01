(ns iced.boot-test
  (:require
   [clojure.java.io :as io]
   [clojure.test :as t]
   [iced.boot :as sut]))

(t/deftest dependencies->args-test
  (let [deps {'foo {:mvn/version "1"}
              'bar {:mvn/version "2"}}]
    (t/is (= #{["-i" "\"(require 'cider.tasks)\""]
               ["-d" "foo:1"]
               ["-d" "bar:2"]}
             (set (sut/dependencies->args deps))))))

(t/deftest middlewares->args-test
  (let [mdws ["foo" "bar"]]
    (t/is (= #{
               ["--" "cider.tasks/add-middleware"]
               ["-m" "foo"]
               ["-m" "bar"]}
             (set (sut/middlewares->args mdws))))))