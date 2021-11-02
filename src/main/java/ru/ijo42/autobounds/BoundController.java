package ru.ijo42.autobounds;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class BoundController {
    private final Map<Player, BoundPoints> boundsCache;

    public BoundController() {
        boundsCache = new LinkedHashMap<>();
    }

    public void setPointFirst(Player player, Location point) {
        boundsCache.compute(player, (pl, bounds) -> {
            if (bounds == null) {
                return new BoundPoints(point, null);
            }
            bounds.setFirst(point);
            return bounds;
        });
    }

    public void setPointSecond(Player player, Location point) {
        boundsCache.compute(player, (pl, bounds) -> {
            if (bounds == null) {
                return new BoundPoints(null, point);
            }
            bounds.setSecond(point);
            return bounds;
        });
    }

    public BoundPoints getPoints(Player pl) {
        return boundsCache.getOrDefault(pl, new BoundPoints());
    }
}
