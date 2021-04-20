package me.RoboBoy99.Raft.Files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;


import me.RoboBoy99.Raft.Raft;

public class WorldFile {
	 private File worldFile = null;
	 private FileConfiguration worldConfig;
	 
	 public WorldFile() {
		 saveDefaultConfig();
	 }
	 public void reloadConfig() {
		 if(this.worldFile == null) {
			 this.worldFile = new File(Raft.getInstance().getDataFolder(), "Worlds.yml");
		 }
		 this.worldConfig = YamlConfiguration.loadConfiguration(this.worldFile);
		 InputStream worldStream = Raft.getInstance().getResource("Worlds.yml");
		 
		 if(worldStream != null) {
			 YamlConfiguration WorldC = YamlConfiguration.loadConfiguration(new InputStreamReader(worldStream));
			 this.worldConfig.setDefaults(WorldC);
		 }
	 }
	 
	 public FileConfiguration getWorldConfig() {
		if(this.worldConfig == null) 
			reloadConfig();
		
		return worldConfig;
	 }

    public void saveConfig() {
    	if(this.worldConfig == null || this.worldFile == null) {
    		return;
    	}
    	
    	try {
			this.worldConfig.save(this.worldFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void saveDefaultConfig() {
    	if(this.worldFile == null) {
    		this.worldFile = new File(Raft.getInstance().getDataFolder(), "Worlds.yml");
    		
    	}
    	if(!this.worldFile.exists()) {
    		Raft.getInstance().saveResource("Worlds.yml", false);
    	}
    }

}


