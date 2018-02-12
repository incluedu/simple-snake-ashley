package net.lustenauer.snake.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Patric Hollenstein on 09.02.18.
 *
 * @author Patric Hollenstein
 */
public class AssetDescriptors {

    /*
     * CONSTANTS
     */
    public static final AssetDescriptor<BitmapFont> UI_FONT =
            new AssetDescriptor<BitmapFont>(AssetsPaths.UI_FONT, BitmapFont.class);

    public static final AssetDescriptor<TextureAtlas> GAME_PLAY =
            new AssetDescriptor<TextureAtlas>(AssetsPaths.GAME_PLAY, TextureAtlas.class);

    public static final AssetDescriptor<Skin> UI_SKIN =
            new AssetDescriptor<Skin>(AssetsPaths.UI_SKIN, Skin.class);

    public static final AssetDescriptor<Sound> COIN_SOUND =
            new AssetDescriptor<Sound>(AssetsPaths.COIN_SOUND, Sound.class);

    public static final AssetDescriptor<Sound> LOSE_SOUND =
            new AssetDescriptor<Sound>(AssetsPaths.LOSE_SOUND, Sound.class);


    /*
     * CONSTRUCTORS
     */
    private AssetDescriptors() {
    }
}
