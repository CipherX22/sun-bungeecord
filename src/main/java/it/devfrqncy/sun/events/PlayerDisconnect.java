package it.devfrqncy.sun.events;


import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerDisconnect implements Listener {
    @EventHandler
    public void onPlayerDisconnect(PlayerDisconnectEvent event){
        System.out.print("PlayerDisconnectEvent");
    }
}
