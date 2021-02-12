package net.donutcraft.donutstaff.module;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import me.yushust.inject.Binder;
import me.yushust.inject.Module;
import me.yushust.inject.key.TypeReference;
import net.donutcraft.donutstaff.DonutStaff;
import net.donutcraft.donutstaff.cache.FreezeCache;
import net.donutcraft.donutstaff.cache.StaffChatCache;
import net.donutcraft.donutstaff.cache.StaffModeCache;
import net.donutcraft.donutstaff.cache.VanishCache;
import net.donutcraft.donutstaff.files.FileCreator;
import net.donutcraft.donutstaff.files.FileMatcher;
import net.donutcraft.donutstaff.staffmode.SimpleStaffModeHandler;
import net.donutcraft.donutstaff.staffmode.SimpleStaffModeManager;
import net.donutcraft.donutstaff.util.nms.NMSManager;
import net.donutcraft.donutstaff.util.nms.SimpleNMSManager;
import net.donutcraft.donutstaff.api.cache.Cache;
import net.donutcraft.donutstaff.api.staffmode.StaffModeHandler;
import net.donutcraft.donutstaff.api.staffmode.StaffModeManager;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class MainModule implements Module {

    private final DonutStaff donutStaff;

    public MainModule(DonutStaff donutStaff) {
        this.donutStaff = donutStaff;
    }

    @Override
    public void configure(Binder binder) {

        FileMatcher fileMatcher = new FileMatcher()
                //.bind("config", new FileCreator(donutStaff, "config"))
                .bind("messages", new FileCreator(donutStaff, "messages"))
                .bind("items", new FileCreator(donutStaff, "items"));


        binder.install(fileMatcher.build());

        binder.install(new ServiceModule());

        binder.bind(DonutStaff.class).toInstance(donutStaff);
        binder.bind(Plugin.class).to(DonutStaff.class);

        binder.bind(NMSManager.class).to(SimpleNMSManager.class).singleton();

        binder.bind(StaffModeManager.class).to(SimpleStaffModeManager.class);
        binder.bind(StaffModeHandler.class).to(SimpleStaffModeHandler.class);

        binder.bind(new TypeReference<Cache<UUID>>() {}).named("freeze-cache").to(FreezeCache.class).singleton();
        binder.bind(new TypeReference<Cache<UUID>>() {}).named("staff-chat-cache").to(StaffChatCache.class).singleton();
        binder.bind(new TypeReference<Cache<UUID>>() {}).named("staff-mode-cache").to(StaffModeCache.class).singleton();
        binder.bind(new TypeReference<Cache<UUID>>() {}).named("vanish-cache").to(VanishCache.class).singleton();

    }
}
