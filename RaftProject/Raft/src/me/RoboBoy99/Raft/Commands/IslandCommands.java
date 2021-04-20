package me.RoboBoy99.Raft.Commands;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.RoboBoy99.Raft.Raft;
import me.RoboBoy99.Raft.IslandProfile.IslandProfileManager;

public class IslandCommands implements CommandExecutor {

	IslandProfileManager isM = Raft.getInstance().isM;
	@SuppressWarnings("deprecation")
	@Override
	 public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!cmd.getName().equalsIgnoreCase("Raft"))
            return false;
        if (!(sender instanceof Player))
            return false;
 
        Player player = (Player) sender;
  
        if(args.length == 0) {
        	isM.getRaftInfo(player);
            return true;
        }
        
        if(args[0].equalsIgnoreCase("create")) {
        	if(isM.CheckIfHasIsland(player)) {
        		
        	} else {
            	player.sendMessage(ChatColor.GRAY + "World is being created, Please wait...");
            	isM.CreateIsland(player);
            	player.sendMessage(ChatColor.GREEN + "World has been created!");
            	return true;
        	}
        }
        
        if(args[0].equalsIgnoreCase("invite")) {
        	isM.Size(player, Bukkit.getPlayer(args[1]));
        	return true;
        }
        
        if(args[0].equalsIgnoreCase("kick")) {
        	isM.kickMember(player, Bukkit.getPlayer(args[1]));
        	return true;
        }
        
        if(args[0].equalsIgnoreCase("check")) {
        	player.sendMessage("Profile " + isM.Profile.toString());
        	player.sendMessage("Island " + isM.Island.toString());
        	return true;
        }
        if(args[0].equalsIgnoreCase("accept")) {
			isM.accept(player, Bukkit.getPlayer(args[1]));
        	return true;
        }
        
        
        if(player.hasPermission("Raft.admin")) {
            if(args[0].equalsIgnoreCase("worlds")) {
            	player.sendMessage(ChatColor.GRAY + "" + ChatColor.UNDERLINE + "Worlds List");

            	for(World w : Bukkit.getWorlds()) {
            		//add players numbers later on
            		player.sendMessage(ChatColor.GRAY + "> " + ChatColor.GREEN + w.getName());
            	}   
            }
            if(args[0].equalsIgnoreCase("Find")) {
            	isM.TPPlayer(Bukkit.getOfflinePlayer(args[1]), player);
            }
        } else {
        	player.sendMessage(ChatColor.RED + "you don't have permission for this cmd");
        }
		return false;
	}
}