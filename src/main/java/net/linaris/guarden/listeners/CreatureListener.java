package net.linaris.guarden.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import net.linaris.guarden.Guarden;
import net.linaris.guarden.ProtectedRegion;

public class CreatureListener implements Listener {
	
	@EventHandler(priority=EventPriority.LOWEST)
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		ProtectedRegion region = Guarden.getInstance().getProtectedRegion(event.getLocation(), Guarden.getInstance().getProtectedRegions());
		if (region != null && !region.isAllowCreatureSpawn()) {
			event.setCancelled(true);
		}
	}
	
}
	