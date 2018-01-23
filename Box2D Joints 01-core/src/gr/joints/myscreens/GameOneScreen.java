package gr.joints.myscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import gr.joints.managers.MyInputManager;
import gr.joints.managers.MyScreenManager;
import static gr.joints.MyConstants.MyConsts.PPM;
import static gr.joints.MyConstants.MyConsts.STEP;

public class GameOneScreen extends GraniteScreen{
	private SpriteBatch sb;
	private Texture backgroundTex, mcTex;
	private TextureRegion tr, currentFrame;
	private TextureRegion[] mc2StandingTextureRegion;
	private Sprite backgroundSprite, mcSprite; // mcSprite = MainCharacterSprite
	private OrthographicCamera cam;
	private TextureAtlas atlas;
	private Animation<TextureRegion> ani;
	private float ff;
	
	public GameOneScreen(MyScreenManager msm) {		// H pista me ta bouna
		super(msm);
		
		sb = getSb();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 480, 270);
		atlas = new TextureAtlas(Gdx.files.internal("gameone_ani/gameone.atlas"));
		tr = atlas.findRegion("pista1");
		backgroundSprite = new Sprite(tr);
		mc2StandingTextureRegion = new TextureRegion[30];
		//ani = new Animation<TextureRegion>(1/30f, atlas.findRegions("mc2standing"));
		
		mc2StandingTextureRegion[0] = atlas.findRegion("mc2standing0001");
		mc2StandingTextureRegion[1] = atlas.findRegion("mc2standing0002");
		mc2StandingTextureRegion[2] = atlas.findRegion("mc2standing0003");
		mc2StandingTextureRegion[3] = atlas.findRegion("mc2standing0004");
		mc2StandingTextureRegion[4] = atlas.findRegion("mc2standing0005");
		mc2StandingTextureRegion[5] = atlas.findRegion("mc2standing0006");
		mc2StandingTextureRegion[6] = atlas.findRegion("mc2standing0007");
		mc2StandingTextureRegion[7] = atlas.findRegion("mc2standing0008");
		mc2StandingTextureRegion[8] = atlas.findRegion("mc2standing0009");
		mc2StandingTextureRegion[9] = atlas.findRegion("mc2standing0010");
		mc2StandingTextureRegion[10] = atlas.findRegion("mc2standing0011");
		mc2StandingTextureRegion[11] = atlas.findRegion("mc2standing0012");
		mc2StandingTextureRegion[12] = atlas.findRegion("mc2standing0013");
		mc2StandingTextureRegion[13] = atlas.findRegion("mc2standing0014");
		mc2StandingTextureRegion[14] = atlas.findRegion("mc2standing0015");
		mc2StandingTextureRegion[15] = atlas.findRegion("mc2standing0016");
		mc2StandingTextureRegion[16] = atlas.findRegion("mc2standing0017");
		mc2StandingTextureRegion[17] = atlas.findRegion("mc2standing0018");
		mc2StandingTextureRegion[18] = atlas.findRegion("mc2standing0019");
		mc2StandingTextureRegion[19] = atlas.findRegion("mc2standing0020");
		mc2StandingTextureRegion[20] = atlas.findRegion("mc2standing0021");
		mc2StandingTextureRegion[21] = atlas.findRegion("mc2standing0022");
		mc2StandingTextureRegion[22] = atlas.findRegion("mc2standing0023");
		mc2StandingTextureRegion[23] = atlas.findRegion("mc2standing0024");
		mc2StandingTextureRegion[24] = atlas.findRegion("mc2standing0025");
		mc2StandingTextureRegion[25] = atlas.findRegion("mc2standing0026");
		mc2StandingTextureRegion[26] = atlas.findRegion("mc2standing0027");
		mc2StandingTextureRegion[27] = atlas.findRegion("mc2standing0028");
		mc2StandingTextureRegion[28] = atlas.findRegion("mc2standing0029");
		mc2StandingTextureRegion[29] = atlas.findRegion("mc2standing0030");
		
		//ani = new Animation<TextureRegion>(1/30f, atlas.findRegion("mc2standing0016"));
		ani = new Animation<TextureRegion>(1/30f, mc2StandingTextureRegion);
		
		Gdx.input.setInputProcessor(new MyInputManager());
	}

	@Override
	public void update(float dt) {
		ff += dt;
		cam.update();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 75f/255f, 80f/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		
		backgroundSprite.draw(sb);
		
		//sb.draw(ani.getKeyFrame(ff, true), 0,0);
		
		
		currentFrame = ani.getKeyFrame(ff, true);
		//sb.draw(currentFrame, 0, 0);
		sb.draw(currentFrame, 0, 0, 0, 0, currentFrame.getRegionWidth(), currentFrame.getRegionHeight(), .2f, .2f, 0);
		
		sb.end();
	}

	@Override
	public void dispose() {
		backgroundSprite.getTexture().dispose();
		atlas.dispose();
	}

	@Override
	public void resize(int width, int height) {
		cam.setToOrtho(false, 480, 270);
	}

}// END CLASS
