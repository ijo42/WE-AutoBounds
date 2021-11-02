package ru.ijo42.autobounds;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AutoBoundCommand implements CommandExecutor {

    private final BoundController boundController;
    private final int boundMaterial;

    protected AutoBoundCommand(BoundController boundController, int boundMaterial) {
        this.boundController = boundController;
        this.boundMaterial = boundMaterial;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        BoundPoints points = this.boundController.getPoints((Player) sender);
        if (points.getFirst() == null) {
            sender.sendMessage("Position 1 not defined");
            return false;
        } else if (points.getSecond() == null) {
            sender.sendMessage("Position 2 not defined");
            return false;
        } else {
            double x1 = points.getFirst().getX(), y1 = points.getFirst().getY(), z1 = points.getFirst().getZ(),
                    x2 = points.getSecond().getX(), y2 = points.getSecond().getY(), z2 = points.getSecond().getZ();
            PointCollector pointCollector = new PointCollector(points.getFirst().getWorld());

            pointCollector.addPoint(x2, y1, z2);
            pointCollector.addPoint(x2, y2, z2);

            pointCollector.addPoint(x1, y1, z1);
            pointCollector.addPoint(x1, y2, z1);

            pointCollector.addPoint(x1, y1, z2);
            pointCollector.addPoint(x1, y2, z2);

            pointCollector.addPoint(x2, y1, z1);
            pointCollector.addPoint(x2, y2, z1);

            pointCollector.getLocationList().forEach(p -> p.getBlock().setTypeId(boundMaterial));
        }
        return false;
    }

    private static class PointCollector {
        private final World world;
        private final List<Location> locationList;

        private PointCollector(World world) {
            this.world = world;
            this.locationList = new ArrayList<>();
        }

        public List<Location> getLocationList() {
            return locationList;
        }

        public void addPoint(double x, double y, double z) {
            locationList.add(new Location(world, x, y, z));
        }
    }
}
