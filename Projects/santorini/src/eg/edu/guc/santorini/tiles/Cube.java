package eg.edu.guc.santorini.tiles;

import java.util.ArrayList;

import eg.edu.guc.santorini.utilities.Location;

public class Cube extends Piece {

	public Cube() {
		super();
	}
	
	public Cube(int y, int x) {
		super(y, x);
	}

	public ArrayList<Location> possibleMoves() {
		ArrayList<Location> temp = new ArrayList<Location>();
		int y = getLocation().getY();
		int x = getLocation().getX();
		temp.add(new Location(y-1 , x));
		temp.add(new Location(y , x-1));
		temp.add(new Location(y , x+1));
		temp.add(new Location(y+1 , x));
		return removeit(temp);
	}

}
