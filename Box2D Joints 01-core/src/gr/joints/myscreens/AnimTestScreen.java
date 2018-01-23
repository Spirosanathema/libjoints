package gr.joints.myscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import gr.joints.managers.MyScreenManager;
import static gr.joints.MyConstants.MyConsts.PPM;
import static gr.joints.MyConstants.MyConsts.STEP;

public class AnimTestScreen extends GraniteScreen{
	
	private Sprite sprite;
	private Texture tex, runnerTex;
	private TextureAtlas atlas, satlas;
	private TextureRegion[] region, regionStanding, regionWalkingL, regionWalkingR;
	private TextureRegion regionFrame;
	private TextureRegion[][] regionTemp;
	private OrthographicCamera cam;
	private World world;
	private Box2DDebugRenderer renderer;
	private SpriteBatch sb;
	private Animation<TextureRegion> ani, sani;
	
	private float ff;
	private float regionFrameX, regionFrameY;
	
	private int cols, rows;
	
	public static boolean moveRight, moveLeft;
	
	// FOR ANIMATION WITHOUT TEXTUREATLAS
	// We create 1 TextureRegion[][] temp, 1TextureRegion[] region, and one TextureRegion currentFrame.
	// We create a Texture with the spriteSheet which is numbered well
	// We split the texture with temp = TextureRegion.split(runnerTex, runnerTex.getWidth() / cols, runnerTex.getHeight() / rows);
	// We put all textureregions into region TextureRegion eg. region[ppp++] = regionTemp[i][j];
	// We create animation object with region and STEP eg. ani = new Animation<TextureRegion>(STEP, region);
	// in the render() we put: currentFrame = ani.getKeyFrame(elapsedTime, true); and sb.draw(currentFrame, 300, 200);
	
	
	public AnimTestScreen(MyScreenManager msm) {
		super(msm);
		sb = getSb();
		tex = new Texture(Gdx.files.internal("mesa/mesa2.png"));
		//runnerTex = new Texture(Gdx.files.internal("runner_ani/runner.png"));
		//atlas = new TextureAtlas(Gdx.files.internal("birds_animation/birds.atlas"));
		//atlas = new TextureAtlas(Gdx.files.internal("runner_ani/run.atlas"));
		//atlas = new TextureAtlas(Gdx.files.internal("tiger_ani/tiger.atlas"));
		//atlas = new TextureAtlas(Gdx.files.internal("miki_ani/miki.atlas"));
		//atlas = new TextureAtlas(Gdx.files.internal("girl_ani/girl.atlas"));
		//atlas = new TextureAtlas(Gdx.files.internal("run2_ani/run2.atlas"));
		atlas = new TextureAtlas(Gdx.files.internal("spacer/sr.atlas"));
		region = new TextureRegion[24];
		regionWalkingL = new TextureRegion[8];
		regionWalkingR = new TextureRegion[4];
		regionStanding = new TextureRegion[6];
		ff = 0;
		ani = new Animation<TextureRegion>(3 * STEP, atlas.getRegions());
		
		sprite = new Sprite(tex);
		
		cols = 6;
		rows = 4;
		
		regionFrameX = 0;
		regionFrameY = 10;
		
		
//		if(sb != null)
//			System.out.println("sb den einai null");		===> TEST FOR OBJECT DISTRUCTION
//		if(atlas != null)
//			System.out.println("atlas den einai null");
		
		
		
//		region[0] = atlas.findRegion("0");
//		region[1] = atlas.findRegion("2");
//		region[2] = atlas.findRegion("3");
		regionWalkingR[0] = atlas.findRegion("6");
		regionWalkingR[1] = atlas.findRegion("7");
		regionWalkingR[2] = atlas.findRegion("8");
		regionWalkingR[3] = atlas.findRegion("9");
		
		regionStanding[0] = atlas.findRegion("0");
		regionStanding[1] = atlas.findRegion("1");
		regionStanding[2] = atlas.findRegion("2");
		regionStanding[3] = atlas.findRegion("3");
		regionStanding[4] = atlas.findRegion("4");
		regionStanding[5] = atlas.findRegion("5");
		
		regionWalkingL[0] = atlas.findRegion("10");
		regionWalkingL[1] = atlas.findRegion("11");
		regionWalkingL[2] = atlas.findRegion("12");
		regionWalkingL[3] = atlas.findRegion("13");
		regionWalkingL[4] = atlas.findRegion("14");
		regionWalkingL[5] = atlas.findRegion("15");
		regionWalkingL[6] = atlas.findRegion("16");
		regionWalkingL[7] = atlas.findRegion("17");
				
		
		//ani = new Animation<TextureRegion>(2f * STEP, region);
		
		//regionTemp = TextureRegion.split(runnerTex, runnerTex.getWidth() / cols, runnerTex.getHeight() / rows);
//		int ppp = 0;
//		for(int i=0; i<rows; i++) {
//			for(int j=0; j<cols; j++) {
//				region[ppp++] = regionTemp[i][j];
//			}
//		}
		
		//ani = new Animation<TextureRegion>(STEP, region);
		//ani = new Animation<TextureRegion>(4 * STEP, regionStanding);
		
		cam = getCam();
		//cam.setToOrtho(false, 960/2, 540/2);
		cam.setToOrtho(false);
		satlas = new TextureAtlas(Gdx.files.internal("spacer/spsp.atlas"));
		sani = new Animation<TextureRegion>(1f/45f ,satlas.getRegions());
	}

	@Override
	public void update(float dt) {
		ff += dt;
		moveChar();
		moveRight = false;
		moveLeft = false;
	}

	@Override
	public void render() {
		//Gdx.gl.glClearColor(1, 67f/255f, 38f/255f, 1);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		//sb.draw(sprite,300,200);
		//System.out.println("ff = "+ff);
		//sb.draw(ani.getKeyFrame(ff, true), 40, 30);
		//regionFrame = ani.getKeyFrame(ff, true);
		
		//sb.draw(regionFrame, regionFrameX, regionFrameY);
		
		sb.draw(sani.getKeyFrame(ff, true), 0,0);
		sb.end();
	}

	@Override
	public void dispose() {
		tex.dispose();
		atlas.dispose();
	}

	@Override
	public void resize(int width, int height) {
		
		
	}
	
	public void moveChar() {
		if(moveRight && !moveLeft) {
			ani = new Animation<TextureRegion>(4*STEP, regionWalkingR);
			regionFrameX += 5f;
		}
		else if(moveLeft && !moveRight) {
			ani = new Animation<TextureRegion>(4*STEP, regionWalkingL);
			regionFrameX -= 5f;
		}
			
		else 
			ani = new Animation<TextureRegion>(4*STEP, regionStanding);
	}

}
