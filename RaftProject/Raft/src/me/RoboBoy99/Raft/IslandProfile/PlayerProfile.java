package me.RoboBoy99.Raft.IslandProfile;

import java.util.UUID;

public class PlayerProfile {

	UUID IslandOwner;
	
	UUID player;
	
	boolean hasRaft;
	
	boolean gui;
	
	public PlayerProfile(UUID IslandOwner, UUID player, boolean hasRaft, boolean gui) {
		
		this.hasRaft = hasRaft;
		this.gui = gui;
		this.IslandOwner = IslandOwner;
		this.player = player;
	}
	
	public UUID getIslandOwner() { return IslandOwner; }
	
	void setIslandOwner(UUID Io) { IslandOwner = Io; }
		
	public UUID getPlayer() { return player; }
	
	public boolean hasRaft() { return hasRaft; }
	
	void setHasRaft(boolean hr) { hasRaft = hr; }
	
	public boolean gui() { return gui; }
	
	void setGui(boolean g) { gui = g; }
}
