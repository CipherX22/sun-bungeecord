package it.devfrqncy.sun;


import java.util.LinkedList;

public class LokedPlayers {
        private LinkedList<Player> list;

        public LokedPlayers(){
            list = new LinkedList<>();
        }

        public void addPlayer(Player p){
            //if(contains(p) instanceof Player)
            list.add(p);
        }

        public void removePlayer(Player p){
            list.remove(p);
        }

        public boolean contains(Player p){
            for(Player player: list){ if(p.equals(player)) return true; }
            return false;
        }

        public Player getPlayer(Player p){
            for(Player player: list){ if(p.equals(player)) return player; }
            return null;
        }
}
