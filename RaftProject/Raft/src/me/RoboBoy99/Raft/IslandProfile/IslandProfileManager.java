package me.RoboBoy99.Raft.IslandProfile;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;	
import java.util.Map;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;

import me.RoboBoy99.Raft.Raft;
import me.RoboBoy99.Raft.Files.SavingPlayerProfile;
import net.md_5.bungee.api.ChatColor;


public class IslandProfileManager {

	public Map<UUID, IslandProfileData> Island;
	public Map<UUID, PlayerProfile> Profile; //>> U 	UID is the player, Player is the island owner
	public Map<UUID, UUID> Invites; //Add bukkitrunable and delete it after a certian time frame 
	
	public IslandProfileManager(){
		Island = new HashMap<>();
		Profile = new HashMap<>();
		Invites = new HashMap<>();
	}
	
    public void unloadWorld(Player pl) {	
    	World world = Bukkit.getWorld(pl.getName());
    	File deleteFolder = world.getWorldFolder();
        if (!deleteFolder.exists() || !deleteFolder.isDirectory()) return;
            world.getPlayers().forEach(p -> p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation()));
            Bukkit.unloadWorld(pl.getName(), true);
            deleteWorld(deleteFolder);
    }
    
    public boolean deleteWorld(File path) {
        if(path.exists()) {
            File files[] = path.listFiles();
            for(int i=0; i<files.length; i++) {
                if(files[i].isDirectory()) {
                    deleteWorld(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return(path.delete());
  }
	
	public boolean CheckIfHasIsland(Player pl) {
		UUID uuid = pl.getUniqueId();
		if(Island.containsKey(uuid)) {
			pl.sendMessage(ChatColor.RED + "You already have a raft!");
			return true;
		} else {
		}
		return false; 
	}
	

	public void CreateIsland(Player pl) {
		UUID uuid = pl.getUniqueId();
		List<Player> f = new ArrayList<>();
		Island.put(uuid, new IslandProfileData(pl, true, f));
		Profile.replace(uuid, new PlayerProfile(uuid, uuid, true, false));
	}
	
	public void CreateProfile(Player pl)  {
		SavingPlayerProfile pf = Raft.getInstance().pf;
		UUID uuid = pl.getUniqueId();
		Profile.put(uuid, new PlayerProfile(uuid, uuid, false, false));
		pf.SaveProfile(pl);

	}
	
	public void getRaftInfo(Player pl) {
		UUID uuid = pl.getUniqueId();
		
		if(Profile.get(uuid).hasRaft() == true) {
			pl.sendMessage(ChatColor.GREEN + "YES!");
		} else {
			pl.sendMessage(ChatColor.RED + "NO!");
		}
		if(!Island.containsKey(uuid)) {
			if(Profile.get(uuid).hasRaft()) {
				pl.sendMessage("Owner: " + Bukkit.getPlayer(Profile.get(uuid).getIslandOwner()).getName());
				pl.sendMessage("Player: " + Bukkit.getPlayer(Profile.get(uuid).getPlayer()).getName());
			}
		}
		if(Island.containsKey(uuid)) {
			pl.sendMessage("Owner: " + Island.get(uuid).getOwner());
			pl.sendMessage("Members " + Island.get(uuid).getMembers());
		}
	}
	
	public void TPPlayer(OfflinePlayer target, Player player) {
		UUID Targetuuid = target.getUniqueId();
		if(!Island.containsKey(Targetuuid)) {
			if(Profile.get(Targetuuid).hasRaft()) {
				player.teleport(Island.get(Profile.get(Targetuuid).getIslandOwner()).getWorldLocation());
				player.sendMessage(ChatColor.GREEN + "You teleported to " + ChatColor.GRAY + target.getName() + ChatColor.GREEN + " Raft");

			} else {
				player.sendMessage(ChatColor.RED + "this player doesnt have a raft!");
			}
		}
		if(Island.containsKey(Targetuuid)) {
			player.teleport(Island.get(Targetuuid).getWorldLocation());
			player.sendMessage(ChatColor.GREEN + "You teleported to " + ChatColor.GRAY + target.getName() + ChatColor.GREEN + " Raft");
		}
	}
	

	
	public void accept(Player pl, Player target) {
		UUID Targetuuid = target.getUniqueId();
		UUID uuid = pl.getUniqueId();
		if(Island.containsKey(uuid)) {
			if(pl.getWorld().getName() == Island.get(uuid).getWorldName()) {
				//pl.teleport(Raft.getInstance().getServer().getWorld("world").getSpawnLocation());
				Profile.get(uuid).setIslandOwner(Targetuuid);
				Profile.get(uuid).setHasRaft(true);
				Island.get(Targetuuid).members.add(pl);
				Island.remove(uuid);
				unloadWorld(pl);
			} else {
				Profile.get(uuid).setIslandOwner(Targetuuid);
				Profile.get(uuid).setHasRaft(true);
				Island.get(Targetuuid).members.add(pl);
				Island.remove(uuid);
				unloadWorld(pl);
			}
		} else {
			Profile.get(uuid).setIslandOwner(Targetuuid);
			Profile.get(uuid).setHasRaft(true);
			Island.get(Targetuuid).members.add(pl);
		}
	}
	
	public void kickMember(Player pl, Player target) {
		UUID uuid = pl.getUniqueId();
		UUID Targetuuid = target.getUniqueId();
		if(Island.containsKey(uuid)) {
			if(Profile.get(Targetuuid).getIslandOwner() == uuid) {
				Profile.get(Targetuuid).setIslandOwner(Targetuuid);
				Profile.get(Targetuuid).setHasRaft(false);
				Island.get(uuid).removeMember(target);
				target.sendMessage(ChatColor.RED + "You get kicked from your raft");
				pl.sendMessage(ChatColor.RED + "You kicked " + ChatColor.GRAY + target.getName());
			}
		}
	}
	
	public String Size(Player pl, Player target) {
		int Size = 0;
		
		for(UUID pla : Island.keySet())
		{	   
	        for (@SuppressWarnings("unused") Player p : Island.get(pla).getMembers()) 
	        {
	        	Size++;  
	        	
	        }
	        
		}
    	String SizeString = String.valueOf(Size).toString();
    	pl.sendMessage("Size " + SizeString);
    	return SizeString;
	}

}
