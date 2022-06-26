package de.kleckzz.plugintest.bukkit;

import de.kleckzz.event.CallEvent;
import de.kleckzz.event.EventManager;
import de.kleckzz.plugintest.bukkit.socket.Socket;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        new PluginMessage(this);

        new Socket().startServer("localhost", 8080);
        new Socket().startClient("localhost", 8081);

        EventManager.register(new CustomAnnotation());
        EventManager.call(new CallEvent());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this);
    }



}
