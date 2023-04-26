import processing.core.PImage;

import java.util.List;

/**
 * A simple class representing a location in 2D space.
 */
public final class Point {

    private final int x;
    private final int y;


    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int x(){return this.x;}
    public int y(){return this.y;}

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean equals(Object other) {
        return other instanceof Point && ((Point) other).x == this.x && ((Point) other).y == this.y;
    }

    public int hashCode() {
        int result = 17;
        result = result * 31 + x;
        result = result * 31 + y;
        return result;
    }
    public int distanceSquared(Point p2) {
        int deltaX = this.x - p2.x;
        int deltaY = this.y - p2.y;

        return deltaX * deltaX + deltaY * deltaY;
    }
    public boolean adjacent( Point p2) {
        return (this.x == p2.x && Math.abs(this.y - p2.y) == 1) || (this.y == p2.y && Math.abs(this.x - p2.x) == 1);
    }
//    public Entity createSapling(String id, List<PImage> images, int health) {
//        return new Entity(EntityKind.SAPLING, id, this, images, 0, 0, SAPLING_ACTION_ANIMATION_PERIOD, SAPLING_ACTION_ANIMATION_PERIOD, 0, SAPLING_HEALTH_LIMIT);
//    }
//    public Entity createFairy(String id, double actionPeriod, double animationPeriod, List<PImage> images) {
//        return new Entity(EntityKind.FAIRY, id, this, images, 0, 0, actionPeriod, animationPeriod, 0, 0);
//    }
//    public Entity createDudeFull(String id, double actionPeriod, double animationPeriod, int resourceLimit, List<PImage> images) {
//        return new Entity(EntityKind.DUDE_FULL, id, this, images, resourceLimit, 0, actionPeriod, animationPeriod, 0, 0);
//    }
//    public Entity createDudeNotFull(String id, double actionPeriod, double animationPeriod, int resourceLimit, List<PImage> images) {
//        return new Entity(EntityKind.DUDE_NOT_FULL, id, this, images, resourceLimit, 0, actionPeriod, animationPeriod, 0, 0);
//    }
//    public Entity createStump(String id, List<PImage> images) {
//        return new Entity(EntityKind.STUMP, id, this, images, 0, 0, 0, 0, 0, 0);
//    }
//    public Entity createHouse(String id, List<PImage> images) {
//        return new Entity(EntityKind.HOUSE, id, this, images, 0, 0, 0, 0, 0, 0);
//    }
//    public Entity createTree(String id, double actionPeriod, double animationPeriod, int health, List<PImage> images) {
//        return new Entity(EntityKind.TREE, id, this, images, 0, 0, actionPeriod, animationPeriod, health, 0);
//    }
//    public  Entity createObstacle(String id, double animationPeriod, List<PImage> images) {
//        return new Entity(EntityKind.OBSTACLE, id, this, images, 0, 0, 0, animationPeriod, 0, 0);
//    }

}
