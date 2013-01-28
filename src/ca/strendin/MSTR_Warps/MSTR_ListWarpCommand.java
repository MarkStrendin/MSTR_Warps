package ca.strendin.MSTR_Warps;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MSTR_ListWarpCommand implements CommandExecutor {

	public MSTR_ListWarpCommand(MSTR_Warps mstr_Warps) {}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] arg3) {		
		
		if (sender instanceof Player) {
			Player player = (Player)sender;
			
			if (MSTR_Permissions.canUseWarps(player)) {
				if (MSTR_WarpHandler.getAllWarps().isEmpty()) {
					MSTR_Comms.sendInfo(player, "There are no available warps");
				} else {
					StringBuilder warpList = new StringBuilder();
					warpList.append(ChatColor.GRAY + "Available warps: ");
					
					for (MSTR_Warp thisWarp : MSTR_WarpHandler.getAllWarps()) {
						warpList.append(ChatColor.WHITE + thisWarp.getName());
						warpList.append(ChatColor.GRAY + ", ");
					}
					
					warpList.deleteCharAt(warpList.length()-1);
					warpList.deleteCharAt(warpList.length()-1);
					
					MSTR_Comms.sendInfo(player, warpList.toString());
				}				
			}			
		} else {
			MSTR_Comms.sendtoCommandSender(sender, "Player command only");
		}
		
		

		return true;
	}

}
