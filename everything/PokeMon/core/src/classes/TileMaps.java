package classes;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.ArrayList;

public class TileMaps implements InputProcessor, Screen {
    Texture img;
    TiledMap tiledMap;
    OrthographicCamera camera;
    SpriteBatch sb;
    Texture texture;
    Sprite sprite;
    TiledMapRenderer tiledMapRenderer;
    ArrayList<Integer> toX;
    ArrayList<Integer> toY;
    int single = 1;
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.LEFT) {
            if(toX.size() < 4){
                for(int i = 0; i < 4; i++) {
                    toX.add(-4);
                    toY.add(0);
                }
            }
            //camera.translate(-16, 0);
        }
        else if(keycode == Input.Keys.RIGHT) {
            if(toX.size() < 4){
                for(int i = 0; i < 4; i++) {
                    toX.add(4);
                    toY.add(0);
                }
            }
            //camera.translate(16, 0);
        }
        else if(keycode == Input.Keys.UP) {
            if(toX.size() < 4){
                for(int i = 0; i < 4; i++) {
                    toX.add(0);
                    toY.add(4);
                }
            }
            //camera.translate(0, 16);
        }
        else if(keycode == Input.Keys.DOWN) {
            if(toX.size() < 4){
                for(int i = 0; i < 4; i++) {
                    toX.add(0);
                    toY.add(-4);
                }
            }
            //camera.translate(0, -16);
        }
        if(keycode == Input.Keys.NUM_1) {
            tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
        }
        if(keycode == Input.Keys.NUM_2) {
            tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
        }
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

    @Override
    public void show() {

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        toX = new ArrayList<Integer>();
        toY = new ArrayList<Integer>();
        sb = new SpriteBatch();
        texture = new Texture("core/assets/imgs/black dude ghost.png");
        sprite = new Sprite(texture);
        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);

        if(single == 1){
            camera.zoom -= .5;
            single--;
        }


        camera.update();
        tiledMap = new TmxMapLoader().load("core/assets/maps/Hearthome City.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        Gdx.input.setInputProcessor(this);
    }
    public void moveOrder(){
        if(toX.size() != 0) {
            camera.translate(toX.get(0), toY.get(0));
            toX.remove(0);
            toY.remove(0);
        }
    }
    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        moveOrder();
        camera.update();
        tiledMapRenderer.setView(camera);
        //for(int i = 0; i < 7; i ++)
            //tiledMapRenderer.renderTileLayer((TiledMapTileLayer)(tiledMap.getLayers().get(i)));
        tiledMapRenderer.render();
        sb.begin();
        sprite.setSize(32, 48);
        sprite.draw(sb);
        sprite.setPosition(608, 344);
        sb.end();
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

    }
}

