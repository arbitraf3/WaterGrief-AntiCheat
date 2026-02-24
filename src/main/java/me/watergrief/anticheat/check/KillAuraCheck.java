
package me.watergrief.anticheat.check;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class KillAuraCheck {

    public static void handle(Player player, Entity target) {

        if (!(target instanceof Player)) return;

        Vector dir = player.getLocation().getDirection().normalize();
        Vector to = target.getLocation().toVector()
                .subtract(player.getLocation().toVector()).normalize();

        double dot = dir.dot(to);
        double distance = player.getLocation().distance(target.getLocation());

        if (dot < 0.80 && distance > 2.4 && player.getPing() < 170) {
            me.watergrief.anticheat.WaterGriefAntiCheat
                .getInstance().getViolationManager()
                .addViolation(player, "KillAura", 1.5);
        }
    }
}
