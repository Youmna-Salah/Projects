package eg.edu.guc.santorini.tiles;

import java.util.ArrayList;

import eg.edu.guc.santorini.utilities.Location;

public class Pyramid extends Piece {

	public Pyramid() {
		super();
	}
	
	public Pyramid(int y, int x) {
		super(y, x);
	}

	public ArrayList<Location> possibleMoves() {
		ArrayList<Location> temp = new ArrayList<Location>();
		int y = getLocation().getY();
		int x = getLocation().getX();
		temp.add(new Location(y-1 , x-1));
		temp.add(new Location(y-1 , x+1));
		temp.add(new Location(y+1 , x-1));
		temp.add(new Location(y+1 , x+1));
		return removeit(temp);
	}

}
