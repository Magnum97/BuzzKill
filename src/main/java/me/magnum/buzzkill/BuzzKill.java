package me.magnum.buzzkill;

import lombok.extern.java.Log;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

@Log
public class BuzzKill extends JavaPlugin implements Listener {

	//	private static BuzzKill plugin;

	@Override
	public void onEnable () {
		//		BuzzKill plugin = this;
		getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable () {
		super.onDisable();
	}

	@EventHandler (priority = EventPriority.HIGHEST)
	private void catchBees (CreatureSpawnEvent event) {
		if (event.getEntity().getType().equals(EntityType.BEE)) {
			String spawnReason = event.getSpawnReason().toString();
			event.setCancelled(true);
			log.info("Bee spawn prevented due to: " + spawnReason);
			for (Player player : getServer().getOnlinePlayers()) {
				if (player.hasPermission("buzzkill.notify"))
					player.sendMessage("[BuzzKill] Just prevented bee spawn - Reason: " + spawnReason);
			}
		}
	}
}
