package me.RoboBoy99.Raft;


	
import java.util.UUID;	
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import org.bukkit.plugin.java.JavaPlugin;


import me.RoboBoy99.Raft.Commands.IslandCommands;
import me.RoboBoy99.Raft.Events.PlayerJoin;
import me.RoboBoy99.Raft.Files.IslandSaving;
import me.RoboBoy99.Raft.Files.SavingPlayerProfile;
import me.RoboBoy99.Raft.Files.WorldFile;
import me.RoboBoy99.Raft.IslandProfile.IslandProfileManager;

public class Raft extends JavaPlugin {
	
	private static Raft INSTANCE;
    
	public static Raft getInstance() {
		return INSTANCE;
	}
	
	public IslandProfileManager isM;
	public WorldFile WorldFile;
	public IslandSaving Islandsaving;
	public SavingPlayerProfile pf;

	
	@Override
	public void onEnable() { 
		INSTANCE = this;
        isM = new IslandProfileManager();	
        WorldFile = new WorldFile();
        Islandsaving = new IslandSaving();
        pf = new SavingPlayerProfile();
		RegisterCommands();
		RegisterEvents();
	
		
			
	}
		
	public void onDisable() {
		saveWorlds();

	}
		
    public void saveWorlds() {
    	for(UUID pl : isM.Island.keySet()) {
        	for(World s : Bukkit.getWorlds()) {
        		WorldFile.getWorldConfig().set(Bukkit.getPlayer(pl).getName(), s);
        	}
    	}
    	WorldFile.saveConfig();
    }
    
	public void createWorlds() {
		for(UUID pl : isM.Island.keySet()) {
			if(Bukkit.getWorld(Bukkit.getPlayer(pl).getName()) == null) {
				Bukkit.createWorld((WorldCreator) WorldFile.getWorldConfig().get(Bukkit.getPlayer(pl).getName()));
			}
		}
	}
	public void RegisterCommands() {
		getCommand("Raft").setExecutor(new IslandCommands());
	}
	
	
	public void RegisterEvents() {
		getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
	}
		
    

}
	
	
