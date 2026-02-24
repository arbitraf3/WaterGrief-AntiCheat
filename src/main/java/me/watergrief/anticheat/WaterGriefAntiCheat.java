
package me.watergrief.anticheat;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import me.watergrief.anticheat.manager.ViolationManager;
import me.watergrief.anticheat.listener.*;

public class WaterGriefAntiCheat extends JavaPlugin {

    private static WaterGriefAntiCheat instance;
    private ViolationManager violationManager;

    @Override
    public void onEnable() {
        instance = this;
        violationManager = new ViolationManager();

        Bukkit.getPluginManager().registerEvents(new MoveListener(), this);
        Bukkit.getPluginManager().registerEvents(new CombatListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockListener(), this);

        getLogger().info("WaterGrief-AntiCheat FULL Production Enabled.");
    }

    public static WaterGriefAntiCheat getInstance() {
        return instance;
    }

    public ViolationManager getViolationManager() {
        return violationManager;
    }

    public static String hex(String hex) {
        return net.md_5.bungee.api.ChatColor.of(hex).toString();
    }
}
