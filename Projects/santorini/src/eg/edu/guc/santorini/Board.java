package eg.edu.guc.santorini;

import java.util.ArrayList;

import eg.edu.guc.santorini.exceptions.GameIsOver;
import eg.edu.guc.santorini.exceptions.InvalidMoveException;
import eg.edu.guc.santorini.exceptions.InvalidPlacementException;
import eg.edu.guc.santorini.players.Player;
import eg.edu.guc.santorini.tiles.Cube;
import eg.edu.guc.santorini.tiles.Piece;
import eg.edu.guc.santorini.utilities.Location;

public class Board implements BoardInterface{
	
	private String [][] game = new String [BoardInterface.SIDE][BoardInterface.SIDE]; 
	Player player1;
	Player player2;
	
	public Board(Player play1 , Player play2) {
		this.player1=play1;
		this.player2=play2;
		player1.getT1().setLocation(new Location(0,0));
		player1.getT2().setLocation(new Location(4,1));
		player2.getT1().setLocation(new Location(0,3));
		player2.getT2().setLocation(new Location(4,4));
		for (int i = 0; i < game.length; i++) {
			for (int j = 0; j < game[i].length; j++) {
				game[i][j]="0";
			}
		}
		String s1,s2;
		if (player1.getT1() instanceof Cube ) {
			s1="0C1";
		}
		else {
			s1="0P1";
		}
		if (player2.getT2() instanceof Cube ) {
			s2="0C2";
		}
		else {
			s2="0P2";
		}
		game[0][0]=s1;
		game[4][1]=s1;
		game[0][3]=s2;
		game[4][4]=s2;
	}
	
	public String[][] display() {
		return game; 
	}
	
	public Piece getPiece( int Y, int X) {
		if (player1.getT1().getLocation().getY()==Y && player1.getT1().getLocation().getX()==X) {
			return player1.getT1();
		} else {
			if (player1.getT2().getLocation().getY()==Y && player1.getT2().getLocation().getX()==X) {
				return player1.getT2();
			}
		}
		if (player2.getT1().getLocation().getY()==Y && player2.getT1().getLocation().getX()==X) {
			return player2.getT1();
		} else {
			if (player2.getT2().getLocation().getY()==Y && player2.getT2().getLocation().getX()==X) {
				return player2.getT2();
			}
		}
		return null;
	}
	
//	public static void main(String[] args) throws InvalidMoveException, InvalidPlacementException {
//		Player p1 = new Player("P1", 2);
//		Player p2 = new Player("Player 2", 1);
//
//		Board board = new Board(p1, p2);
//
//		
//		
//		
//	}

	@SuppressWarnings("unused")
	private static boolean arrayListsEqual(ArrayList<Location> l1,
			ArrayList<Location> l2) {
		for (int i = 0; i < l1.size(); i++) {
			if(!l1.get(i).equals(l2.get(i))) {
				return false;
			}
		}
		return true;
	}

	public void move(Piece Piece, Location newLocation)
			throws InvalidMoveException, GameIsOver {
		if(isGameOver()) {
			throw new GameIsOver();
		} else {
			if((Piece==this.getTurn().getT1() || Piece==this.getTurn().getT2()) && !this.isGameOver()) {
				Location current = Piece.getLocation();
				int y = current.getY();
				int x = current.getX();
				int newy = newLocation.getY();
				int newx = newLocation.getX();
				String s = game[y][x];
				int mov = Piece.getMoves();
				if (canMove(Piece, newLocation) && Piece.getMoves()%2==0 && getTurn().getMoves()%2==0) {
					Piece.setLocation(newLocation);
					game[newy][newx]+=""+s.charAt(1)+s.charAt(2);
					game[y][x] = ""+s.charAt(0);
					Piece.setMoves(++mov);
			// 		Piece.setTurn(true);
				}
				else {
					throw new InvalidMoveException();
				}
			}
			else {
				throw new InvalidMoveException();
			}
		}
		
	}

	public void place(Piece Piece, Location newLocation)
			throws InvalidPlacementException, GameIsOver {
		if (isGameOver()) {
			throw new GameIsOver();
		} else {
			int newy = newLocation.getY();
			int newx = newLocation.getX();
			String news = game[newy][newx];
			int mov = Piece.getMoves();
			if((canPlace(Piece, newLocation)
					&& Piece.getMoves()%2==1) 
					&& !this.isGameOver()) {
				int pos = Integer.parseInt(news);
				pos++;
				game[newy][newx] = ""+pos;
				Piece.setMoves(++mov);
//				Piece.setTurn(false);
			}
			else {
				throw new InvalidPlacementException();
			}
		}
	}

//	public boolean isGameOver() {
//		if (isWinner(player1) || isWinner(player2)) {
//			return true;
//		}
//		else {
//			return false;
//		}
//	//	return false;
//	}
//
//	public boolean isWinner(Player player) {
//		Piece pi1 = player.getT1();
//		Piece pi2 = player.getT2();
//		int y1 = pi1.getLocation().getY();
//		int x1 = pi1.getLocation().getX();
//		int y2 = pi2.getLocation().getY();
//		int x2 = pi1.getLocation().getX();
//		Player other;
//		if(player==this.player1) {
//			other=player2;
//		}
//		else {
//			other=player1;
//		}
//		if(other==getTurn() && this.hasNoMoves(other)) {
//			return true;
//		}
//		if(game[y1][x1].charAt(0)=='3' || game[y2][x2].charAt(0)=='3') {
//			return true;
//		}
//		else {
//			return false;
//		}
//	}

