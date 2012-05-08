/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.cnaude.plugin.CatPunch;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author cnaude
 */
public class CatPunch extends JavaPlugin implements Listener {     
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);        
    }  
   
    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Tameable) {
            if (((Tameable) event.getEntity()).isTamed()) {
                Entity attacker = event.getDamager();
                if ((attacker != null) && ((attacker instanceof Player))) {
                    Player p = (Player) attacker;
                    if (p.isSneaking()) {                                                                                                
                        if (((Tameable) event.getEntity()).getOwner() instanceof OfflinePlayer) {
                            String ownerName = ((OfflinePlayer) ((Tameable) event.getEntity()).getOwner()).getName();
                            p.sendMessage(ChatColor.GRAY + "The owner of this animal is " 
                                + ChatColor.GREEN + ownerName + ChatColor.GRAY + ".");
                            event.setCancelled(true);
                        } else if (((Tameable) event.getEntity()).getOwner() instanceof Player) {
                            String ownerName = ((Player) ((Tameable) event.getEntity()).getOwner()).getName();
                            p.sendMessage(ChatColor.GRAY + "The owner of this animal is " 
                                + ChatColor.RED + ownerName + ChatColor.GRAY + ".");
                            event.setCancelled(true);
                        }
                    }                    
                }
            }
        }
    }
}

