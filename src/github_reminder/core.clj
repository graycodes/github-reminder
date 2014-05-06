(ns github-reminder.core
  (:gen-class)
  (:require [net.cgrand.enlive-html :as html]))

(defn fetch-url
  "Returns the html from a given URL"
  [url]
  (html/html-resource (java.net.URL. url)))

(defn get-commits
  [user]
  (map :content (html/select (fetch-url "http://www.github.com/xinumbralis") [:a])))

(defn print-commits
  [l]
  (loop [list l]
    (if (not (empty? list))
      (do (println (first (first list)))
        (recur (rest list))))))

(defn -main
  [user]
  (print-commits (get-commits user)))
