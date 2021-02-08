package net.donutcraft.donutstaff.api.nms;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class NMHSHandler_1_12_R1 implements NMSHandler {
    @Override
    public void sendTitle(Player player, String title, String subTitle, int fadeIn, int fadeShow, int fadedOut) {
        player.sendTitle(title, subTitle, fadeIn, fadeShow, fadedOut);
    }

    @Override
    public void sendActionBar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
    }

    @Override
    public void sendPacket(Player player, Object object) {}
}
