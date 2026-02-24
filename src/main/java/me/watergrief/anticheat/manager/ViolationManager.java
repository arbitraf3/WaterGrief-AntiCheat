
package me.watergrief.anticheat.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.*;

public class ViolationManager {

    private final Map<UUID, Map<String, Double>> violations = new HashMap<>();

    public void addViolation(Player player, String check, double amount) {

        if (Bukkit.getTPS()[0] < 18.5) return;
        if (player.getPing() > 180) return;

        violations.putIfAbsent(player.getUniqueId(), new HashMap<>());
        Map<String, Double> map = violations.get(player.getUniqueId());

        double vl = map.getOrDefault(check, 0.0) + amount;
        map.put(check, vl);

        if (vl >= 6) sendAlert(player, check, vl);
        if (vl >= 30) punish(player, check);
    }

    private void sendAlert(Player player, String check, double vl) {

        String msg =
            me.watergrief.anticheat.WaterGriefAntiCheat.hex("#00E3FF") + "WaterGrief" +
            me.watergrief.anticheat.WaterGriefAntiCheat.hex("#0088FF") + "-AntiCheat " +
            me.watergrief.anticheat.WaterGriefAntiCheat.hex("#AAAAAA") + "» " +
            me.watergrief.anticheat.WaterGriefAntiCheat.hex("#FF4444") +
            player.getName() +
            me.watergrief.anticheat.WaterGriefAntiCheat.hex("#AAAAAA") +
            " подозревается в " +
            me.watergrief.anticheat.WaterGriefAntiCheat.hex("#FFD700") +
            check +
            me.watergrief.anticheat.WaterGriefAntiCheat.hex("#AAAAAA") +
            " (VL: " + String.format("%.1f", vl) + ")";

        for (Player staff : Bukkit.getOnlinePlayers()) {
            if (staff.hasPermission("watergrief.alerts")) {
                staff.sendMessage(msg);
            }
        }
    }

    private void punish(Player player, String check) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
            "tempban " + player.getName() + " 3d Использование читов (" + check + ")");
        violations.remove(player.getUniqueId());
    }
}
