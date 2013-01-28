package ca.strendin.MSTR_Warps;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MSTR_RemoveWarpCommand implements CommandExecutor {

	public MSTR_RemoveWarpCommand(MSTR_Warps mstr_Warps) {}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] args) {
		
		if (sender instanceof Player) {
			Player player = (Player)sender;
			if (MSTR_Permissions.canCreateWarps(player)) {
				// Get the name
				if (args.length > 0) {					

					String warpName = args[0];
					
					MSTR_WarpHandler.removeWarp(player, MSTR_WarpHandler.sanitizeInput(warpName));					
					
				} else {
					MSTR_Comms.sendError(player, "You need to specify a name");
					MSTR_Comms.sendError(player, "Usage: /removewarp <name>");
				}
				
			} else {
				MSTR_Comms.sendError(player, "Sorry, you don't have permissions to do that");
			}
			
		} else {
			MSTR_Comms.sendtoCommandSender(sender, "Player command only");
		}

		return true;		
	}

}
