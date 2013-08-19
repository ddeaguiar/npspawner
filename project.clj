(defproject npspawner "1.0.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[cljminecraft "1.0.4-SNAPSHOT"]
                     ;make sure any required projects here either are already in clj-minecraft uberjar or
                     ;just make this an uberjar; or find a way to add them to ../lib in bukkit
                 [org.clojure/clojure "1.5.1"]
                 [org.clojure/tools.logging "0.2.6"]
                 [org.bukkit/bukkit "1.5.2-R1.0"]]
  :profiles {:dev {:dependencies []
                   :plugins [[cljminecraft-deploy "0.1.0"]]}}
  :hooks [leiningen.cljminecraft-deploy]
  :repositories [["bukkit.snapshots" "http://repo.bukkit.org/content/repositories/snapshots"]
                 ["bukkit.releases" "http://repo.bukkit.org/content/repositories/releases"]])
