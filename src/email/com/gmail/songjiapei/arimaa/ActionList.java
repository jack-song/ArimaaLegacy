package email.com.gmail.songjiapei.arimaa;

import java.util.ArrayList;

import android.graphics.Point;

public class ActionList {
	
	ArrayList<GameAction> actions;
	ArrayList<Boolean> moves;

	public ActionList() {
		actions = new ArrayList<GameAction>();
		moves = new ArrayList<Boolean>();
	}
	
	public boolean isEmpty(){
		return actions.isEmpty();
	}
	
	public boolean isMovesEmpty(){
		for(int i = 0; i < moves.size(); i++){
			if(moves.get(i)){
				return false;
			}
		}
		
		return true;
	}
	
	public void clear() {
		actions.clear();
		moves.clear();
	}
	
	public void addMove(MoveAction m){
		actions.add(m);
		moves.add(true);
	}
	
	public void addGameAction(GameAction a){
		actions.add(a);
		moves.add(false);
	}
	
	public MoveAction getLastMove(){
		for(int i = actions.size()-1; i >= 0; i--){
			if(moves.get(i)){
				return (MoveAction) actions.get(i);
			}
		}
		
		return null;
	}
	
	public Point getLastMoveSource(){
		if(!isMovesEmpty())
			return getLastMove().getStart();
		
		return null;
	}
	
	public boolean isLastMoveShift(){
		if(!isMovesEmpty()){
			return getLastMove() instanceof ShiftMove;
		}
		
		return false;
	}
		
	//MUST GETREVERTMOVE FIRST
	public RemoveAction revertMoveAndGetRemoveAction(){
		GameAction lastAction = actions.get(actions.size()-1);
		
		if(lastAction instanceof RemoveAction){
			
			//remove the taking piece action
			actions.remove(moves.size()-1);
			moves.remove(moves.size()-1);
			
			//remove the actual move
			actions.remove(moves.size()-1);
			moves.remove(moves.size()-1);
			
			return (RemoveAction) lastAction;
		}
		
		//remove just the move
		actions.remove(moves.size()-1);
		moves.remove(moves.size()-1);
		
		return null;
	}
	
	//CAN ONLY TAKBACK A SHIFTMOVE
	CpuPlaceMove getRevertedMove(){
		if(isMovesEmpty())
			return null;
		
		ShiftMove lastMove = (ShiftMove) getLastMove();
		
		Point newStart = lastMove.getEnd();
		Point newEnd = lastMove.getStart();
		Piece piece = lastMove.getPiece();
		
		return new CpuPlaceMove(newStart, newEnd, piece, false);		
	}
	
	public Piece getLastPiece(){
		if(!isMovesEmpty())
			return getLastMove().getPiece();
		
		return null;
	}
	
	public String getHistory(){
		String history= "";
		
		for(GameAction action : actions){
			if(null != action)
			{
				history = history.concat(action.toString() + " ");
			}
		}
		
		return history;
	}
	
	public boolean wasPushing(){
		if(!isMovesEmpty())
			return getLastMove().isPush();
		
		return false;
	}

}
