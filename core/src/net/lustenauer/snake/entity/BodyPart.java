package net.lustenauer.snake.entity;

import net.lustenauer.snake.config.GameConfig;

/**
 * Created by Patric Hollenstein on 06.02.18.
 *
 * @author Patric Hollenstein
 */
@Deprecated
public class BodyPart extends EntityBase {

    private boolean justAdded = true;

    public BodyPart() {
        setSize(GameConfig.SNAKE_SIZE, GameConfig.SNAKE_SIZE);
    }

    public boolean isJustAdded() {
        return justAdded;
    }

    public void setJustAdded(boolean justAdded) {
        this.justAdded = justAdded;
    }
}
