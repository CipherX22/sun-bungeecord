package it.devfrqncy.sun.events;

import it.devfrqncy.sun.IPapi.IPapi;
import it.devfrqncy.sun.SUN;
import net.md_5.bungee.api.event.ClientConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ClientConnect implements Listener {

    @EventHandler
    public void onClientConnect(ClientConnectEvent event){
        System.out.print("ClientConnectEvent");

        //VPN
        //if(SUN.getConfigYml().getAntiVPN()){

        String ip = event.getSocketAddress().toString().split(":")[0].replace("/","");
        IPapi connectioninfo = new IPapi(ip);

        if(connectioninfo.getUser().get("status").getAsString().equals("fail")){
            SUN.getTempBlockIpList().put(ip,0);
            return;
        }
        // controllo whitelist vpn
        if(connectioninfo.getUser().get("hosting").getAsBoolean() && SUN.getConfigYml().getAntiVPN()){
            SUN.getTempBlockIpList().put(ip,1);
            return;
        }
        if(connectioninfo.getUser().get("proxy").getAsBoolean() && SUN.getConfigYml().getAntiProxy()){
            SUN.getTempBlockIpList().put(ip,2);
            return;
        }
        //}
    }
}
