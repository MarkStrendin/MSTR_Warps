package ca.strendin.MSTR_Warps;

import org.bukkit.entity.Player;


public class MSTR_Permissions {
    
    public static boolean canUseWarps(Player player) {
        return player.hasPermission("mstr_warps.warp");
    }
    
    
    public static boolean canCreateWarps(Player player) {
        return player.hasPermission("mstr_warps.createwarps");
    }
    
}
