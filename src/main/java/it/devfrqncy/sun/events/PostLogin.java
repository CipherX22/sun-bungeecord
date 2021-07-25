package it.devfrqncy.sun.events;

import it.devfrqncy.sun.SUN;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PostLogin implements Listener {
    @EventHandler
    public void onPostLogin(PostLoginEvent event){
        System.out.print("PostLoginEvent");

    }
}
