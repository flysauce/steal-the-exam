public class Exit extends Roomable{
	
	Room nextRoom;
	int nextRow;
	int nextCol;
	
	public Exit(int row, int col, Room nextRoom, int nextRow, int nextCol){
		this.symbol = 'D';
		this.row = row;
		this.col = col;
		this.nextRoom = nextRoom;
		this.nextRow = nextRow;
		this.nextCol = nextCol;
	}
	
	public Room getNextRoom(){
		return this.nextRoom;
	}
	
	public int getNextRow(){
		return this.nextRow;
	}
	
	public int getNextCol(){
		return this.nextCol;
	}
}