package de.kleckzz.plugintest.bukkit.socket;

import java.io.IOException;

public class Socket {
    public void startServer(String address, int port) {
        Runnable server = new Runnable() {
            @Override
            public void run() {
                try {
                    new SocketServer(address, port);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };
        new Thread(server, "server1").start();
    }
    public void startClient(String address, int port) {
        Runnable client = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    new SocketClient(address, port);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        new Thread(client, "client1").start();
    }
}
