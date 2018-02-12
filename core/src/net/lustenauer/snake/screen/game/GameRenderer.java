package net.lustenauer.snake.screen.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.lustenauer.snake.assets.AssetDescriptors;
import net.lustenauer.snake.assets.RegionNames;
import net.lustenauer.snake.common.GameManager;
import net.lustenauer.snake.config.GameConfig;
import net.lustenauer.snake.entity.BodyPart;
import net.lustenauer.snake.entity.Coin;
import net.lustenauer.snake.entity.Snake;
import net.lustenauer.snake.entity.SnakeHead;
import net.lustenauer.snake.util.GdxUtils;
import net.lustenauer.snake.util.ViewportUtils;
import net.lustenauer.snake.util.debug.DebugCameraController;

/**
 * Created by Patric Hollenstein on 03.02.18.
 *
 * @author Patric Hollenstein
 */
@Deprecated
public class GameRenderer implements Disposable {
    /*
     * CONSTANTS
     */
    private static final float PADDING = 20.0f;

    /*
     * ATTRIBUTES
     */
    private final GameController controller;
    private final SpriteBatch batch;
    private final AssetManager assetManager;

    private OrthographicCamera camera;
    private Viewport viewport;
    private Viewport hudViewport;
    private ShapeRenderer renderer;

    private BitmapFont font;
    private final GlyphLayout layout = new GlyphLayout();

    private TextureRegion backgroundRegion;
    private TextureRegion bodyRegion;
    private TextureRegion headRegion;
    private TextureRegion coinRegion;


    private DebugCameraController debugCameraController;


    /*
     * CONSTRUCTORS
     */

    public GameRenderer(SpriteBatch batch, AssetManager assetManager, GameController controller) {
        this.batch = batch;
        this.assetManager = assetManager;
        this.controller = controller;
        init();
    }

    private void init() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        hudViewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HIGHT);
        renderer = new ShapeRenderer();

        font = assetManager.get(AssetDescriptors.UI_FONT);

        TextureAtlas gamePlayAtlas = assetManager.get(AssetDescriptors.GAME_PLAY);

        backgroundRegion = gamePlayAtlas.findRegion(RegionNames.BACKGROUND);
        headRegion = gamePlayAtlas.findRegion(RegionNames.HEAD);
        bodyRegion = gamePlayAtlas.findRegion(RegionNames.BODY);
        coinRegion = gamePlayAtlas.findRegion(RegionNames.COIN);

        debugCameraController = new DebugCameraController();
        debugCameraController.setStartPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);
    }

    /*
     * PUBLIC METHODES
     */
    public void render(float delta) {
        debugCameraController.handleDebugInput(delta);
        debugCameraController.applyTo(camera);
        GdxUtils.clearScreen();

        renderGamePlay();
        renderHud();
        //renderDebug();
    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
        ViewportUtils.debugPixelsPerUnit(viewport);
        ViewportUtils.debugPixelsPerUnit(hudViewport);
    }


    @Override
    public void dispose() {
        renderer.dispose();
    }


    /*
     * PRIVATE METHODES
     */
    private void renderGamePlay() {
        viewport.apply();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        drawGamePlay();

        batch.end();
    }

    private void drawGamePlay() {

        // background
        batch.draw(backgroundRegion, 0, 0, GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT);

        // coin
        Coin coin = controller.getCoin();
        if (coin.isAvailable()) {
            batch.draw(coinRegion, coin.getX(), coin.getY(), coin.getWidth(), coin.getHeight());
        }

        // snake
        Snake snake = controller.getSnake();

        // body parts
        for (BodyPart bodyPart : snake.getBodyParts()) {
            batch.draw(bodyRegion, bodyPart.getX(), bodyPart.getY(), bodyPart.getWidth(), bodyPart.getHeight());
        }

        // head
        SnakeHead head = snake.getHead();
        batch.draw(headRegion, head.getX(), head.getY(), head.getWidth(), head.getHeight());

    }

    private void renderDebug() {
        ViewportUtils.drawGrid(viewport, renderer);

        viewport.apply();
        Color oldColor = renderer.getColor().cpy();
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);

        drawDebug();

        renderer.end();
        renderer.setColor(oldColor);
    }

    private void drawDebug() {
        Snake snake = controller.getSnake();

        // body parts
        renderer.setColor(Color.YELLOW);
        for (BodyPart bodyPart : snake.getBodyParts()) {
            Rectangle bodyPartBounds = bodyPart.getBounds();
            renderer.rect(bodyPartBounds.x, bodyPartBounds.y, bodyPartBounds.width, bodyPartBounds.height);
        }

        // head
        renderer.setColor(Color.GREEN);
        SnakeHead snakeHead = snake.getHead();
        Rectangle headBounds = snakeHead.getBounds();
        renderer.rect(headBounds.x, headBounds.y, headBounds.width, headBounds.height);

        // coin
        Coin coin = controller.getCoin();
        if (coin.isAvailable()) {
            renderer.setColor(Color.BLUE);
            Rectangle coinBounds = coin.getBounds();
            renderer.rect(coinBounds.x, coinBounds.y, coinBounds.width, coinBounds.height);
        }
    }

    private void renderHud() {
        hudViewport.apply();
        batch.setProjectionMatrix(hudViewport.getCamera().combined);
        batch.begin();

        drawHud();

        batch.end();
    }

    private void drawHud() {
        String highScoreString = "HIGH SCORE: " + GameManager.INSTANCE.getDisplayHighScore();
        layout.setText(font, highScoreString);
        font.draw(batch, layout, PADDING, hudViewport.getWorldHeight() - PADDING);

        float scoreX = hudViewport.getWorldWidth() - layout.width;
        float scoreY = hudViewport.getWorldHeight() - PADDING;

        String scoreString = "SCORE: " + GameManager.INSTANCE.getDisplayScore();
        layout.setText(font, scoreString);
        font.draw(batch, layout, scoreX, scoreY);

    }
}
