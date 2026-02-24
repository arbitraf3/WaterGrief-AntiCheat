
package me.watergrief.anticheat.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import me.watergrief.anticheat.check.KillAuraCheck;

public class CombatListener implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player)) return;

        Player attacker = (Player) event.getDamager();
        KillAuraCheck.handle(attacker, event.getEntity());
    }
}
