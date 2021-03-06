package me.koba1.fortunecommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;

import java.io.File;

public class Main extends JavaPlugin implements Listener {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "A commands plugin betöltött.");
    }

    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "A commands plugin leállt.");
    }

    public String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("discord")) {
            if (sender instanceof Player) {
                sender.sendMessage(ChatColor.GRAY + "Discord link: " + ChatColor.GREEN + getConfig().getString("discordlink"));
                saveConfig();
                return false;
            }
        }

        if (cmd.getName().equalsIgnoreCase("fortunecommands")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.DARK_GREEN + "§lFortune" + ChatColor.GREEN + "§lCommands");
                sender.sendMessage("");
                sender.sendMessage(ChatColor.GRAY + "Plugint készítette: " + ChatColor.GREEN + "koba1");
                sender.sendMessage(ChatColor.GRAY + "Parancsok:" + ChatColor.GREEN + " /fortunecommands help");
                sender.sendMessage("");
                return false;
            }

            if (args[0].equalsIgnoreCase("reload")) {
                reloadConfig();
                sender.sendMessage(ChatColor.AQUA + "Config fájl újratöltve");
                return false;
            }


            if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage(ChatColor.DARK_GREEN + "§lFortune" + ChatColor.GREEN + "§lCommands");
                sender.sendMessage("");
                sender.sendMessage(ChatColor.DARK_GREEN + "Parancsok:");

                if(p.getPlayer().hasPermission("fortunecommands.admin")) {
                    sender.sendMessage(ChatColor.GREEN + "/fc reload " + ChatColor.GRAY + "- Config fájl újratöltése");
                }

                if(p.getPlayer().hasPermission("fortunecommands.default")) {
                    sender.sendMessage(ChatColor.GREEN + "/fc help" + ChatColor.GRAY + " - Parancsok kilistázása");
                    sender.sendMessage(ChatColor.GREEN + "/discord" + ChatColor.GRAY + " - Discord link megnézése");
                    sender.sendMessage(ChatColor.GREEN + "/szabalyzat" + ChatColor.GRAY + " - Szabályzat megnézése");
                    sender.sendMessage(ChatColor.GREEN + "/roleplay" + ChatColor.GRAY + " - Fortune GTA V American RolePlay - Hungarian discord link megnézése");
                    sender.sendMessage(ChatColor.GREEN + "/stats" + ChatColor.GRAY + " - Statisztikád megnézése");
                    sender.sendMessage(ChatColor.GREEN + "/csapat" + ChatColor.GRAY + " - Csapattagok megnézése - Parancsot készítette: §aSKYDAZ");
                }
                return false;
            } else {
                sender.sendMessage("§cNincs ilyen parancs!");
                sender.sendMessage("§7Parancsokért írd be a §a/fc help §7parancsot!");
            }
        }

        if (cmd.getName().equalsIgnoreCase("szabalyzat")) {
            if (sender instanceof Player) {
                ((Player) sender).performCommand("rules");
            }
        }

        if (cmd.getName().equalsIgnoreCase("roleplay")) {
            if (sender instanceof Player) {
                sender.sendMessage(ChatColor.GRAY + "Fortune GTA V American RolePlay - Hungarian");
                sender.sendMessage(ChatColor.GRAY + "Discord link: " + ChatColor.GREEN + getConfig().getString("rpdiscordlink"));
            }
        }

        if(cmd.getName().equalsIgnoreCase("csapat")) {
            if(sender instanceof Player) {
                sender.sendMessage(ChatColor.GRAY + "Tulajdonosok: " + getConfig().getString("tulajdonosok"));
                sender.sendMessage(ChatColor.GRAY + "Építészek: " + getConfig().getString("epiteszek"));
                sender.sendMessage(ChatColor.GRAY + "Fejlesztő: " + getConfig().getString("fejlesztok"));
                sender.sendMessage("");
                sender.sendMessage(ChatColor.GRAY + "Parancsot készítette: §aSKYDAZ");
            }
        }

        return false;
    }
}
