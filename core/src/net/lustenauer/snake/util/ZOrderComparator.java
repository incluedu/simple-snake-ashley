package net.lustenauer.snake.util;

import com.badlogic.ashley.core.Entity;

import java.util.Comparator;

/**
 * Created by Patric Hollenstein on 20.02.18.
 *
 * @author Patric Hollenstein
 */
public class ZOrderComparator implements Comparator<Entity>{

    /*
     * CONSTANTS
     */
    public static final ZOrderComparator INSTANCE = new ZOrderComparator();

    /*
     * CONSTRUCTORS
     */
    private ZOrderComparator() {
    }

    /*
     * PUBLIC METHODES
     */

    @Override
    public int compare(Entity entity1, Entity entity2) {
        return Float.compare(
                Mappers.Z_ORDER.get(entity1).z,
                Mappers.Z_ORDER.get(entity2).z
        );
    }
}
