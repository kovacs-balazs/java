    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        if(p.getKiller() instanceof  Player) {
            Player k = p.getKiller();
            String pUUID = p.getUniqueId().toString();
            String kUUID = k.getUniqueId().toString();
            int kills = getConfig().getInt("Players." + kUUID + ".Kills");
            int deaths = getConfig().getInt("Players." + pUUID + ".Deaths");

            getConfig().set("Players." + kUUID + ".Kills", kills + 1);
            getConfig().set("Players." + pUUID + ".Deaths", deaths + 1);
            saveConfig();

            k.sendMessage(color("&aMegölted őt: " + ChatColor.RED + k.getName()));
            p.sendMessage(color("&cMegölt téged: " + ChatColor.YELLOW + p.getName()));
        }
    }
