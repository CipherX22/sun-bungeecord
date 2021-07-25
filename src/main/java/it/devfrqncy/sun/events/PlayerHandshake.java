package it.devfrqncy.sun.events;

import net.md_5.bungee.api.event.PlayerHandshakeEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerHandshake implements Listener {
    @EventHandler
    public void onPlayerHandshake(PlayerHandshakeEvent event){
        System.out.print("PlayerHandshakeEvent");

        //event.getConnection().getUniqueId()
        //System.out.println("aaaa"+event.getConnection().getSocketAddress());
        //System.out.println(ip);

    }
}
