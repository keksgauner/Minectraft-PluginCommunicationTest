package de.kleckzz.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EventManager {

    private static List<Listener> listeners;

    static {
        listeners = new ArrayList<>();
    }

    public static void register(Listener l) {
        listeners.add(l);
    }

    public static void unregister(Listener l) {
        listeners.remove(l);
    }

    public static void call(Event event) {
        event.getClass();

        Iterator<Listener> it = listeners.iterator();
        // Alle Listener iterieren
        while (it.hasNext()) {
            Listener l = it.next();

            // Alle Methoden iterieren
            for (Method m : l.getClass().getMethods()) {

                // Prüfen, ob Annotation gesetzt
                if (m.isAnnotationPresent(EventHandler.class)) {

                    // Prüfen, ob der erste Parameter mit event (siehe Parameter) kompatibel ist
                    if (m.getParameterTypes().length == 1 && m.getParameterTypes()[0].isAssignableFrom(event.getClass())) {
                        try {
                            m.invoke(l, event);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
