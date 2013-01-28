package ca.strendin.MSTR_Warps;

import org.bukkit.plugin.java.JavaPlugin;

public class MSTR_Warps extends JavaPlugin {	
	
	@Override
    public void onDisable() {
        MSTR_Comms.logThis("Plugin DISABLED");        
    }

    @Override
    public void onEnable() {
    	
    	MSTR_WarpHandler.initWarps(this);
    	
        //Commands
    	getCommand("warp").setExecutor(new MSTR_WarpCommand(this));
    	getCommand("setwarp").setExecutor(new MSTR_SetWarpCommand(this));
    	getCommand("listwarps").setExecutor(new MSTR_ListWarpCommand(this));
    	getCommand("removewarp").setExecutor(new MSTR_RemoveWarpCommand(this));
    	MSTR_Comms.logThis("Plugin ENABLED");        
    }

}
