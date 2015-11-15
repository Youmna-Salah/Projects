package eg.edu.guc.santorini;

import eg.edu.guc.santorini.exceptions.GameIsOver;
import   eg.edu.guc.santorini.exceptions.InvalidMoveException;
import   eg.edu.guc.santorini.exceptions.InvalidPlacementException;
import   eg.edu.guc.santorini.players.Player;
import   eg.edu.guc.santorini.tiles.Piece;
import   eg.edu.guc.santorini.utilities.Location;

public interface  BoardInterface {
	int SIDE = 5;
	void  move(Piece Piece,   Location newLocation)  throws  InvalidMoveException, GameIsOver;
	void  place(Piece Piece,   Location newLocation)  throws  InvalidPlacementException, GameIsOver;
	boolean  isGameOver();
	boolean  isWinner(Player player); 
	boolean  hasNoMoves(Player player); 
	Player  getWinner();
	boolean  canMove(Piece Piece,   Location location); 
	boolean  canPlace(Piece Piece,   Location location); 
	Player  getTurn();
	String [][] display();
	
}
