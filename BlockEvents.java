    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Location loc = e.getBlock().getLocation();

        e.getPlayer().sendMessage(ChatColor.GREEN + "Kiütöttél egy blockot!");
        e.getPlayer().playEffect(loc, Effect.MOBSPAWNER_FLAMES, 1);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Location loc = e.getBlock().getLocation();

        e.getPlayer().sendMessage(ChatColor.AQUA + "Letettél egy blockot!");
        e.getPlayer().playEffect(loc, Effect.MOBSPAWNER_FLAMES, 1);

    }
