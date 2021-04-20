package me.RoboBoy99.Raft.Files;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.RoboBoy99.Raft.Raft;
import me.RoboBoy99.Raft.IslandProfile.IslandProfileData;
import me.RoboBoy99.Raft.IslandProfile.IslandProfileManager;

public class IslandSaving {
	
	IslandProfileManager isM = Raft.getInstance().isM;
	
	public void SaveIslandProfile(Player p) throws IOException {
        File f = new File(Raft.getInstance().getDataFolder().getAbsolutePath(), p.getName() + ".yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        c.set("Owner: ", isM.Island.get(p.getUniqueId()).getOwner());
        c.set("Members: ", isM.Island.get(p.getUniqueId()).getMembers());
        c.set("Open: ", isM.Island.get(p.getUniqueId()).getOpen()); 	
        c.save(f); 
    }

    public void LoadIslandProfile(Player p) throws IOException {
        File f = new File(Raft.getInstance().getDataFolder().getAbsolutePath(), p.getName() + ".yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        UUID owner = (UUID) c.get("Owner: ");
        boolean open = (boolean) c.get("Open: ");
        isM.Island.put(p.getUniqueId(), new IslandProfileData(Bukkit.getPlayer(owner).getPlayer(), open, null));
    }
}
