package it.devfrqncy.sun.events;

import it.devfrqncy.sun.SUN;
import it.devfrqncy.sun.premium.PremiumCheck;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;


public class PreLogin implements Listener {
    @EventHandler
    public void onPreLogin(PreLoginEvent event){
        System.out.print("PreLoginEvent");

        String playername = event.getConnection().getName();
        System.out.print(playername);
        // se il nome del player e' di un account premium nella cache setta premium


        /*if(new PremiumCheck(playername).getResult()){
            if(SUN.getPremiumCache().contains(playername)){
                event.getConnection().setOnlineMode(true);
            } else {
                SUN.getPremiumCache().add(playername);
                event.setCancelled(true);
                event.setCancelReason(new TextComponent("rientra"));
            }
        }*/


        if(SUN.getConfigYml().getPremiumBypass()){
            if(SUN.getPremiumCache().contains(playername)){
                event.getConnection().setOnlineMode(true);
                System.out.print("premium DIRECT");
            }else{
                System.out.print(new PremiumCheck(playername).getResult());
                if(new PremiumCheck(playername).getResult()){
                    SUN.getPremiumCache().add(playername);
                    event.getConnection().setOnlineMode(true);
                    System.out.print("premium");
                }else{
                    System.out.print("sp");
                }
            }
        }





        //VPN
        if(SUN.getConfigYml().getAntiVPN() || SUN.getConfigYml().getAntiProxy()){
            String ip = event.getConnection().getSocketAddress().toString().split(":")[0].replace("/","");
            // se il player non e' nella whitelist controlla se e' da bloccare per VPN
            if(!SUN.getWhitelistPlayers().contains(event.getConnection().getUniqueId())){
                if(SUN.getTempBlockIpList().containsKey(ip)){
                    switch (SUN.getTempBlockIpList().get(ip)){
                        case 0:
                            event.setCancelled(true);
                            event.setCancelReason(new TextComponent("errore controllo VPN"));
                            break;
                        case 1:
                            event.setCancelled(true);
                            event.setCancelReason(new TextComponent("uso VPN"));
                            break;
                        case 2:
                            event.setCancelled(true);
                            event.setCancelReason(new TextComponent("uso proxy"));
                            break;
                    }
                    SUN.getTempBlockIpList().remove(ip);
                    return;
                }
            }

        }

    }
}
