package actors;

import Zomtasia.Zomtasia;
//ADD A FUNCTION FOR THIS IN THE SWITCH!
import com.badlogic.gdx.Gdx;

public class ButtonFunctions {
	public static void function(int function){
		switch(function){
			case 1:
				Zomtasia.getGame();
				Zomtasia.getGame().setScreen(Zomtasia.player);
				Gdx.input.setInputProcessor(Zomtasia.controls);
				Gdx.input.setCursorCatched(true);
				break;
			default:
				System.out.println("BUTTON FUNCTION NOT FOUND: ButtonFunctions.java:4");
		}
	}
}
