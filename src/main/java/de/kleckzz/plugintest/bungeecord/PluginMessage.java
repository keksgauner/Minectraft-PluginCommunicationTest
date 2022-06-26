package de.kleckzz.plugintest.bungeecord;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.io.*;

public class PluginMessage implements Listener {
    private Plugin plugin;

    public PluginMessage(Plugin plugin) {
        this.plugin = plugin;

        plugin.getProxy().getPluginManager().registerListener(plugin, this);
        // IncomingPluginChannel from Bukkit
        plugin.getProxy().registerChannel("pluginchannel:sendproxymessage");
    }

    @EventHandler
    public void onPluginMessage(PluginMessageEvent e) {
        // Incomming Tag
        if (e.getTag().equalsIgnoreCase("pluginchannel:sendproxymessage")) {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(e.getData()));
            System.out.println("Reseaving Tag: " + e.getTag());
            try {
                String channel = in.readUTF();
                if(channel.equals("sendMeAMessage")) {
                    ServerInfo server = plugin.getProxy().getPlayer(e.getReceiver().toString()).getServer().getInfo();
                    sendToBukkit(server, "Hello");
                }
            }catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void sendToBukkit(ServerInfo server, String message) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(stream);
        try {
            out.writeUTF(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        server.sendData("pluginchannel:getproxymessage", stream.toByteArray());
        System.out.println("Sending the Message over pluginchannel:getproxymessage");
    }
}
