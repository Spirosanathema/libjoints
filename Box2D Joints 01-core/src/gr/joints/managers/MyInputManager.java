package gr.joints.managers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

import gr.joints.MyConstants.MyInputs;

public class MyInputManager implements InputProcessor{

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.UP) {
			MyInputs.setMyKey(MyInputs.UP_KEY, true);
			System.out.println("UP = true");
		}
		if(keycode == Keys.DOWN) {
			MyInputs.setMyKey(MyInputs.DOWN_KEY, true);
		}
		if(keycode == Keys.LEFT) {
			MyInputs.setMyKey(MyInputs.LEFT_KEY, true);
		}
		if(keycode == Keys.RIGHT) {
			MyInputs.setMyKey(MyInputs.RIGHT_KEY, true);
		}
		if(keycode == Keys.SPACE) {
			MyInputs.setMyKey(MyInputs.SPACE_KEY, true);
		}
		if(keycode == Keys.ESCAPE) {
			MyInputs.setMyKey(MyInputs.ESC_KEY, true);
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.UP) {
			MyInputs.setMyKey(MyInputs.UP_KEY, false);
			System.out.println("UP = false");
		}
		if(keycode == Keys.DOWN) {
			MyInputs.setMyKey(MyInputs.DOWN_KEY, false);
		}
		if(keycode == Keys.LEFT) {
			MyInputs.setMyKey(MyInputs.LEFT_KEY, false);
		}
		if(keycode == Keys.RIGHT) {
			MyInputs.setMyKey(MyInputs.RIGHT_KEY, false);
		}
		if(keycode == Keys.SPACE) {
			MyInputs.setMyKey(MyInputs.SPACE_KEY, false);
		}
		if(keycode == Keys.ESCAPE) {
			MyInputs.setMyKey(MyInputs.ESC_KEY, false);
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}	

}// END
