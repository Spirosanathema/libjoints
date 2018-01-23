package gr.joints.myscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import gr.joints.managers.MyScreenManager;

public class RopeScreen extends GraniteScreen{
	private Texture tex;
	private Sprite sprite;
	private OrthographicCamera cam;
	private World world;
	private Box2DDebugRenderer renderer;
	private SpriteBatch sb;
	
	
	public RopeScreen(MyScreenManager msm) {
		super(msm);
		tex = new Texture(Gdx.files.internal("dog.png"));
		sprite = new Sprite(tex);
		cam = getCam();
		cam.setToOrtho(false);
		sb = getSb();
	}

	@Override
	public void update(float dt) {
		cam.update();
		
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.3f, 0.6f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		sb.setProjectionMatrix(cam.combined);
		
		sb.begin();
		sb.draw(sprite, 0, 0);
		sb.end();
	}

	@Override
	public void dispose() {
		sprite.getTexture().dispose();
	}

	@Override
	public void resize(int width, int height) {
		System.out.println("malakas");
		cam.setToOrtho(false, width, height);
		System.out.println("Cam vpWidth" + cam.viewportWidth);
	}

}// END
