package net.linaris.guarden.tasks;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.linaris.guarden.Guarden;
import net.linaris.guarden.ProtectedRegion;

public class CheckLocationsTask extends BukkitRunnable {
	
	private Guarden plugin;
	private Map<String, String> playerNameToRegionName;
	
	public CheckLocationsTask(Guarden plugin) {
		this.plugin = plugin;
		this.playerNameToRegionName = new HashMap<String, String>();
	}
	
	@Override
	public void run() {
		// Might need to make this async...
		for (Player player : this.plugin.getServer().getOnlinePlayers()) {
			ProtectedRegion region = this.plugin.getProtectedRegion(player.getLocation(), this.plugin.getProtectedRegions());

            if (this.playerNameToRegionName.containsKey(player.getName())) {
				String regionName = this.playerNameToRegionName.get(player.getName());
				if (!regionName.equals(region.getRegionName())) {
					this.playerNameToRegionName.put(player.getName(), region.getRegionName());
					player.sendMessage(ChatColor.YELLOW + "Entering region " + ChatColor.GREEN + region.getRegionName());
				}
			} else {
                this.playerNameToRegionName.put(player.getName(), region.getRegionName());
                player.sendMessage(ChatColor.YELLOW + "You are in region " + ChatColor.GREEN + region.getRegionName());
            }
		}
	}

	public Guarden getPlugin() {
		return plugin;
	}

	public void setPlugin(Guarden plugin) {
		this.plugin = plugin;
	}

	public Map<String, String> getPlayerNameToRegionName() {
		return playerNameToRegionName;
	}

	public void setPlayerNameToRegionName(Map<String, String> playerNameToRegionName) {
		this.playerNameToRegionName = playerNameToRegionName;
	}

}
