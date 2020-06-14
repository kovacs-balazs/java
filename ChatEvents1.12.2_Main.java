package me.koba1.chatevents;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import static org.bukkit.Bukkit.getServer;

public class Main extends JavaPlugin implements Listener {

    Runnable runnable;

    @Override
    public void onEnable() {
        runnable = new Runnable(this);
        getServer().getPluginManager().registerEvents(new Runnable(this), this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("videobolismertemmeg")) {
            Player p = ((Player) sender);
            if (!getConfig().contains("Players." + p.getName())) {
                getConfig().set("Players." + p.getName(), "Videóból");
                sender.sendMessage("§aVálasztásodat sikeresen rögzítettük!");
                saveConfig();
            } else {
                sender.sendMessage(ChatColor.RED + "Már választottál egyet!");
            }
        }

        if (cmd.getName().equalsIgnoreCase("baratomtolismertemmeg")) {
            Player p = ((Player) sender);
            if (!getConfig().contains("Players." + p.getName())) {
                getConfig().set("Players." + p.getName(), "Barátomtól");
                sender.sendMessage("§aVálasztásodat sikeresen rögzítettük!");
                saveConfig();
            } else {
                sender.sendMessage(ChatColor.RED + "Már választottál egyet!");
            }
        }

        if (cmd.getName().equalsIgnoreCase("hirdetesbolismertemmeg")) {
            Player p = ((Player) sender);
            if (!getConfig().contains("Players." + p.getName())) {
                getConfig().set("Players." + p.getName(), "Hírdetésből");
                sender.sendMessage("§aVálasztásodat sikeresen rögzítettük!");
                saveConfig();
            } else {
                sender.sendMessage(ChatColor.RED + "Már választottál egyet!");
            }
        }

        if (cmd.getName().equalsIgnoreCase("egyebtolismertemmeg")) {
            Player p = ((Player) sender);
            if(!getConfig().contains("Players." + p.getName())) {
                getConfig().set("Players." + p.getName(), "Egyéb");
                sender.sendMessage("§aVálasztásodat sikeresen rögzítettük!");
                saveConfig();
            } else {
                sender.sendMessage(ChatColor.RED + "Már választottál egyet!");
            }
        }

        return false;
    }
}

