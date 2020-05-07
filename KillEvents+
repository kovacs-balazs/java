package me.koba1.killevents115;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.CommandSender;
import java.util.Objects;


public class Main extends JavaPlugin implements Listener {

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

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Location loc = Objects.requireNonNull(p.getPlayer()).getLocation();
        if (p.getKiller() != null) {
            Player k = p.getKiller();
            Objects.requireNonNull(k.getPlayer()).playEffect(loc, Effect.MOBSPAWNER_FLAMES, 1);
            k.getPlayer().playSound(loc, Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
            String pUUID = p.getUniqueId().toString();
            String kUUID = k.getUniqueId().toString();
            int kills = getConfig().getInt("Players." + kUUID + ".Kills");
            int deaths = getConfig().getInt("Players." + pUUID + ".Deaths");

            getConfig().set("Players." + kUUID + ".Kills", kills + 1);
            getConfig().set("Players." + pUUID + ".Deaths", deaths + 1);
            saveConfig();

            e.setDeathMessage(ChatColor.GRAY + p.getName() + ChatColor.GRAY + " meghalt " + k.getName() + " által.");

            int money = 0;
            if(k.getPlayer().hasPermission("kill.default")) {
                money = 100;
            }

            if(k.getPlayer().hasPermission("kill.pro")) {
                money = 200;
            }

            if(k.getPlayer().hasPermission("kill.slime")) {
                money = 300;
            }

            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + k.getName() + " " + money);
            k.sendMessage(color("&3Megölted őt: " + ChatColor.GREEN + p.getName() + "&3. Kaptál &a$" + money + "&3-t."));

            p.sendMessage(color("&3Megölt téged: " + ChatColor.GREEN + k.getName() + "&3. Veszettél &a$50&3-t."));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco take " + p.getName() + " 50");
        }
    }

    public String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("stats")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                Player t = (Player) sender;
                String uuid = p.getUniqueId().toString();

                sender.sendMessage(ChatColor.AQUA + "+------------------------------------+");
                sender.sendMessage(ChatColor.BLUE + "Játékosneved: " + ChatColor.GREEN + ((Player) sender).getName() + "§9!");
                sender.sendMessage(ChatColor.RED + "Életed: " + ((Player) sender).getHealth() / 2);
                sender.sendMessage(ChatColor.RED + "Kajacsíkod: " + ((Player) sender).getFoodLevel() / 2);

                int kills = getConfig().getInt("Players." + uuid + ".Kills");
                int deaths = getConfig().getInt("Players." + uuid + ".Deaths");
                sender.sendMessage("§a§n" + p.getName() + "§r " + ChatColor.YELLOW + kills + "§a öléssel és " + ChatColor.YELLOW + deaths + "§a halállal rendelkezik!");
                sender.sendMessage(ChatColor.AQUA + "+------------------------------------+");
            }
        }

        return false;
    }

}
