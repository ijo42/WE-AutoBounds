package ru.ijo42.autobounds;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClickListener implements Listener {

    private final BoundController boundController;

    public ClickListener(BoundController boundController) {
        this.boundController = boundController;
    }

    @EventHandler()
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem() != null && event.getItem().getType().equals(Material.WOOD_AXE)) {
            switch (event.getAction()) {
                case LEFT_CLICK_BLOCK:
                    event.getPlayer().sendMessage("Position 1 is set");
                    boundController.setPointFirst(event.getPlayer(), event.getClickedBlock().getLocation());
                    break;
                case RIGHT_CLICK_BLOCK:
                    event.getPlayer().sendMessage("Position 2 is set");
                    boundController.setPointSecond(event.getPlayer(), event.getClickedBlock().getLocation());
                    break;
            }
        }
    }
}
