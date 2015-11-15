package eg.edu.guc.santorini.tiles;

import java.util.ArrayList;

import eg.edu.guc.santorini.utilities.Location;

public abstract class Piece implements PieceInterface {

	private Location loc;
	private int moves;
	
	public Piece() {
		loc=null;
		setMoves(0);
	}
	
	public Piece(int y , int x) {
		loc=new Location(y, x);
		setMoves(0);
	}

	public ArrayList<Location> possiblePlacements() {
		ArrayList<Location> temp = new ArrayList<Location>();
		int y = loc.getY();
		int x = loc.getX();
		temp.add(new Location(y-1 , x-1));
		temp.add(new Location(y-1 , x  ));
		temp.add(new Location(y-1 , x+1));
		temp.add(new Location(y   , x-1));
		temp.add(new Location(y   , x+1));
		temp.add(new Location(y+1 , x-1));
		temp.add(new Location(y+1 , x  ));
		temp.add(new Location(y+1 , x+1));
		return removeit(temp);
	}

	public Location getLocation() {
		return loc;
	}

	public void setLocation(Location loc) {
		this.loc = loc;
	}
	
	static ArrayList<Location> removeit(ArrayList<Location> input) {
		ArrayList<Location> temp = new ArrayList<Location>();
		for (int i = 0; i < input.size(); i++) {
			Location current = input.get(i);
			if (current.getX() < 5 && current.getX() > -1 && current.getY() < 5 && current.getY() > -1) {
				temp.add(current);
			}
		}
		return temp;
	}

	public int getMoves() {
		return moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}

}
