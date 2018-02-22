package net.lustenauer.snake.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.lustenauer.snake.common.GameManager;

/**
 * Created by Patric Hollenstein on 22.02.18.
 *
 * @author Patric Hollenstein
 */
public class HudRenderSystem extends EntitySystem {
    /*
     * CONSTANTS
     */
    private static final float PADDING = 20.0f;

    /*
     * ATTRIBUTES
     */
    private final SpriteBatch batch;
    private final Viewport viewport;
    private final BitmapFont font;
    private final GlyphLayout layout = new GlyphLayout();


    /*
     * CONSTRUCTORS
     */
    public HudRenderSystem(SpriteBatch batch, Viewport viewport, BitmapFont font) {
        this.batch = batch;
        this.viewport = viewport;
        this.font = font;
    }

    /*
     * PROCESS
     */

    @Override
    public void update(float deltaTime) {
        GameManager.INSTANCE.updateDisplayScore(deltaTime);

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        float y = viewport.getWorldHeight() - PADDING;

        // high score
        String highScoreString = "HIGH SCORE: " + GameManager.INSTANCE.getDisplayHighScore();
        layout.setText(font, highScoreString);
        font.draw(batch, layout, PADDING, y);

        // score
        String scoreString = "SCORE: " + GameManager.INSTANCE.getDisplayScore();
        float scoreX = viewport.getWorldWidth() - layout.width;
        layout.setText(font, scoreString);
        font.draw(batch, layout, scoreX, y);

        batch.end();
    }
}
