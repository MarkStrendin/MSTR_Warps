package ca.strendin.MSTR_Warps;

import java.io.Serializable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class MSTR_Warp implements Serializable{
	private static final long serialVersionUID = -1010528914494667312L;
	private String creator;	
	private String name;
	private int coordX;
	private int coordY;
	private int coordZ;
	private float coordYaw;
	private float coordPitch;
	private String coordWorld;
	
	
	public Location getLocation() {
		World world;
		Location returnMe;		
		try {
			world = Bukkit.getServer().getWorld(coordWorld);			
			returnMe = new Location(world, coordX, coordY, coordZ, coordYaw, coordPitch);			
			return returnMe;
		} catch (Exception i) {
			return null;
		}
	}
	
	public String getName() {
		return name;		
	}
	
	public String getCreator() {
		return creator;
	}
	
	public String getDescription() {
		return name + " ("+coordX+","+coordY+","+coordZ+" in world \""+coordWorld+"\")";
	}
	
	public MSTR_Warp(Player player, String warpName) {
		this.name = warpName;
		this.creator = player.getName();
		this.coordX = player.getLocation().getBlockX();
		this.coordY = player.getLocation().getBlockY();
		this.coordZ = player.getLocation().getBlockZ();
		this.coordYaw = player.getLocation().getYaw();
		this.coordPitch = player.getLocation().getPitch();
		this.coordWorld = player.getLocation().getWorld().getName();
	}
}
