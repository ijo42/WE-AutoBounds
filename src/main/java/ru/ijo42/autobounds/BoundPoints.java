package ru.ijo42.autobounds;

import org.bukkit.Location;

public class BoundPoints {
    private Location first, second;

    public BoundPoints(Location first, Location second) {
        this.first = first;
        this.second = second;
    }

    public BoundPoints() {
    }

    public Location getFirst() {
        return first;
    }

    public void setFirst(Location first) {
        this.first = first;
    }

    public Location getSecond() {
        return second;
    }

    public void setSecond(Location second) {
        this.second = second;
    }

}
