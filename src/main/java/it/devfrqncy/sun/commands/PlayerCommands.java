package it.devfrqncy.sun.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class PlayerCommands extends Command {


    public PlayerCommands(String name, String permission, String... aliases) {
        super("commands", "l", aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

    }
    /*public PlayerCommands() {
        super("player commands");
    }*/
}
