(ns github-reminder.core
  (:gen-class)
  (:require [net.cgrand.enlive-html :as html]))

(defn fetch-url
  "Returns the html from a given URL"
  [url]
  (html/html-resource (java.net.URL. url)))

(defn get-commits
  [user]
  (map :content
       (html/select (fetch-url "https://github.com/xinumbralis") [:div.contrib-details :div :span]))
  )

(defn print-commits
  [l]
  (loop [list l]
    (if (not (empty? list))
      (do (println (first (first list)))
        (recur (rest list))))))

(defn -main
  [user]
  (print-commits (get-commits user)))
