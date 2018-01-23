package gr.joints.myscreens;

import static gr.joints.MyConstants.MyConsts.PPM;
import static gr.joints.MyConstants.MyConsts.STEP;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import gr.joints.managers.MyBox2DManager;
import gr.joints.managers.MyScreenManager;

public class ScreenMenu extends GraniteScreen{
	private Texture tex, dynoTex, vertigoTex;
	private Texture mesa1Tex, mesa2Tex, mesa3Tex, mesa4Tex;
	private Sprite sprite, dynoSprite, vertigoSprite, mesa1Sprite, mesa2Sprite, mesa3Sprite, mesa4Sprite;
	private OrthographicCamera b2dcam;
	private World world;
	private Box2DDebugRenderer renderer;
	private SpriteBatch sb;
	private  Body ground, dyno, player, wallLeft, wallRight, mesa, poly;
	
	private float spriteWidth, spriteHeight, dynoSpriteWidth, dynoSpriteHeight;
	
	private static float horizontalMovement;
	private static float verticalMovement;
	
	private Vector2[] vecOfPoints;
	
	
	public ScreenMenu(MyScreenManager msm) {
		super(msm);
		sb = getSb();
		tex = new Texture(Gdx.files.internal("gun.jpeg"));
		dynoTex = new Texture(Gdx.files.internal("dyno.png"));
		vertigoTex = new Texture(Gdx.files.internal("vertigo3.png"));
		//vertigoTex = new Texture(Gdx.files.internal("mesa/mesa3.png"));
		
		mesa1Tex = new Texture(Gdx.files.internal("mesa/mesa1.png"));
		mesa2Tex = new Texture(Gdx.files.internal("mesa/mesa2.png"));
		mesa3Tex = new Texture(Gdx.files.internal("mesa/mesa3.png"));
		mesa4Tex = new Texture(Gdx.files.internal("mesa/mesa4.png"));
		
		sprite = new Sprite(tex);
		dynoSprite = new Sprite(dynoTex);
		vertigoSprite = new Sprite(vertigoTex);
		
		mesa1Sprite = new Sprite(mesa1Tex);
		mesa2Sprite = new Sprite(mesa2Tex);
		mesa3Sprite = new Sprite(mesa3Tex);
		mesa4Sprite = new Sprite(mesa4Tex);
		
		spriteWidth = Gdx.graphics.getWidth() /PPM;
		spriteHeight = Gdx.graphics.getHeight() /PPM;
		dynoSpriteWidth = 100f /PPM;
		dynoSpriteHeight = 100f /PPM;
		
		horizontalMovement = 0;
		verticalMovement = 0;		
		
		sprite.setSize(spriteWidth, spriteHeight);
		dynoSprite.setSize(dynoSpriteWidth, dynoSpriteHeight);
		vertigoSprite.setSize(130 /PPM, 130 /PPM);
		
		mesa1Sprite.setSize(200 /PPM, 100 /PPM);
		mesa2Sprite.setSize(130 /PPM, 130 /PPM);
		mesa3Sprite.setSize(130 /PPM, 130 /PPM);
		mesa4Sprite.setSize(200 /PPM, 100 /PPM);
		
		world = getWorld();
		renderer = gerBox2DDebugRenderer();
		b2dcam = getb2dCam();
		b2dcam.setToOrtho(false, Gdx.graphics.getWidth() /PPM, Gdx.graphics.getHeight() /PPM);
		
		
		// Bodies Creation
		ground = MyBox2DManager.createPolygonBody(true, Gdx.graphics.getWidth(), 15f, Gdx.graphics.getWidth() + 10f, 15 + 10f, world, renderer);
		dyno = MyBox2DManager.createCircleBody(false, dynoSprite.getWidth(), dynoSprite.getHeight(), 100f, world, renderer);
		//player = MyBox2DManager.createPolygonBody(false, 700, 400, 100, 100, world, renderer);
		player = MyBox2DManager.createCircleBody(false, 400, 400, 130, world, renderer);
		player.getFixtureList().get(0).setRestitution(1f);
		
		wallLeft = MyBox2DManager.createPolygonBody(true, 10, Gdx.graphics.getHeight(), 10, Gdx.graphics.getHeight(), world, renderer);
		wallRight = MyBox2DManager.createPolygonBody(true, 2*Gdx.graphics.getWidth() - 10f, Gdx.graphics.getHeight(), 10, Gdx.graphics.getHeight(), world, renderer);
		mesa = MyBox2DManager.createPolygonBody(false, 600, 300, 200, 100, world, renderer);
		mesa.getFixtureList().get(0).setRestitution(.9f);
		mesa.getFixtureList().get(0).setFriction(.1f);
		
		vecOfPoints = new Vector2[] {new Vector2(4,2), new Vector2(5,3), new Vector2(6,2), new Vector2(5,1)};
		//poly = MyBox2DManager.createTruePolygonBody(false, 600, 100, 4, vecOfPoints, world, renderer);
	}

