package eg.edu.guc.santorini.GUI;

import java.io.IOException;
import java.util.ArrayList;

import eg.edu.guc.santorini.Board;
import eg.edu.guc.santorini.exceptions.GameIsOver;
import eg.edu.guc.santorini.exceptions.InvalidMoveException;
import eg.edu.guc.santorini.exceptions.InvalidPlacementException;
import eg.edu.guc.santorini.players.Player;
import eg.edu.guc.santorini.tiles.Piece;
import eg.edu.guc.santorini.utilities.Location;

public class Adaptor {
static Board board ;
protected static Piece holdedPiece;
Window window;
public Adaptor(String name1,int type1,String name2,int type2) throws IOException{
	 Player p1 = new Player(name1,type1);
     Player p2 = new Player(name2,type2);
     Board board = new Board(p1,p2);
	this.board =board;
	window =new Window();
}

public static  String getTileType(int y,int x){
 return  board.display()[y][x];
}
public static  ArrayList<Location> getPos(int y,int x ){
	Piece p =board.getPiece(y, x);
	if(p.getMoves()%2==0){
	return p.possibleMoves() ;
	}
	else {
		return p.possiblePlacements();
	}
}
public static  boolean can(int y,int x){
		Piece p =board.getPiece(y, x);
		if(p.getMoves()%2==0){
		return true ;
		}
		else {
			return false;
		}
}
public static ArrayList<Location> Clicked(int y,int x) throws InvalidMoveException, InvalidPlacementException{
	ArrayList<Location> temp =new ArrayList<Location>();
	if( board.display()[y][x].length()>1){
	Piece p =board.getPiece(y, x);
	
	if(board.getTurn().getT1()==p ||board.getTurn().getT2()==p){
		holdedPiece = p;
		
	}
	else return null;
	
	}
	else {
	if(holdedPiece != null){	
		if(holdedPiece.getMoves()%2==0){
			temp.add(holdedPiece.getLocation());
		 try {
			board.move(holdedPiece, new Location(y,x));
		} catch (GameIsOver e) {
			new TheWinner(board).setVisible(true);
		}
		 temp.add(new Location(y,x));
		 
	}
		else{
			try {
				board.place(holdedPiece, new Location(y,x));
			} catch (GameIsOver e) {
				new TheWinner(board).setVisible(true);

			}
			System.out.println(y+","+x);
//			 temp.add(holdedPiece.getLocation());
			 temp.add(new Location(y,x));
			 holdedPiece =null;
			 
			}
		}
	}
	return temp;
}
public static String getWin(){
	return board.getWinner().getName();
}
public static String getTurn(){
	String state = "";
	if (board.getTurn().getMoves()%2==0) {
		state = " to MOVE";
	} else {
		state = " to PLACE";
	}
	return board.getTurn().getName()+"'s Turn "+state;
}
}
