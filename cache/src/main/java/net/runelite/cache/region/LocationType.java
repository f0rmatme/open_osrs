package net.runelite.cache.region;

import java.util.HashMap;
import java.util.Map;

public enum LocationType {
    /**
     * A wall that is presented lengthwise with respect to the tile.
     */
    LENGTHWISE_WALL(0),

    /**
     * A triangular object positioned in the corner of the tile.
     */
    TRIANGULAR_CORNER(1),

    /**
     * A corner for a wall, where the model is placed on two perpendicular edges of a single tile.
     */
    WALL_CORNER(2),

    /**
     * A rectangular object positioned in the corner of the tile.
     */
    RECTANGULAR_CORNER(3),

    /**
     * An object placed on a wall that can be interacted with by a player.
     */
    INTERACTABLE_WALL_DECORATION(4),

    /**
     * A wall that you can interact with.
     */
    INTERACTABLE_WALL(5),

    /**
     * A wall joint that is presented diagonally with respect to the tile.
     */
    DIAGONAL_WALL(9),

    /**
     * An object that can be interacted with by a player.
     */
    INTERACTABLE(10),

    /**
     * An [INTERACTABLE] object, rotated `pi / 2` radians.
     */
    DIAGONAL_INTERACTABLE(11),

    /**
     * A decoration positioned on the floor.
     */
    FLOOR_DECORATION(22);

    private int value;
    private static Map map = new HashMap<>();

    LocationType(int value) {
        this.value = value;
    }

    static {
        for (LocationType pageType : LocationType.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static LocationType valueOf(int pageType) {
        return (LocationType) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
