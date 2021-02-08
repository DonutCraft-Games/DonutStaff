package net.donucraft.loader;

import net.donucraft.DonutStaff;
import net.donucraft.listeners.PlayerChatListener;
import net.donucraft.listeners.PlayerJoinListener;
import net.donucraft.listeners.PlayerMoveListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import javax.inject.Inject;

public class ListenersLoader implements Loader {

    @Inject private DonutStaff donutStaff;
    @Inject private PlayerChatListener playerChatListener;
    @Inject private PlayerMoveListener playerMoveListener;
    @Inject private PlayerJoinListener playerJoinListener;

    @Override
    public void load() {
        registerListeners(
            playerChatListener,
            playerMoveListener,
            playerJoinListener
        );
    }

    private void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, donutStaff);
        }
    }
}
