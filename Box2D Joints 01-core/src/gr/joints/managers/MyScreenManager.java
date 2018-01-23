package gr.joints.managers;

import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import gr.joints.MyGame;
import gr.joints.myscreens.AnimTestScreen;
import gr.joints.myscreens.BackMotionScreen;
import gr.joints.myscreens.GameOneScreen;
import gr.joints.myscreens.GraniteScreen;
import gr.joints.myscreens.RopeScreen;
import gr.joints.myscreens.ScreenMenu;
import gr.joints.myscreens.TileMapTestScreen;

public class MyScreenManager {
	private MyGame game;
	private Stack<GraniteScreen> screensStack;
	
	private final int MENUSCREEN = 0;
	private final int ROPEJOINTSCREEN = 1;
	private final int ANIMTESTSCREEN = 2;
	private final int TILEMAPTESTSCREEN = 3;
	private final int BACKMOTIONSCREEN = 4;
	private final int GAMEONESCREEN = 5;
	
	private int screenState;
	
//......................... End of Fields ...................................................................	
	
	public MyScreenManager(MyGame game) {
		this.game = game;
		screensStack = new Stack<GraniteScreen>();		
		//pushScreen(ROPEJOINTSCREEN);	
		pushScreen(MENUSCREEN);	
	}
	
	public void myScreenManagerUpdate(float dt) {
		//inputHandler(dt);
		screensStack.peek().update(dt);
	}
	
	public void render() {
		screensStack.peek().render();
	}
	
	public void resize(int width, int height) {
		screensStack.peek().resize(width, height);
	}
	
	public void dispose() {
		screensStack.peek().dispose();
	}
	
	//.................... Input Handler .......................................................
	public void inputHandler(float deltaTime) {
		
		if(Gdx.input.isKeyJustPressed(Keys.F1)) {	// Changer Screens
			if(screenState == ROPEJOINTSCREEN) {
				popScreenAndDispose();
				pushScreen(ANIMTESTSCREEN);	
			}
			
			else if(screenState == ANIMTESTSCREEN) {
				popScreenAndDispose();
				pushScreen(GAMEONESCREEN);
			}			
			
			else if(screenState == GAMEONESCREEN) {
				popScreenAndDispose();
				pushScreen(MENUSCREEN);
			}			
			
			else if(screenState == MENUSCREEN) {
				popScreenAndDispose();
				pushScreen(TILEMAPTESTSCREEN);
			}
			
			else if(screenState == TILEMAPTESTSCREEN) {
				popScreenAndDispose();
				pushScreen(BACKMOTIONSCREEN);
			}
			
			else if(screenState == BACKMOTIONSCREEN) {
				popScreenAndDispose();
				pushScreen(ROPEJOINTSCREEN);
			}
		}		
				
		if( screensStack.peek() instanceof ScreenMenu) {					// Find out which screen you are on right now !
			if(Gdx.input.isKeyPressed(Keys.RIGHT)) { ScreenMenu.setDynoHorizontalMove(1f);}
		if(Gdx.input.isKeyPressed(Keys.LEFT)) { ScreenMenu.setDynoHorizontalMove(-1f);}
		if(Gdx.input.isKeyJustPressed(Keys.UP)) { ScreenMenu.setDynoVerticalMove(1f);}
		if(Gdx.input.isKeyJustPressed(Keys.DOWN)) {ScreenMenu.setDynoVerticalMove(-1f);}
		}
		
		if( screensStack.peek() instanceof AnimTestScreen) {					// Find out which screen you are on right now !
//			//Rope Joint Menu
//			if((Gdx.input.getX(0) >= Gdx.graphics.getWidth() /2f - AnimTestScreen.ropeJointMenu.myFontWidth /2f) && 
//					Gdx.input.getX(0) < Gdx.graphics.getWidth() /2f + AnimTestScreen.ropeJointMenu.myFontWidth /2f) {
//				if((Gdx.input.getY(0) >= AnimTestScreen.menuMenu.myFontHeight + 30f ) &&
//						Gdx.input.getY(0) < AnimTestScreen.menuMenu.myFontHeight + 30f + AnimTestScreen.ropeJointMenu.myFontHeight) {
//					System.out.println("Rope Joint Button!!!");
//				}
//			}
//			//Revolute Joint Menu
//			if((Gdx.input.getX(0) >= Gdx.graphics.getWidth() /2f - AnimTestScreen.revoluteJointMenu.myFontWidth /2f) && 
//					Gdx.input.getX(0) < Gdx.graphics.getWidth() /2f + AnimTestScreen.revoluteJointMenu.myFontWidth /2f) {
//				if((Gdx.input.getY(0) >= AnimTestScreen.menuMenu.myFontHeight + 30f + AnimTestScreen.ropeJointMenu.myFontHeight ) &&
//						Gdx.input.getY(0) < AnimTestScreen.menuMenu.myFontHeight + 30f + AnimTestScreen.ropeJointMenu.myFontHeight
//						+ AnimTestScreen.revoluteJointMenu.myFontHeight) {
//					System.out.println("Revolute Joint Button!!!");
//				}
//			}
			
			if(Gdx.input.isKeyPressed(Keys.RIGHT))
				AnimTestScreen.moveRight = true;
			if(Gdx.input.isKeyPressed(Keys.LEFT))
				AnimTestScreen.moveLeft = true;
		}
		
//		if((Gdx.input.getX() >150 && Gdx.input.getY() >20) && (Gdx.input.getX() < 200 && Gdx.input.getY() < 150)) { // Create "Button"
//			System.out.println("Button");
//		}
		
	}
	
