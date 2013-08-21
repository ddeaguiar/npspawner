(ns npspawner.core
    (:require [cljminecraft.bukkit :as bk]
              [cljminecraft.blocks :as blocks]
              [cljminecraft.events :as ev]
              [cljminecraft.entity :as ent]
              [cljminecraft.player :as plr]
              [cljminecraft.util :as util]
              [cljminecraft.logging :as log]
              [cljminecraft.config :as cfg]
              [cljminecraft.commands :as cmd]
              [cljminecraft.recipes :as r]
              [cljminecraft.items :as i]
              [cljminecraft.files :as f]))

(defonce plugin (atom nil))

(defn- portal []
  [(blocks/up 4)
   (blocks/turn-right)
   (blocks/forward 3)
   (blocks/down 4)
   (blocks/turn-left)
   (blocks/turn-left)
   (blocks/forward 3)])

(defn- facing-dir [ctx]
  ([:north :south :east :west] (bit-and (Math/round
                                         (/ (.getYaw (:origin ctx))
                                            (float 90)))
                                        0x3)))

(defn- build-nether-portal! [player-name]
  (let [ctx (blocks/setup-context player-name)
        air (blocks/material :air)
        obsidian (blocks/material :obsidian)]
    (-> ctx
        (blocks/run-actions (blocks/turn (facing-dir ctx))
                            air (blocks/forward 5)
                            obsidian (portal)))))

(defn nether-portal-command
  [sender]
  (let [pname (.getPlayerListName sender)]
    (log/info "%s called nether-portal-command" pname)
    (build-nether-portal! (.getPlayerListName sender))
    {:msg (format (cfg/get-string @plugin "netherportal.string") pname )}))

;; Plugin lifecycle
(defn start
  [plugin-instance]
  (log/info "%s" "in start npspawner")
  (reset! plugin plugin-instance)
  (cmd/register-command @plugin "npspawner.nether-portal" #'nether-portal-command))

(defn stop
  [plugin]
  (log/info "%s" "in stop npspawner"))
