package it.devfrqncy.sun;

import java.util.UUID;

public class Player {
    private UUID uuid;
    private String name;
    private String ip;

    public Player(UUID uuid,String name, String ip){
        this.uuid = uuid;
        this.name = name;
        this.ip = ip;
    }

    public boolean equals(Player p){
        return p.getUuid().equals(this.uuid);
    }

    public UUID getUuid(){ return uuid; }
    public String getName(){ return name; }
    public String getIP(){ return ip; }

}