	public boolean isGameOver() {
		return isWinner(player1) || isWinner(player2);
	}

	@Override
	public boolean isWinner(Player player) {
		if (player == player1) {
			return isLevelThree(player1.getT1()) || isLevelThree(player1.getT2()) 
				|| (hasNoMoves(player2) && getTurn() == player2);
		} else if (player == player2) {
			return isLevelThree(player2.getT1()) || isLevelThree(player2.getT2()) 
				|| (hasNoMoves(player1) && getTurn() == player1);			
		}
		return false;
	}
	private boolean isLevelThree(Piece piece) {
		return game[piece.getLocation().getY()][piece.getLocation().getX()].charAt(0) == '3';
		}
	public boolean hasNoMoves(Player player) {
		Piece pic1 = player.getT1();
		Piece pic2 = player.getT2();
		ArrayList<Location> pi1 = player.getT1().possibleMoves();
		ArrayList<Location> pi2 = player.getT2().possibleMoves();
		for (int i = 0; i < pi1.size(); i++) {
			if(canMove(pic1, pi1.get(i))) {
				return false;
			}
		}
		for (int i = 0; i < pi2.size(); i++) {
			if(canMove(pic2, pi2.get(i))) {
				return false;
			}
		}
		return true;
	}

	public Player getWinner() {
		if(isWinner(player1)) {
			return player1;
		}
		if(isWinner(player2)) {
			return player2;
		}
		return null;
	}

	public boolean contain(ArrayList<Location> temp , Location loccation) {
		for (int i = 0; i < temp.size(); i++) {
			if(temp.get(i).equals(loccation)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean canMove(Piece Piece, Location location) {
		Location current = Piece.getLocation();
		int y = current.getY();
		int x = current.getX();
		int newy = location.getY();
		int newx = location.getX();
		String s = game[y][x];
		String news = game[newy][newx];
		int level = s.charAt(0);
		int newlevel = news.charAt(0);
		return (contain(Piece.possibleMoves(),location) && newlevel < '4'  && !(newlevel > level+1) && news.length()==1);
	}

	public boolean canPlace(Piece Piece, Location location) {
		int newy = location.getY();
		int newx = location.getX();
		String news = game[newy][newx];
		return (contain(Piece.possiblePlacements(),location) && news.length()==1 && !news.equals("4"));
	}

	public boolean right(Piece piece) {
		Player it = getTurn();
		return (piece==it.getT1() || piece==it.getT2());
	}
	
	public Player getTurn() {
		if (player2.getMoves()==player1.getMoves()-2 || player2.getMoves()%2==1) {
			return player2;
		} 
		else {
			return player1;
		}
	}
public static void main (String [] args) throws InvalidMoveException, InvalidPlacementException, GameIsOver{
	Player p1 = new Player("name",1);
	Player p2 = new Player("name2",2);
	Board board = new Board(p1,p2);
	board.game[0][0] = "0";
	board.game[0][1] = "0";
	board.game[0][2] = "1C1";
	board.game[0][3] = "0";
	board.game[0][4] = "0";

	board.game[1][0] = "0";
	board.game[1][1] = "2C1";
	board.game[1][2] = "4";
	board.game[1][3] = "2P2";
	board.game[1][4] = "1";

	board.game[2][0] = "1";
	board.game[2][1] = "1";
	board.game[2][2] = "3";
	board.game[2][3] = "1P2";
	board.game[2][4] = "1";

	board.game[3][0] = "0";
	board.game[3][1] = "0";
	board.game[3][2] = "0";
	board.game[3][3] = "1";
	board.game[3][4] = "0";

	board.game[4][0] = "0";
	board.game[4][1] = "0";
	board.game[4][2] = "0";
	board.game[4][3] = "0";
	board.game[4][4] = "0";
	board.player1.getT1().setLocation(new Location(0,2));
	board.player1.getT2().setLocation(new Location(1,1));
	board.player1.getT1().setLocation(new Location(1,3));
	board.player1.getT1().setLocation(new Location(2,3));
	board.move(board.player1.getT2(), new Location(2,1));
	for (int i = 0; i < board.game.length; i++) {
		for (int j = 0; j < board.game[i].length; j++) {
			System.out.print(board.game[i][j]+"\t "+i+" "+j+" | ");
		}
		System.out.println();
	}
	
	System.out.println();
	board.place(board.player1.getT2(), new Location(2,0));
	for (int i = 0; i < board.game.length; i++) {
		for (int j = 0; j < board.game[i].length; j++) {
			System.out.print(board.game[i][j]+"\t "+i+" "+j+" | ");
		}
		System.out.println();
	}
	
}
}