	//---------------------------------- 4 Screen Manager Methods (IMPORTANT!!!) -----------------------------------
	public void popScreenAndDispose() {
		GraniteScreen graniteScreen = screensStack.pop();			// 1
		graniteScreen.dispose();
		//System.out.println("Etrexa popScreenAndDispose");
	}
	public GraniteScreen getScreenFromUser(int state) {			//	  2
		if(state == MENUSCREEN) {
			screenState = MENUSCREEN;
			//System.out.println("etrexa getScreenFromUser = " + screenState);
			return new ScreenMenu(this);
		}
		
		else if(state == ANIMTESTSCREEN) {
			screenState = ANIMTESTSCREEN;
			return new AnimTestScreen(this);
		}
		
		else if(state == ROPEJOINTSCREEN) {
			screenState = ROPEJOINTSCREEN;
			//System.out.println("etrexa getScreenFromUser = " + screenState);
			return new RopeScreen(this);
		}
		else if(state == TILEMAPTESTSCREEN) {
			screenState = TILEMAPTESTSCREEN;
			//System.out.println("etrexa getScreenFromUser = " + screenState);
			return new TileMapTestScreen(this);
		}
		else if(state == BACKMOTIONSCREEN) {
			screenState = BACKMOTIONSCREEN;
			//System.out.println("etrexa getScreenFromUser = " + screenState);
			return new BackMotionScreen(this);
		}
		else if(state == GAMEONESCREEN) {
			screenState = GAMEONESCREEN;
			//System.out.println("etrexa getScreenFromUser = " + screenState);
			return new GameOneScreen(this);
		}
		else {
			screenState = MENUSCREEN;
			//System.out.println("etrexa ELSE getScreenFromUser = " + screenState);
			return new ScreenMenu(this);
		}
	}
	public void pushScreen(int state) {							//    3
		screensStack.push(getScreenFromUser(state));
		//System.out.println("etrexa pushScreen, state = " + state);
	}
	public void setScreenToStack(int state) {					//   4
		popScreenAndDispose();
		pushScreen(state);
		//System.out.println("Etrexa setScreenToStack, stateScreen = " + state);
	}
	
	
	
	//................................. Getters - Setters ......................................................
	public MyGame getGame() { return game; }
	
}// END
