package com.region.base.fatherless;


import classes.Battlefield;
import classes.TileMaps;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.region.screens.Splash;

public class UnknownTrainer extends Game {
	
	
	@Override
	public void create () {
//		Battlefield.battlefield("Trump");
//		setScreen((Screen) new TileMaps());
		setScreen((Screen) new Splash());
	}

	@Override
	public void render () {
	    super.render();
		/*
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
		*/
	}
	
	@Override
	public void dispose () {

        super.dispose();
	}

	public void resize(int width, int height){
	    super.resize(width, height);
    }

	public void pause(){
	    super.pause();
    }

    public void resume(){
	    super.resume();
    }
}
