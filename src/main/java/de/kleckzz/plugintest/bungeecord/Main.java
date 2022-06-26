package de.kleckzz.plugintest.bungeecord;

import de.kleckzz.event.CallEvent;
import de.kleckzz.event.EventManager;
import de.kleckzz.plugintest.bukkit.CustomAnnotation;
import de.kleckzz.plugintest.bungeecord.socket.Socket;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        new PluginMessage(this);

        new Socket().startServer("localhost", 8081);
        new Socket().startClient("localhost", 8080);

        EventManager.register(new CustomAnnotation());
        EventManager.call(new CallEvent());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}