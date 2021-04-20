package me.RoboBoy99.Raft.IslandProfile;

import java.util.ArrayList;		
import java.util.List;

import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import me.RoboBoy99.Raft.Raft;
import net.md_5.bungee.api.ChatColor;


public class IslandProfileData {

	WorldCreator world;
	List<Player> members;
	Player pl;
	
	boolean open;
	
	Location spawnpoint;
	@SuppressWarnings("deprecation")
	public IslandProfileData(Player player, boolean open, List<Player> members){
		
		this.pl = player;
		this.open = open;
		this.members = members = new ArrayList<>();
		world = new WorldCreator(player.getName());
		world.generateStructures(false);	
		//world.seed(459556876156685819);
		world.createWorld();
		spawnpoint = Raft.getInstance().getServer().getWorld(player.getName()).getSpawnLocation();
		player.teleport(spawnpoint);
		player.sendTitle(ChatColor.RED + "Loading Raft...", ChatColor.GRAY + "Please be patient :)");
		
		
	}
	


	public String getOwner() { return pl.getName(); }
	
	public WorldCreator getWorld() { return world; }
	
	public Location getWorldLocation() { return spawnpoint; }
	
	public String getWorldName() { return world.name(); }
	
	public List<Player> getMembers() { return members; }
	
	public boolean getOpen() {return open ;}
	
	public void removeMember(Player player) { members.remove(player); }
	
	
	public void addMembers(Player player) { members.add(player); }

	
}
