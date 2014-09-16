package email.com.gmail.songjiapei.arimaa;

import android.graphics.Point;
import email.com.gmail.songjiapei.arimaa.ShiftMove.Direction;

public class DoneAction extends GameAction {

	public DoneAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "DD";
	}
	
	public static DoneAction fromString(String str){
		return new DoneAction();
	}

}
