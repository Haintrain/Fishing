package io.github.haintrain.listeners;

import io.github.archemedes.customitem.CustomTag;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ConsumeListener implements Listener{

    @EventHandler
    public void playerEat(PlayerItemConsumeEvent event){
        ItemStack consuming = event.getItem();
        Player player = event.getPlayer();

        CustomTag fishTag = CustomTag.getFrom(consuming);

        if(CustomTag.hasCustomTag(consuming, "name")) {
            if(fishTag.get("raw").equalsIgnoreCase("false")) {
                if(fishTag.get("name").equalsIgnoreCase("Perch")) {
                    PotionEffect pot = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, 1);
                    player.addPotionEffect(pot);
                    player.sendMessage("Strength fills you for a time");
                }
                if (fishTag.get("name").equalsIgnoreCase("Flounder")) {
                    PotionEffect pot = new PotionEffect(PotionEffectType.WATER_BREATHING, 500, 1);
                    player.addPotionEffect(pot);
                    player.sendMessage("You feel an urge to go for a swim");
                }
                if (fishTag.get("name").equalsIgnoreCase("Wormfish")) {
                    PotionEffect pot = new PotionEffect(PotionEffectType.CONFUSION, 100, 1);
                    PotionEffect pot1 = new PotionEffect(PotionEffectType.HUNGER, 100, 3);
                    player.addPotionEffect(pot);
                    player.addPotionEffect(pot1);
                }
                if (fishTag.get("name").equalsIgnoreCase("Ghoulfish")) {
                    PotionEffect pot = new PotionEffect(PotionEffectType.CONFUSION, 100, 1);
                    PotionEffect pot1 = new PotionEffect(PotionEffectType.HUNGER, 100, 3);
                    PotionEffect pot2 = new PotionEffect(PotionEffectType.SLOW, 100, 2);
                    PotionEffect pot3 = new PotionEffect(PotionEffectType.POISON, 100, 1);
                    player.addPotionEffect(pot);
                    player.addPotionEffect(pot1);
                    player.addPotionEffect(pot2);
                    player.addPotionEffect(pot3);
                }
                if(fishTag.get("name").equalsIgnoreCase("Ghostfish")){
                    PotionEffect pot = new PotionEffect(PotionEffectType.INVISIBILITY, 40, 1);
                    playSound(player, Sound.ENTITY_GHAST_SCREAM);
                    player.addPotionEffect(pot);
                    player.sendMessage("Casper is proud");
                }
                if(fishTag.get("name").equalsIgnoreCase("Tuna") || fishTag.get("name").equalsIgnoreCase("Bass")){
                    player.setFoodLevel(10);
                    player.setSaturation(40);
                    player.sendMessage("You feel especially filled and content with your meal.");
                }
                if (fishTag.get("name").equalsIgnoreCase("Moltenfish")) {
                    PotionEffect pot = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 600, 1);
                    player.addPotionEffect(pot);
                }
                if (fishTag.get("name").equalsIgnoreCase("Hagfish")) {
                    PotionEffect pot = new PotionEffect(PotionEffectType.NIGHT_VISION, 400, 1);
                    player.addPotionEffect(pot);
                }
                if (fishTag.get("name").equalsIgnoreCase("Cactusfish")) {
                    PotionEffect pot = new PotionEffect(PotionEffectType.POISON, 150, 1);
                    player.addPotionEffect(pot);
                }
                if(fishTag.get("name").equalsIgnoreCase("Mudsucker")) {
                    PotionEffect pot = new PotionEffect(PotionEffectType.CONFUSION, 100, 1);
                    PotionEffect pot1 = new PotionEffect(PotionEffectType.SLOW, 100, 2);
                    PotionEffect pot2 = new PotionEffect(PotionEffectType.HUNGER, 100, 3);
                    player.addPotionEffect(pot);
                    player.addPotionEffect(pot1);
                    player.addPotionEffect(pot2);
                }
                if (fishTag.get("name").equalsIgnoreCase("The Harrison Fish")) {
                    player.setFireTicks(100);
                }
                else{
                    player.sendMessage("It's just a fish.");
                }
            }
            else{
                PotionEffect pot = new PotionEffect(PotionEffectType.HUNGER, 100, 2);
                player.addPotionEffect(pot);

                player.getInventory().removeItem(player.getInventory().getItemInMainHand());
                event.setCancelled(true);

                player.sendMessage(ChatColor.RED + "You feel ill after eating raw fish...");
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void fishCook(FurnaceSmeltEvent event){
        ItemStack uncooked = event.getSource();

        CustomTag fishTag = CustomTag.getFrom(uncooked);

        if(CustomTag.hasCustomTag(uncooked, "name")){
            ItemStack cooked = new ItemStack(Material.COOKED_FISH);

            ItemMeta cookedItemMeta = cooked.getItemMeta();
            cookedItemMeta.setDisplayName("Cooked " + uncooked.getItemMeta().getDisplayName());

            cooked.setItemMeta(cookedItemMeta);

            CustomTag.getFrom(uncooked);
            fishTag.remove("raw");
            fishTag.put("raw", "false");

            cooked = CustomTag.applyTo(fishTag, cooked);

            event.setResult(cooked);
        }
    }

    private void playSound(Player player, Sound sound) {
        for (Player pl : player.getWorld().getPlayers()) {
            if (pl.getLocation().distanceSquared(player.getLocation()) < 50) {
                pl.playSound(player.getLocation(), sound, 0.5f, 1);
            }
        }
    }
}
