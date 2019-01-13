public class World{

	int rows;
	int cols;
	Room[][] world;
	
	public World(){
		this.rows = 2;
		this.cols = 3;
		world = new Room[this.rows][this.cols];
		for(int row = 0; row < this.rows; row++){
			for(int col = 0; col < this.cols; col++){
				world[row][col] = new Room();
			}
		}
	}
	
	public World(int integer){
		this.rows = 1;
		this.cols = 2;
		world = new Room[this.rows][this.cols];
		for(int row = 0; row < this.rows; row++){
			for(int col = 0; col < this.cols; col++){
				world[row][col] = new Room();
			}
		}
	}
	
	public Room[][] getWorld(){
		return this.world;
	}
	
	public Room getCurrentRoom(){
		for(int row = 0; row < this.rows; row++){
			for(int col = 0; col < this.cols; col++){
				if(world[row][col].getCurrentRoom() == true){
					return world[row][col];
				}
			}
		}
		return null;
	}
}