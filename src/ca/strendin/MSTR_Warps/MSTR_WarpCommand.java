package ca.strendin.MSTR_Warps;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MSTR_WarpCommand implements CommandExecutor {

	public MSTR_WarpCommand(MSTR_Warps mstr_Warps) {
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] args) {
		
		if (sender instanceof Player) {
			Player player = (Player)sender;
			if (MSTR_Permissions.canUseWarps(player)) {
				// Find the warp				
				// Get the name
				if (args.length > 0) {
					String warpName = args[0];
					
					MSTR_Warp specifiedWarp = MSTR_WarpHandler.getWarpNamed(MSTR_WarpHandler.sanitizeInput(warpName)); 
					
					if (specifiedWarp != null) {
						MSTR_Warper.warpPlayer(player,specifiedWarp);
					} else {
						MSTR_Comms.sendError(player, "Warp \""+MSTR_WarpHandler.sanitizeInput(warpName)+"\" does not exist!");
						
					}
				} else {
					MSTR_Comms.sendError(player, "You must specify a warp name");
					MSTR_Comms.sendError(player, "Usage: /warp <warp name>");
				}
							
				
				
			}
		} else {
			MSTR_Comms.sendtoCommandSender(sender, "Player command only!");
		}
		
		return true;
	}
	
	

}
