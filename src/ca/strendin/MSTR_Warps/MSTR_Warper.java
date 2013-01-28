package ca.strendin.MSTR_Warps;

import org.bukkit.entity.Player;

public class MSTR_Warper {
	
	
	public static void warpPlayer(Player player, MSTR_Warp warp) {
		MSTR_Comms.sendInfo(player, "Warping to \""+warp.getName()+"\"");
		MSTR_SpecialEffects.poofAtLocation(player.getLocation());
		player.teleport(warp.getLocation());
		MSTR_SpecialEffects.poofAtLocation(warp.getLocation());
		
	}

	
	/*
	 * Get the next 5 blocks below the destination - if they are air, create a glass platform to stand on
	 */
	
	
}
