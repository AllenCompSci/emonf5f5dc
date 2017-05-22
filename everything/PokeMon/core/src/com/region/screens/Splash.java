package com.region.screens;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import classes.AudioPla;
import classes.Battlefield;
import classes.Utility;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.region.tween.SpriteAccessor;

/**
 * Created by Taylor on 1/21/2017.
 */
public class Splash implements Screen, InputProcessor{

    private Sprite titleScreen;
    private SpriteBatch batch;
    private TweenManager tweenManager;
    private AudioPla example;

    @Override
    public void show() {
        example = new AudioPla("core/assets/Music/Silver Surfer - level 1 - Nes Music.mp3");
        new Thread(example).start();
        batch = new SpriteBatch();

        tweenManager = new TweenManager();
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());

        Texture splashTexture = new Texture("core/assets/imgs/beige battle screen.jpg");
        titleScreen = new Sprite(splashTexture);
        titleScreen.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Tween.set(titleScreen, SpriteAccessor.ALPHA).target(0).start(tweenManager);
        Tween.to(titleScreen, SpriteAccessor.ALPHA, 1).target(1).start(tweenManager);
//        Tween.to(titleScreen, SpriteAccessor.ALPHA, 4).target(0).delay(15).start(tweenManager);

        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void render(float delta) {
        // delta is time between frames

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        tweenManager.update(delta);

        batch.begin();
        titleScreen.draw(batch);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        example.doKILLKILL();
        dispose();
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(screenY > 560 && screenX > 200 && screenX < 1080){
            int moveX = (screenX - 200) / 440;
            int moveY = (screenY - 560) / 80;
            if(moveX == 0){
                if(moveY == 0) Battlefield.turn(1);
                else Battlefield.turn(2);

                if(moveY == 0) System.out.println(1);
                else System.out.println(3);
            }
            else{
                if(moveY == 0) Battlefield.turn(3);
                else Battlefield.turn(4);
                if(moveY == 0) System.out.println(2);
                else System.out.println(4);
            }


        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
