package gr.joints;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import static gr.joints.MyConstants.MyConsts.PPM;		// IMPORT static word as well!!!

import gr.joints.MyConstants.MyInputs;
import gr.joints.managers.MyScreenManager;

public class MyGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private OrthographicCamera cam, b2dcam;
	private MyScreenManager myScreenManager;
	private World world;
	private Box2DDebugRenderer b2drenderer;
	
	private final float STEP = 1/45f;
	
	private float accum;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		cam = new OrthographicCamera();
		b2dcam = new OrthographicCamera();
		world = new World(new Vector2(0, -9.81f), false);
		b2drenderer = new Box2DDebugRenderer();
		
		myScreenManager = new MyScreenManager(this);	// IMPORTANT!!! ALWAYS last in constructor!!!
	}
	
	public void update(float dt) {
		world.step(STEP,  6,  2);
		MyInputs.myInputUpdate();
		myScreenManager.myScreenManagerUpdate(STEP);		
	}

	@Override
	public void render () {		
		accum += Gdx.graphics.getDeltaTime();
		while(accum >= STEP) {
			accum -= STEP;
			update(STEP);
		}
		
		myScreenManager.inputHandler(STEP);
		myScreenManager.render();
	}
	
	@Override
	public void resize(int width, int height) {
		myScreenManager.resize(width, height);
	}
	
	@Override
	public void dispose () {
		myScreenManager.dispose();
		batch.dispose();
		world.dispose();
		b2drenderer.dispose();
	}
	
	//............................... Getters - Setters .........................................
	public SpriteBatch getSpriteBatch() { return batch; }
	public OrthographicCamera getCam() { return cam; }
	public OrthographicCamera getb2dCam() { return b2dcam; }
	public World getWorld() { return world; }
	public Box2DDebugRenderer getBox2DDebugRenderer() { return b2drenderer; }
	
}// END
