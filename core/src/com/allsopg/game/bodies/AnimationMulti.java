package com.allsopg.game.bodies;

/**
 * Created by Harry on 26/02/2018.
 */

import com.allsopg.game.utility.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Comparator;

public class AnimationMulti extends Sprite{
    public Animation idle, ammo, animation; // Variables used to set the current animation
    private TextureAtlas atlas_1, atlas_2; // Variables used to set up the two animation atlases

    // Sets up the two atlases and their regions used for the animation change. atlas_1 will be set to the 'idle' animation; atlas_2 will be set to the 'anim' animation
    public AnimationMulti(String atlasString_1, String atlasString_2, Texture t, Vector2 pos, Animation.PlayMode loopType){
        super(t);
        atlas_1 = new TextureAtlas(Gdx.files.internal(atlasString_1));
        Array<TextureAtlas.AtlasRegion> regions_1 = new
                Array<TextureAtlas.AtlasRegion>(atlas_1.getRegions());
        regions_1.sort(new RegionComparator());
        idle = new Animation(Constants.FRAME_DURATION, regions_1, loopType);

        atlas_2 = new TextureAtlas(Gdx.files.internal(atlasString_2));
        Array<TextureAtlas.AtlasRegion> regions_2 = new
                Array<TextureAtlas.AtlasRegion>(atlas_2.getRegions());
        regions_2.sort(new RegionComparator());
        ammo = new Animation(Constants.FRAME_DURATION, regions_2, loopType);

        animation = idle; // Animation is set to 'idle' by default, so the crate remains closed at the beginning of the animation

        this.setPosition(300, 300);
    }

    public void update(float deltaTime){
        this.setRegion((TextureAtlas.AtlasRegion) animation.getKeyFrame(deltaTime)); // Updates the region every frame
    }

    private static class RegionComparator implements Comparator<TextureAtlas.AtlasRegion> {

        @Override
        public int compare(TextureAtlas.AtlasRegion region1, TextureAtlas.AtlasRegion region2) {
            return region1.name.compareTo(region2.name); // Compares the regions of the atlases
        }
    }
}
