package me.koba1.chatevents;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Runnable implements Listener {

    Main plugin;

    public Runnable(Main plugin) {
        this.plugin = plugin;
    /*static Main plugin;

    public Runnable(Main instance) {
        plugin = instance;*/
    }

    private Main m = Main.getPlugin(Main.class);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        //if (!p.hasPlayedBefore()) {

        new BukkitRunnable() {
            @Override
            public void run() {
                Player p = event.getPlayer();

                if (!plugin.getConfig().contains("Players." + p.getName())) {
                    p.sendMessage(ChatColor.DARK_AQUA + "Honnan ismerted meg a szervert? §6Válassz!");
                    p.sendMessage(" ");
                    //if(!p.hasPlayedBefore()) {
                    TextComponent ismeres = new TextComponent();
                    ismeres.setText("Videóból");
                    ismeres.setColor(ChatColor.GOLD);
                    ismeres.setBold(false);
                    ismeres.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/videobolismertemmeg"));
                    ismeres.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Kattints a választáshoz").color(ChatColor.DARK_AQUA).italic(true).create()));
                    p.sendMessage(ismeres);

                    TextComponent ismeres2 = new TextComponent();
                    ismeres2.setText("Barátomtól");
                    ismeres2.setColor(ChatColor.GOLD);
                    ismeres2.setBold(false);
                    ismeres2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/baratomtolismertemmeg"));
                    ismeres2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Kattints a választáshoz").color(ChatColor.DARK_AQUA).italic(true).create()));
                    p.sendMessage(ismeres2);

                    TextComponent ismeres3 = new TextComponent();
                    ismeres3.setText("Hírdetésből");
                    ismeres3.setColor(ChatColor.GOLD);
                    ismeres3.setBold(false);
                    ismeres3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/hirdetesbolismertemmeg"));
                    ismeres3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Kattints a választáshoz").color(ChatColor.DARK_AQUA).italic(true).create()));
                    p.sendMessage(ismeres3);

                    TextComponent ismeres4 = new TextComponent();
                    ismeres4.setText("Egyéb");
                    ismeres4.setColor(ChatColor.GOLD);
                    ismeres4.setBold(false);
                    ismeres4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/egyebtolismertemmeg"));
                    ismeres4.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Kattints a választáshoz").color(ChatColor.DARK_AQUA).italic(true).create()));
                    p.sendMessage(ismeres4);
                }
            }
        }.runTaskLater(m, 120);
    }
}
