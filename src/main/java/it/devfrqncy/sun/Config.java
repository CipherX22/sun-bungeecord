package it.devfrqncy.sun;

import com.google.common.io.ByteStreams;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;


import java.io.*;
import java.nio.file.Files;

public class Config {
    private boolean AntiVPN;
    private boolean AntiProxy;
    private boolean PhoneConnection;
    private boolean MacAddressWhitelis;
    private boolean PremiumBypass;
    //mysql
    private int port;
    private String host;
    private String dbname;
    private String username;
    private String password;
    private boolean useSSL;
    private boolean autoReconnect;


    public Config(){
        SUN instance = SUN.getInstance();
        try {
            if (!instance.getDataFolder().exists()) {
                instance.getDataFolder().mkdirs();
            }
            File file = new File(instance.getDataFolder(), "config.yml");
            if (!file.exists()) {
                InputStream in = instance.getResourceAsStream("config.yml");
                Files.copy(in, file.toPath());
            }
            Configuration configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
            AntiVPN = configuration.getBoolean("AntiVPN");
            AntiProxy = configuration.getBoolean("AntiProxy");
            PhoneConnection = configuration.getBoolean("PhoneConnection-disabled");
            MacAddressWhitelis = configuration.getBoolean("mac-address-whitelis");
            PremiumBypass = configuration.getBoolean("premium-bypass");

            port = configuration.getInt("port");
            host = configuration.getString("host");
            dbname = configuration.getString("dbname");
            username = configuration.getString("username");
            password = configuration.getString("password");
            useSSL = configuration.getBoolean("useSSL");
            autoReconnect = configuration.getBoolean("autoReconnect");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean getAntiVPN(){ return AntiVPN; }
    public boolean getAntiProxy(){ return AntiProxy; }
    public boolean getPremiumBypass() { return PremiumBypass; }
    public String getPassword() { return password; }
    public String getDBname() { return dbname; }
    public int getPort() { return port; }
    public String getHost() { return host; }
    public String getUsername() { return username; }
    public boolean getUseSSL() { return useSSL; }
    public boolean getAutoReconnect() { return autoReconnect; }



}
