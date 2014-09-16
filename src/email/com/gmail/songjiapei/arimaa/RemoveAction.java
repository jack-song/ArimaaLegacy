package email.com.gmail.songjiapei.arimaa;

import email.com.gmail.songjiapei.arimaa.ShiftMove.Direction;
import android.graphics.Point;


public class RemoveAction extends GameAction {

	protected Point position;
	protected Piece piece;
	
	public RemoveAction(Point position, Piece piece) {
		super();
		this.position = position;
		this.piece = piece;
		// TODO Auto-generated constructor stub
	}
	
	public Point getPosition(){
		return position;
	}
	
	public Piece getPiece(){
		return piece;
	}

	public String toString(){
		
		return "R" + piece.getLetter() + Board.positionToString(position) + "x";
	}
	
	public static RemoveAction fromString(String str){
		Piece piece = new Piece(str.charAt(1));
		Point position = Board.stringToPosition(str.substring(2, 4));
		return new RemoveAction(position, piece);
	}
}
