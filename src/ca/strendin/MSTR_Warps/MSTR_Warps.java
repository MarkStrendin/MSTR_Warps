package ca.strendin.MSTR_Warps;

import org.bukkit.plugin.java.JavaPlugin;

public class MSTR_Warps extends JavaPlugin {
	@Override
    public void onDisable() {
        MSTR_Comms.logThis("Plugin DISABLED");        
    }

    @Override
    public void onEnable() {
        //Commands
        /* getCommand("setspawn").setExecutor(new SASetSpawnCommand(this)); */
    	MSTR_Comms.logThis("Plugin ENABLED");        
    }

}
