package it.devfrqncy.sun.events;


import it.devfrqncy.sun.SUN;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;


public class Login implements Listener {
    @EventHandler
    public void onLogin(LoginEvent event){
        System.out.print("LoginEvent");
        SUN.getLockedPlayers().add(event.getConnection().getName());
        System.out.print(SUN.getLockedPlayers());


    }
}
