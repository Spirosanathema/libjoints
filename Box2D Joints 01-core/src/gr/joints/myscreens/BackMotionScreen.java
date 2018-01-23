package gr.joints.myscreens;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import gr.joints.managers.MyScreenManager;

public class BackMotionScreen extends GraniteScreen{
	private Texture backTex, snowTex;
	private Sprite sp, snowSprite;
	private OrthographicCamera cam, cam2;
	private SpriteBatch sb;
	
	private float camPositionX, camPositionY, horX;
	private float stepSpeed;
	private float snowSpeed;
	
	private boolean horXBool;
	
	private int camPositionXInt;
	private int numberOfSnowflakes;
	
	private Random random;
	
	private ArrayList<Sprite> snowList;
	private ArrayList<Sprite> snowListRemove;

	public BackMotionScreen(MyScreenManager msm) {
		super(msm);
		backTex = new Texture(Gdx.files.internal("bk1.png"));
		backTex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		snowTex = new Texture(Gdx.files.internal("snoflake.png"));
		sp = new Sprite(backTex);
		snowSprite = new Sprite(snowTex);
		cam = new OrthographicCamera();
		cam2 = new OrthographicCamera();
		//cam.setToOrtho(false, backTex.getWidth(), backTex.getHeight());
		cam.setToOrtho(false, 480, 270);
		cam2.setToOrtho(false);
		sb = getSb();
		
		camPositionX = 0;
		camPositionY = 0;
		camPositionXInt = 0;
		stepSpeed = 10;
		horX = 0;
		horXBool = false;
		numberOfSnowflakes = 50;
		snowList = new ArrayList<Sprite>();
		random = new Random();
		
		for(int i=0; i<numberOfSnowflakes; i++) {
			snowList.add(new Sprite(snowTex));
			snowList.get(i).setPosition(random.nextInt((int)backTex.getWidth() / 3), random.nextInt((int)backTex.getHeight()));
			
			float f = random.nextInt(3) + 1;
			//Double sss = ThreadLocalRandom.current().nextDouble(1);
			snowList.get(i).setSize(snowList.get(i).getWidth() / f, snowList.get(i).getHeight() / f);
			snowList.get(i).setColor(0, 0, 0, 1);
		}
		
		
	}

	@Override
	public void update(float dt) {
		bkScreenMotion();
		snowSpeed = -30 * dt;
		snowMotion();
		cam.update();
		
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.draw(backTex, 0, 0, camPositionXInt, 0, 480, 270);
		//sb.draw(sp, camPositionX, camPositionY);
//		if(horXBool) {
//			sb.draw(backTex, 480 - stepSpeed, 0);
//		}
		
		for(int i=0; i<snowList.size(); i++) {
			//sb.draw(snowList[i], random.nextInt((int)backTex.getWidth()), random.nextInt((int)backTex.getHeight()));
			//sb.draw(snowList.get(i), snowList.get(i).getX(), snowList.get(i).getY());
			
			
			snowList.get(i).setAlpha(.3f);
			snowList.get(i).draw(sb);
			
			
			//sb.draw(snowList.get(i), snowList.get(i).getX(), snowList.get(i).getY(), snowList.get(i).getWidth(), snowList.get(i).getHeight());
		}
		
		sb.end();
	}

	@Override
	public void dispose() {
		backTex.dispose();		
	}

	@Override
	public void resize(int width, int height) {
		
		
	}
	
	//............................................. My methods .............................................................
	public void bkScreenMotion() {
		if(camPositionX <= (-backTex.getWidth() + cam.viewportWidth)) {
			horXBool = true;
		}
		if(camPositionX > 0) {
			
		}
		
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			//cam.translate(10, 0);
			//System.out.println("\n\nCam X = "+cam.position.x);
			camPositionX -= stepSpeed;
			camPositionXInt += stepSpeed;
		}
		else if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			//cam.translate(-10, 0);
			//System.out.println("\n\nCam X = "+cam.position.x);
			camPositionX += stepSpeed;
			camPositionXInt -= stepSpeed;
		}
		
	}
	
	public void snowMotion() {
		snowListRemove = new ArrayList<Sprite>();
		if(snowList.size() > 0) {
			for(int i=0; i<snowList.size(); i++) {
				snowList.get(i).translate(random.nextInt(2) - 1, snowSpeed);
				if(snowList.get(i).getY() <= -snowList.get(i).getHeight()) {
					snowListRemove.add(snowList.get(i));
					//createSnowflake();
				}
			}
			snowList.removeAll(snowListRemove);
			createSnowflake();
		}
	}
	
	private void createSnowflake() {
		if(snowList.size() < numberOfSnowflakes) {
			Sprite s = new Sprite(snowTex);
			s.setPosition(random.nextInt(backTex.getWidth() / 3), backTex.getHeight());
			float f = random.nextInt(3) + 1;
			s.setSize(s.getWidth() / f, s.getHeight() / f);
			snowList.add(s);
		}
	}

}// END
