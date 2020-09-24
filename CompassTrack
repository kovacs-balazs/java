package me.kbalu.CompassTrack;

import it.unimi.dsi.fastutil.objects.AbstractObject2ObjectFunction;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Random;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(this, this);
        autobroadcast();
        getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "A plugin betöltött");
        autobroadcast();
        if(!getConfig().contains("Target." + ".Name")) {
            getConfig().set("Target." + ".Name", 0);
            saveConfig();
        }
    }

    String msg2 = ChatColor.GRAY + "§7Távolság jelölés kikapcsolva! (/c toggle)";
    public void autobroadcast() {
        final String[] message = {
/*                ChatColor.GRAY + "§7Távolság jelölés kikapcsolva! (/c toggle)",
                ChatColor.GOLD + "A §eViaBackwards.jar §6fájl nincsen bent a §eplugins §6mappában!",*/
        };
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if(getConfig().contains("Players." + p.getName())) {
                        p.sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(msg2));
                    }
                }
            }
        }, 0 ,6000);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        String name = "" + getConfig().getString("Target." + ".Name");
        Player p = Bukkit.getServer().getPlayer(name);
        assert p != null;
        event.getPlayer().setCompassTarget(p.getLocation());

        if(getConfig().contains("Players." + event.getPlayer().getName())) {
            double X2 = p.getLocation().getX();
            double X1 = event.getPlayer().getLocation().getX();

            double Z2 = p.getLocation().getZ();
            double Z1 = event.getPlayer().getLocation().getZ();

            double dist = Math.round(Math.sqrt(((X2 - X1) * (X2 - X1)) + ((Z2 - Z1) * (Z2 - Z1))) * 100.00) / 100.00;

            if(dist <= 25) {
                String msg = "§7Távolság (" + p.getName() + "): §a" + dist;
                event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(msg));
            }
        } else {
            //event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Távolság jelölés kikapcsolva! (/c toggle)"));
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(event.getPlayer().getItemInHand().getType().equals(Material.COMPASS)) {
            String name = "" + getConfig().getString("Target." + ".Name");
            Player p = Bukkit.getServer().getPlayer(name);
            assert p != null;
            event.getPlayer().setCompassTarget(p.getLocation());

            double X2 = p.getLocation().getX();
            double X1 = event.getPlayer().getLocation().getX();

            double Z2 = p.getLocation().getZ();
            double Z1 = event.getPlayer().getLocation().getZ();

            double Y1 = p.getLocation().getY();
            double Y2 = event.getPlayer().getLocation().getY();

            double dist = Math.round(Math.sqrt(((X2-X1)*(X2-X1)) + ((Z2-Z1)*(Z2-Z1))) * 100.00) / 100.00;

            if(dist <= 25) {
                String msg = "§7Távolság (" + p.getName() + "): §a" + dist;
                event.getPlayer().sendMessage(msg);
                if(Y1 < Y2) {
                    event.getPlayer().sendTitle("§a↡", "§7(Le)", 0, 255, 0);
                }

                if(Y2 < Y1) {
                    event.getPlayer().sendTitle("§a↟", "§7(Fel)", 0, 255, 0);
                }

                if(Y2 == Y1) {
                    event.getPlayer().sendTitle("§a=", "§7(Egyenlő)", 0, 255, 0);
                }

                event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(msg));
            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        String format = "&7({level}) &e{name} &7▶ &f{msg}";
        Player player = e.getPlayer();

        e.setFormat(ChatColor.translateAlternateColorCodes('&',
                format
                        .replace("{level}", player.getLevel() + "")
                .replace("{name}", player.getDisplayName())
                .replace("{msg}", e.getMessage())
        ));
    }

    public boolean view = true;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd,String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("compass")) {
            Player player = (Player) sender;
            if (args.length == 0) {
                String name = "" + getConfig().getString("Target." + ".Name");
                sender.sendMessage("§7Targeted player: §a" + name);
                sender.sendMessage("");
                sender.sendMessage("§6Plugin by §ekbalu, Bugmaster555");
                sender.sendMessage("");
                sender.sendMessage("§6Parancsok:");
                sender.sendMessage("§6/c settarget <név> §e- Target beállítása");
                sender.sendMessage("§6/c reload §e- Config fájl újratöltése");
                sender.sendMessage("§6/c hölp §e- Parancsok megnézése");
                sender.sendMessage("§6/sun §e- Eső elállítása");
                sender.sendMessage("§6/c toggle §e- Actianbar be- vagy kikapcsolása");
                if (!getConfig().contains("Target." + ".Name")) {
                    getConfig().set("Target." + ".Name", 0);
                    saveConfig();
                }
            }

            else if (args[0].equalsIgnoreCase("reload")) {
                reloadConfig();
                sender.sendMessage("§aConfig fájl sikeresen újratöltve!");
            }

            else if (args[0].equalsIgnoreCase("settarget")) {
                getConfig().set("Target." + ".Name", args[1]);
                saveConfig();
                sender.sendMessage("§7Target kijelölve: §a" + args[1]);
            }

            else if (args[0].equalsIgnoreCase("toggle")) {
                if(view == true) {
                    getConfig().set("Players." + sender.getName() + ".Status", true);
                    saveConfig();
                    sender.sendMessage("§aTávolság jelölés bekapcsolva");
                    view = false;
                } else {
                    view = true;
                    sender.sendMessage("§cTávolság jelölés kikapcsolva");
                    getConfig().set("Players." + sender.getName(), null);
                    //getConfig().set("Players." + sender.getName() + ".Status", false);
                    saveConfig();
                }

            }

            else if (args[0].equalsIgnoreCase("hölp") || args[0].equalsIgnoreCase("help")) {
                sender.sendMessage("§6Plugin by §ekbalu, Bugmaster555");
                sender.sendMessage("");
                sender.sendMessage("§6Parancsok:");
                sender.sendMessage("§6/c settarget <név> §e- Target beállítása");
                sender.sendMessage("§6/c reload §e- Config fájl újratöltése");
                sender.sendMessage("§6/c hölp §e- Parancsok megnézése");
                sender.sendMessage("§6/sun §e- Eső elállítása");
                sender.sendMessage("§6/c toggle §e- Actianbar be- vagy kikapcsolása");
            }
        }

        if(cmd.getName().equalsIgnoreCase("sun")) {
            Player player = (Player) sender;
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "weather clear");
            Bukkit.broadcastMessage("§eEső elállítva! §6(" + sender.getName() + "§6)");
        }

        return true;
    }
}
