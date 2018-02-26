package com.allsopg.game.bodies;

import com.allsopg.game.PhysicsExample_2;
import com.allsopg.game.utility.Constants;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

/**
 * Created by gja10 on 19/02/2018.
 */

public class FlyingTurf extends Sprite {

    private Body body;
    private World world;
    private PhysicsExample_2 game;
    private AnimationMulti anim;

    public FlyingTurf(World aWorld, PhysicsExample_2 aGame, float pos_x, float pos_y){

        super(new Texture(Gdx.files.internal("floor.png")));



        Texture small = new Texture(Gdx.files.internal("smallSize.png")); // Initializes one of the textures used to size the sprite
        Texture medium = new Texture(Gdx.files.internal("mediumSize.png")); // Initializes one of the textures used to size the sprite
        anim = new AnimationMulti("gfx/idle/idle_assets.atlas", "gfx/animation/anim_assets.atlas", medium,
                new Vector2(Constants.SCENE_WIDTH/2,Constants.SCENE_HEIGHT/2), Animation.PlayMode.LOOP);



        this.setPosition(pos_x,pos_y);
        world = aWorld;
        game = aGame;

        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.DynamicBody;
        // Set its world position
        groundBodyDef.position.set(this.getX(),this.getY());
        // Create a body from the defintion and add it to the world
        body = world.createBody(groundBodyDef);

        //prepare for Fixture definition
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.getBoundingRectangle().getX(),
                this.getBoundingRectangle().getY());
        // (setAsBox takes half-width and half-height as arguments)
        // Create a fixture from our polygon shape and add it to body
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution=0.6f;
        body.createFixture(fixtureDef);
        // Clean up after ourselves
        shape.dispose();
    }

    public void draw(){
        game.batch.draw(this,this.getX(),this.getY());
    }

    public void update(){
        this.setPosition(body.getPosition().x, body.getPosition().y);
    }

}

