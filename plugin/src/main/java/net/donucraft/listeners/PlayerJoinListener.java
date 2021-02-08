package net.donucraft.listeners;

import net.donucraft.DonutStaff;
import net.donutcraft.donutstaff.api.staffmode.StaffModeManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.inject.Inject;
import java.util.UUID;

public class PlayerJoinListener implements Listener {

    @Inject private StaffModeManager staffModeManager;
    @Inject private DonutStaff donutStaff;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        for (UUID uuid : staffModeManager.getStaffPlayers()) {
            player.hidePlayer(donutStaff, Bukkit.getPlayer(uuid));
        }
    }
}