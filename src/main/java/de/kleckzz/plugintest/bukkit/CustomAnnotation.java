package de.kleckzz.plugintest.bukkit;

import de.kleckzz.event.CallEvent;
import de.kleckzz.event.EventHandler;
import de.kleckzz.event.Listener;

public class CustomAnnotation implements Listener {

    @EventHandler
    public void onFirstCall(CallEvent e) {
        System.out.println("Der erste CallEvent wurde getriggert mit dem Int: " + e.getInt());
    }
    @EventHandler
    public void onSecondCall(CallEvent e) {
        System.out.println("Der zweite CallEvent wurde getriggert mit den Int: " + e.getInt());
    }
}
