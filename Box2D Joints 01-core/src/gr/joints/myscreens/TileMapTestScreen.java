package gr.joints.myscreens;

import static gr.joints.MyConstants.MyConsts.PPM;
import static gr.joints.MyConstants.MyConsts.aspectRatio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import gr.joints.managers.MyBox2DManager;
import gr.joints.managers.MyScreenManager;
import gr.joints.managers.MyTileManager;

public class TileMapTestScreen extends GraniteScreen{
	
	private SpriteBatch sb;
	private Texture tex;
	private OrthographicCamera cam, b2dCam, tileCam;
	private World world;
	private Box2DDebugRenderer renderer;
	
	private TiledMap map;
	private TiledMapRenderer tmr;
	private MapProperties mapProperties;	
	private int mapWidthInTiles, mapHeightInTiles, tileWidth, tileHeight;
	private float mapWidth, mapHeight;
	
	Body body, tileBody;
	
	public TileMapTestScreen(MyScreenManager msm) {
		super(msm);
		sb = getSb();
		tex = new Texture(Gdx.files.internal("owl.jpg"));
		
		cam = getCam();		
		b2dCam = getb2dCam();
		tileCam = new OrthographicCamera();
		
		// When we setToOrth, we Always set box2d cam's viewport /ppm according to tilemap's cam     IMPORTANT !!!!!
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		tileCam.setToOrtho(false, Gdx.graphics.getWidth() /2, Gdx.graphics.getHeight() /2);
		//tileCam.setToOrtho(false, 320 , 320);
		b2dCam.setToOrtho(false, Gdx.graphics.getWidth() /PPM/2, Gdx.graphics.getHeight() /PPM/2);
		//b2dCam.setToOrtho(false, 320 /PPM,320 /PPM);
		
		world = getWorld();
		renderer = gerBox2DDebugRenderer();
		
		map = new TmxMapLoader().load("tilees/spirosmap3.tmx");
		tmr = new OrthogonalTiledMapRenderer(map);
		tmr.setView(tileCam);
		
		mapProperties = map.getProperties();
		mapWidthInTiles = mapProperties.get("width", Integer.class);
		mapHeightInTiles = mapProperties.get("height", Integer.class);
		tileWidth = mapProperties.get("tilewidth", Integer.class);
		tileHeight = mapProperties.get("tileheight", Integer.class);
		
		mapWidth = mapWidthInTiles * tileWidth;		// 320
		mapHeight = mapHeightInTiles * tileHeight;	// 320
		
		//System.out.println("mapWidth = " + mapWidth + "\nmapHeight = " + mapHeight + "\nmap Layers = " + map.getLayers().getCount());
		
		body = MyBox2DManager.createCircleBody(false, 190, 100, 50, world, renderer);
		tileBody = MyTileManager.parseTiledObjectLayer(world, map.getLayers().get("collision_layer").getObjects());
		
	}

	@Override
	public void update(float dt) {
		world.step(dt, 6, 2);
		cam.update();
		b2dCam.update();
		tileCam.update();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//tmr.render();
		
		sb.setProjectionMatrix(cam.combined);
		
		sb.begin();
		sb.draw(tex, 0, 0);
		sb.end();
		
		//renderer.render(world, tileCam.combined);
		tmr.render();
		renderer.render(world, b2dCam.combined);
	}

	@Override
	public void dispose() {
		//tex.dispose();
		world.destroyBody(body);
		world.destroyBody(tileBody);
	}

	@Override
	public void resize(int width, int height) {
		
		//cam.setToOrtho(false, tex.getWidth(), tex.getHeight());
//		cam.setToOrtho(false, width/2, height/2);
//		tileCam.setToOrtho(false, Gdx.graphics.getWidth() /2, Gdx.graphics.getHeight() /2);
//		b2dCam.setToOrtho(false, Gdx.graphics.getWidth() /PPM/2, Gdx.graphics.getHeight() /PPM/2);
		
	}

}
