package ca.strendin.MSTR_Warps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.entity.Player;

public class MSTR_WarpHandler {
    private static ArrayList<MSTR_Warp> warps = new ArrayList<MSTR_Warp>();
    private static File warpDir;
    
    public static void initWarps(MSTR_Warps plugin) {
    	File pluginDirectory = plugin.getDataFolder(); 
    	warpDir = new File(pluginDirectory, "warps");
    	
    	MSTR_Comms.logThis("Initializing warps...");    	
    	warps.clear();
    	
    	if (!pluginDirectory.exists()) {        
            pluginDirectory.mkdir();           
        }
        
        if (!warpDir.exists()) {
        	MSTR_Comms.logThis("Region directory does not exist - creating");
        	warpDir.mkdir();
            // Since we know there will be no warps to load, don't bother trying
            return;
        }
        
     // For each file contained in it, attempt to load
        FilenameFilter filter = new FilenameFilter() {
          public boolean accept(File dir, String name) {
              return name.endsWith(".mswarp");          
          }
        };
        
        String[] warpFileNames = warpDir.list(filter);
        
        if (warpFileNames.length > 0) {            
	        for (String thisWarpFile : warpFileNames) {
	            try {
	                FileInputStream fileIn = new FileInputStream(warpDir + "/" + thisWarpFile);
	                ObjectInputStream in = new ObjectInputStream(fileIn);
	                warps.add((MSTR_Warp) in.readObject());
	                MSTR_Comms.logThis(" Loaded warp: " + thisWarpFile);
	                in.close();
	                fileIn.close();
	            } catch (Exception e) {
	            	MSTR_Comms.logThis(" Failed to load warp \"" + thisWarpFile + "\"");
	            }
	        }
        } else {
        	MSTR_Comms.logThis(" No regions to load");        	
        }
    	
    }
    
    public static void saveAllWarps() {
    	if ((warpDir.exists())) {
    		MSTR_Comms.logThis("Saving regions to \"" + warpDir.getPath() + "\"");
            for (MSTR_Warp thisWarp : warps) {
                try {
                    FileOutputStream fileout = new FileOutputStream(warpDir.getPath() + "\\" + thisWarp.getName() + ".mswarp");
                    ObjectOutputStream out = new ObjectOutputStream(fileout);
                    out.writeObject(thisWarp);
                    out.close();
                    fileout.close();                
                } catch (IOException i) {
                	MSTR_Comms.logThis("Failed to save warp data for " + thisWarp.getName());
                	MSTR_Comms.logThis(" Cause: " + i.getCause());
                	MSTR_Comms.logThis(" Message: " + i.getMessage());
                } catch (Exception i) {
                	MSTR_Comms.logThis("Failed to save warp data for " + thisWarp.getName());
                	MSTR_Comms.logThis(" Cause: " + i.getCause());
                	MSTR_Comms.logThis(" Message: " + i.getMessage());                	
                }
            }    		
    	} else {
    		MSTR_Comms.logThis("No directory to save regions to!");
    	}  
    	
    }
    
    public static boolean warpNameExists(String name) {
    	for (MSTR_Warp thisWarp : warps) {
    		if (thisWarp.getName().contentEquals(name)) {
    			return true;
    		}
    	}
    	return false;    		
    }
    
    public static void createWarp(Player player, String warpName) {
    	String sanitizedWarpName = sanitizeInput(warpName);
    	
    	if (!warpNameExists(sanitizedWarpName)) {    		
    		
    		MSTR_Warp newWarp = new MSTR_Warp(player, sanitizedWarpName);    		
    		
    		
        	warps.add(newWarp); 
			MSTR_Comms.sendInfo(player, "Warp point \"" + warpName + "\" created at your location");       	
        	
        	
        	saveAllWarps();
        	        	
        	    		
    	} else {
    		MSTR_Comms.sendInfo(player, "A warp with that name already exists");    		
    	}    	
    }
    
    public static MSTR_Warp getWarpNamed(String name) {
    	for (MSTR_Warp thisWarp : warps) {
    		if (thisWarp.getName().contentEquals(name)) {
    			return thisWarp;
    		}
    	}
    	return null;    	
    }
    
    public static ArrayList<MSTR_Warp> getAllWarps() {
    	return warps;
    }
    
    public static void removeWarp(Player player, String warpName) {
    	boolean removedWarp = false;        
        MSTR_Warp foundWarp = getWarpNamed(warpName);
 
        if (foundWarp != null) {
            warps.remove(foundWarp);
            File thisWarpFile = new File(warpDir,foundWarp.getName() + ".mswarp");
            if (thisWarpFile.exists()) {
            	thisWarpFile.delete();
            }
            removedWarp = true;
        }
        
        if (removedWarp) {            
        	MSTR_Comms.sendInfo(player, "Warp \""+warpName+"\" removed");
        } else {
        	MSTR_Comms.sendInfo(player, "Warp not found");
        }
    }
    
    public static String sanitizeInput(String input) {
        
        String working = null;
        
        // Only allow a warp name to be 20 characters long (just because)
        // only allow a warp name to be lower case
        if (input.length() > 20) {
            working = input.substring(0, 20).toLowerCase();            
        } else {
            working = input.toLowerCase();
        }
        
        // only output alphabet characters and numbers - remove anything else
        
        String REGEX = "[^a-z0-9]";
        
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(working); // get a matcher object
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
          m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        
        working = sb.toString();
        return working;
    }
    
    
    
    
    
}
