package me.RoboBoy99.Raft.Files;

import java.io.File;	
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.RoboBoy99.Raft.Raft;
import me.RoboBoy99.Raft.IslandProfile.IslandProfileManager;
import me.RoboBoy99.Raft.IslandProfile.PlayerProfile;

public class SavingPlayerProfile {
	
	IslandProfileManager isM = Raft.getInstance().isM;
	
	public void SaveProfile(Player p)  {
		UUID uuid = p.getUniqueId();
        File f = new File(Raft.getInstance().getDataFolder().getAbsolutePath(), p.getName() + ".yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        c.set("Player", isM.Profile.get(uuid).getPlayer());
        c.set("IslandOwner", isM.Profile.get(uuid).getIslandOwner());
        c.set("HasRaft", isM.Profile.get(uuid).hasRaft()); 	
        c.set("Gui", isM.Profile.get(uuid).gui()); 
        try {
			c.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }

    public void LoadProfile()  {
    	for(Player p : Bukkit.getOnlinePlayers()) {
            File f = new File(Raft.getInstance().getDataFolder().getAbsolutePath(), p.getName() + ".yml");
            FileConfiguration c = YamlConfiguration.loadConfiguration(f);
            UUID player = (UUID) c.get("Player");
            UUID IslandOwner = (UUID) c.get("IslandOwner");
            boolean hasRaft = (boolean) c.get("HasRaft");
            boolean gui = (boolean) c.get("Gui");
            isM.Profile.put(p.getUniqueId(), new PlayerProfile(IslandOwner, player, hasRaft, gui));
    	}
    }
}
