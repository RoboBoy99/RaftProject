package me.RoboBoy99.Raft.Events;



import org.bukkit.entity.Player;	
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.RoboBoy99.Raft.Raft;
import me.RoboBoy99.Raft.IslandProfile.IslandProfileManager;

public class PlayerJoin implements Listener {

	IslandProfileManager Ism = Raft.getInstance().isM;
	
	@EventHandler	
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		
		if(!Ism.Profile.containsKey(player.getUniqueId())) {
			Ism.CreateProfile(player);
		}
    //	if(Ism.Island.containsKey(player.getUniqueId())) {
    	//	player.sendMessage("1");
    //	} else {
    //		player.sendMessage("2");
     //   	Ism.CreateIsland(player);
    //    	player.sendMessage("2.0");
     //   	player.sendMessage(ChatColor.GREEN + "works!");
    //	}
	}
}
