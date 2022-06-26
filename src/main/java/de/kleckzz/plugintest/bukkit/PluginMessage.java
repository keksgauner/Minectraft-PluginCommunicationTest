package de.kleckzz.plugintest.bukkit;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class PluginMessage implements PluginMessageListener, Listener {
    private Plugin plugin;

    public PluginMessage(Plugin plugin) {
        this.plugin = plugin;

        // Login event
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        // OutgoingChannel from Proxy
        plugin.getServer().getMessenger().registerIncomingPluginChannel(plugin, "pluginchannel:getproxymessage", this);
        // OutgoingChannel to Proxy
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "pluginchannel:sendproxymessage");
    }
    @Override
    public synchronized void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if(channel.equals("pluginchannel:getproxymessage")) {
            System.out.println("Reseaving Tag: Channel: " + channel);
            ByteArrayDataInput in = ByteStreams.newDataInput(message);
            String first = in.readUTF();
            player.sendMessage(first);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("sendMeAMessage");

        // Nur bei einer Player Connection
        e.getPlayer().sendPluginMessage(plugin, "pluginchannel:sendproxymessage", out.toByteArray());
        System.out.println("Sending the Message pluginchannel:sendproxymessage with sendMeAMessage");
    }
}
