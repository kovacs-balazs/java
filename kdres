package me.koba1.killevents1122;

import me.clip.placeholderapi.PlaceholderAPI;
import me.koba1.killevents1122.Main;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import me.koba1.killevents1122.Main;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "A KillEvents plugin betöltve!");
        getServer().getPluginManager().registerEvents(this, this);
        saveConfig();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "A KillEvents plugin leállt!");
    }

    public String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Location loc = p.getPlayer().getLocation();
        if (p.getKiller() != null) {
            Player k = p.getKiller();
            k.getPlayer().playEffect(loc, Effect.MOBSPAWNER_FLAMES, 1);
            k.getPlayer().playSound(loc, Sound.BLOCK_NOTE_PLING, 1, 1);
            String pUUID = p.getUniqueId().toString();
            String kUUID = k.getUniqueId().toString();
            int kills = getConfig().getInt("Players." + kUUID + ".Kills");
            int deaths = getConfig().getInt("Players." + pUUID + ".Deaths");

            getConfig().set("Players." + kUUID + ".Kills", kills + 1);
            getConfig().set("Players." + pUUID + ".Deaths", deaths + 1);
            saveConfig();

            k.sendMessage(color("&aMegölted őt: " + ChatColor.RED + p.getName()));
            p.sendMessage(color("&cMegölt téged: " + ChatColor.YELLOW + k.getName()));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + p.getName() + "150");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco take " + k.getName() + "50");

            e.setDeathMessage(ChatColor.GRAY + p.getName() + ChatColor.GRAY + " meghalt " + k.getName() + " által.");

            int money = 0;
            if(k.getPlayer().hasPermission("kill.default")) {
                money = 150;
            }

            if(k.getPlayer().hasPermission("kill.fortune")) {
                money = 200;
            }

            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + k.getName() + " " + money);
            k.sendMessage(color("&3Megölted őt: " + ChatColor.GREEN + p.getName() + "&3. Kaptál &a$" + money + "&3-t."));

            p.sendMessage(color("&3Megölt téged: " + ChatColor.GREEN + k.getName() + "&3. Veszettél &a$50&3-t."));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco take " + p.getName() + " 50");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("stats")) {
            if(args.length == 0) {
                Player p = (Player) sender;
                Player t = (Player) sender;
                Player player = (Player) sender;
                String uuid = t.getUniqueId().toString();

                int kills = getConfig().getInt("Players." + uuid + ".Kills");
                int deaths = getConfig().getInt("Players." + uuid + ".Deaths");
                double kills2 = getConfig().getInt("Players." + uuid + ".Kills");
                double deaths2 = getConfig().getInt("Players." + uuid + ".Deaths");
                double kdr = kills2 / deaths2;
                if (deaths < 1) {
                    kdr = 0;
                }

                String replaced = PlaceholderAPI.setPlaceholders(player, "%vault_eco_balance%");
                String replaced2 = PlaceholderAPI.setPlaceholders(player, "%server_time_HH:mm:ss%");
                String replaced3 = PlaceholderAPI.setPlaceholders(player, "%server_time_yyyy.MM.dd%");

                sender.sendMessage(ChatColor.YELLOW + "+------------------------------------+");
                sender.sendMessage(ChatColor.GRAY + "Életed: " + ChatColor.GREEN + player.getHealth() / 2 + ChatColor.GRAY + " | XP szinted: " + ChatColor.GREEN + player.getLevel());
                sender.sendMessage("");
                sender.sendMessage(ChatColor.GRAY + "Idő: " + ChatColor.GREEN + replaced2 + ChatColor.GRAY + " | Dátum: " + ChatColor.GREEN + replaced3);
                sender.sendMessage("");
                sender.sendMessage(ChatColor.GRAY + "Pénzed: " + ChatColor.GREEN + "$" + replaced + ChatColor.GRAY + " | KDR: " + ChatColor.GREEN + kdr);
                sender.sendMessage("");
                sender.sendMessage(ChatColor.GRAY + "Öléseid száma: " + ChatColor.GREEN + kills + ChatColor.GRAY + " | Halálaid száma: " + ChatColor.GREEN + deaths);
                sender.sendMessage(ChatColor.YELLOW + "+------------------------------------+");
                return false;
            }

            if (args[0].equalsIgnoreCase("clear")) {
                Player t = getServer().getPlayer(args[1]);
                String uuid = t.getUniqueId().toString();

                getConfig().set("Players." + uuid + ".Kills", 0);
                getConfig().set("Players." + uuid + ".Deaths", 0);
                saveConfig();
                t.sendMessage(ChatColor.GRAY + "Statisztikád törölve lett " + ChatColor.GREEN + sender.getName() + ChatColor.GRAY + " által.");
                sender.sendMessage(ChatColor.GREEN + t.getName() + " §7nevű játékosnak törölted a statisztikáját!");

                return false;
            }

            if(args[0].equalsIgnoreCase("reload")) {
                reloadConfig();
                sender.sendMessage(ChatColor.AQUA + "Config fájl újratöltve");
                return false;
            }

        }

        return false;
    }
}
