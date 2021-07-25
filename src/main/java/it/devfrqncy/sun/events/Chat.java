package it.devfrqncy.sun.events;

import it.devfrqncy.sun.SUN;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Chat implements Listener {
    @EventHandler
    public void onChat(ChatEvent event){
        //System.out.print("Chat "+event.getSender());
        // se un player contenuto nei lockedplayers esegue un comando o scrive un messaggio diverso da /login o /register blocca cancella l'evento
        if(SUN.getLockedPlayers().contains(event.getSender().toString())){
            if(!(event.getMessage().startsWith("/login") || event.getMessage().startsWith("/l") || event.getMessage().startsWith("/register") || event.getMessage().startsWith("/r"))){
                event.setCancelled(true);
            }
        }
    }
}