	@Override
	public void update(float dt) {
		world.step(STEP, 6, 2);		
		dynoMovementHandler(dt);				
		b2dcam.update();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderer.render(world, b2dcam.combined);
		
		sb.begin();
		//sb.draw(sprite, 0, 0, spriteWidth * PPM, spriteHeight * PPM);
		sb.draw(dynoSprite, dyno.getPosition().x *PPM - dynoSprite.getWidth() *PPM /2, dyno.getPosition().y *PPM - dynoSprite.getHeight() *PPM /2,
				dynoSpriteWidth * PPM , dynoSpriteHeight * PPM);
		
		sb.draw(vertigoSprite, 
				player.getPosition().x *PPM - vertigoSprite.getWidth() *PPM /2,	//x 
				player.getPosition().y *PPM - vertigoSprite.getHeight() *PPM /2,	//y 
				vertigoSprite.getWidth() *PPM /2,		//originX 
				vertigoSprite.getHeight() *PPM /2,		//originY 
				130, 		//width
				130, 		//height 
				1,			//scaleX 
				1, 			//scaleY 
				player.getAngle() * PPM);			//rotation
		
		sb.draw(mesa4Sprite, 
				mesa.getPosition().x *PPM - mesa4Sprite.getWidth() *PPM /2,	//x 
				mesa.getPosition().y *PPM - mesa4Sprite.getHeight() *PPM /2,	//y 
				mesa4Sprite.getWidth() *PPM /2,		//originX 
				//0,		//originX 
				//0,		//originY 
				mesa4Sprite.getHeight() *PPM /2,		//originY 
				200, 		//width
				100, 		//height 
				1,			//scaleX 
				1, 			//scaleY 
				//1.57f);			//rotation
				//Math.toRadians(mesa.getTransform().getRotation()) * PPM);			//rotation
				(mesa.getTransform().getRotation() * 180 / (float)Math.PI));			//rotation.  LibGDX draw method is in DEGREES and not in Radians		
				//mesa.getAngle() * PPM);			//rotation								getTransform().getRotation is in RADIANS. Needs convertion
		
		sb.end();
		
		//renderer.render(world, b2dcam.combined);
	}

	@Override
	public void dispose() {
		tex.dispose();
		dynoTex.dispose();
		vertigoTex.dispose();
		mesa1Tex.dispose();
		mesa2Tex.dispose();
		mesa3Tex.dispose();
		mesa4Tex.dispose();
		world.destroyBody(ground);
		world.destroyBody(dyno);
		world.destroyBody(player);
		world.destroyBody(mesa);
		world.destroyBody(wallLeft);
		world.destroyBody(wallRight);
		
	}
	
	public void resize(int width, int height) {
		//b2dcam.setToOrtho(false, width /PPM, height /PPM);
		//b2dcam.setToOrtho(false, width, height);
		
		b2dcam.setToOrtho(false, Gdx.graphics.getWidth() /PPM, Gdx.graphics.getHeight() /PPM);
	}
	
	private void dynoMovementHandler(float delta) {
		//player.setLinearVelocity(0, -2f);
		//player.applyForceToCenter(0, -12f, false);
		
		dyno.setLinearVelocity(2.5f * horizontalMovement, dyno.getLinearVelocity().y);
		dyno.applyForceToCenter(dyno.getLinearVelocity().x, 200f * verticalMovement, true);
		
		horizontalMovement = 0;
		verticalMovement = 0;
	}
	
//	public String toString() {
//		return "ScreenMenus";
//	}
	
	//-------------------------------------  Getters - Setters -------------------------------------------------------
	public static void setDynoHorizontalMove(float horMove) { horizontalMovement = horMove; }
	public static void setDynoVerticalMove(float vertMove) { verticalMovement = vertMove; }
		

}// END
