package it.devfrqncy.sun;

import it.devfrqncy.sun.commands.PlayerCommands;
import it.devfrqncy.sun.database.MySql;
import it.devfrqncy.sun.events.*;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import java.sql.Connection;
import java.util.*;

public final class SUN extends Plugin {

    private static SUN instance;
    public static SUN getInstance() { return instance; }

    private static Config config;
    public static Config getConfigYml() { return config; }

    private static LinkedList<String> premiumCache;
    public static LinkedList<String> getPremiumCache() { return premiumCache; }

    private static LinkedList<String> lokedPlayers;
    public static LinkedList<String> getLockedPlayers() { return lokedPlayers; }



    //*********************** VPN ********************************//

    private static List<String> whitelistPlayers;
    public static List<String> getWhitelistPlayers() { return  whitelistPlayers; }

    private static Map<String, Integer> tempBlockIpList;
    public static Map<String, Integer> getTempBlockIpList() { return  tempBlockIpList; }

    // Connection per il MySql
    private static Connection con;
    public static Connection getConnection() { return con; }


    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        config = new Config();

        lokedPlayers = new LinkedList<>();
        premiumCache = new LinkedList<>();
        tempBlockIpList = new HashMap<>();
        whitelistPlayers = new LinkedList<>();

        // connessione al database e caricamento tabelle
        if(!config.getPassword().equals("password") && !config.getDBname().equals("dbname")){
            MySql database = new MySql(config.getHost(),config.getPort(),config.getDBname(),config.getUsername(),config.getPassword(),config.getUseSSL(),config.getAutoReconnect());
            con = database.getConnection();
        }

        // registra eventi
        PluginManager pm = getProxy().getPluginManager();
        pm.registerListener(this, new ClientConnect());     // Evento chiamato per rappresentare una connessione client iniziale.
        pm.registerListener(this, new PlayerHandshake());   // Evento chiamato per rappresentare un giocatore che per primo ha reso noti la propria presenza e username. (sia se entra che se esce)
        pm.registerListener(this, new PreLogin());          // Evento chiamato per rappresentare un giocatore che per primo ha reso noti la propria presenza e username.
        pm.registerListener(this, new Login());             // Evento chiamato per rappresentare un giocatore che effettua il login.
        pm.registerListener(this, new PostLogin());         // Evento chiamato non appena una connessione ha un ProxiedPlayered è pronta per essere collegata a un server.
        pm.registerListener(this, new PlayerDisconnect());  // Chiamato quando un giocatore ha lasciato il proxy, non è sicuro chiamare alcun metodo che esegue un'azione sull'istanza del giocatore passato.


        pm.registerListener(this, new Chat());              // Evento chiamato quando un giocatore invia un messaggio a un server.
        //pm.registerListener(this, new PermissionCheck());       // Chiamato quando viene controllata l'autorizzazione di un CommandSender.

        //pm.registerCommand(this,new PlayerCommands());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new PlayerCommands(this.toString()));
        getProxy().getPluginManager().registerCommand(this, new PlayerCommands());
/*

        pm.registerListener(this, new ProxyPing());         // Chiamato quando viene eseguito il ping del proxy con il pacchetto 0xFE dall'elenco dei server.
        pm.registerListener(this, new ProxyReload());       // Chiamato quando qualcuno ricarica BungeeCord
        pm.registerListener(this, new ServerConnected());   // questo evento viene chiamato quando una connessione a un server è completamente operativa e sta per cedere il controllo della sessione al giocatore.
        pm.registerListener(this, new ServerConnect());     // questo evento viene chiamato quando una connessione a un server è completamente operativa e sta per cedere il controllo della sessione al giocatore.
*/



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
