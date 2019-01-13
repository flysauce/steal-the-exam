public class Room{
	
	char[][] room;
	int size = 15;
	Player[] playerList = new Player[5];
	Thing[] thingList = new Thing[500];
	Exit[] exitList = new Exit[5];
	int players = 0;
	int things = 0;
	int exits = 0;
	boolean currentRoom = false;
	
	public Room(){
		this.room = new char[this.size][this.size];
		for(int row = 0; row < this.size; row++){
			for(int col = 0; col < this.size; col++){
				if(row == 0 || row == (this.size - 1)){
					this.room[row][col] = '-';
				}
				else if(col == 0 || col == (this.size - 1)){
					this.room[row][col] = '|';
				}
				else{
					this.room[row][col] = ' ';
				}
			}
		}
	}
	
	public boolean getCurrentRoom(){
		return this.currentRoom;
	}
	
	public void addPlayer(Player player){
		this.playerList[this.players] = player;
		this.players += 1;
		if(player instanceof User){
			this.currentRoom = true;
		}
	}
	
	public void removePlayer(Player player){
		this.players -= 1;
		this.playerList[this.players] = null;
		if(player instanceof User){
			this.currentRoom = false;
		}
	}
	
	public int getPlayers(){
		return this.players;
	}
	
	public void addThing(Thing thing){
		this.thingList[this.things] = thing;
		this.things += 1;
	}
	
	public Thing getThing(char symbol){
		for(int index = 0; index < this.things; index++){
			if(this.thingList[index].getSymbol() == symbol){
				return this.thingList[index];
			}
		}
		return null;
	}
	
	public void addExit(Exit exit){
		this.exitList[this.exits] = exit;
		this.exits += 1;
	}
	
	public Exit getExit(int row, int col){
		for(int index = 0; index < this.exits; index++){
			if(this.exitList[index].getRow() == row && this.exitList[index].getCol() == col){
				return this.exitList[index];
			}
		}
		return null;
	}
	
	public void removeThing(Thing thing){
		Thing[] tempList = new Thing[5];
		int count = 0;
		for(int index = 0; index < this.things; index++){
			if(this.thingList[index] == thing){}
			else{
				tempList[count] = this.thingList[index];
				count += 1;
			}
		}
		for(int index = 0; index < this.things; index++){
			this.thingList[index] = tempList[index];
		}
		this.things -= 1;
	}
	
	public char getSymbol(int[] location){
		int row = location[0];
		int col = location[1];
		char tile = this.room[row][col];
		return tile;
	}
	
	@Override
	public String toString(){
		for(int row = 0; row < this.size; row++){
			for(int col = 0; col < this.size; col++){
				if(row == 0 || row == (this.size - 1)){
					this.room[row][col] = '-';
				}
				else if(col == 0 || col == (this.size - 1)){
					this.room[row][col] = '|';
				}
				else{
					this.room[row][col] = ' ';
				}
			}
		}
		for(int index = 0; index < this.things; index++){
			int row = this.thingList[index].getRow();
			int col = this.thingList[index].getCol();
			this.room[row][col] = this.thingList[index].getSymbol();
		}
		for(int index = 0; index < this.exits; index++){
			int row = this.exitList[index].getRow();
			int col = this.exitList[index].getCol();
			this.room[row][col] = this.exitList[index].getSymbol();
		}
		for(int index = 0; index < this.players; index++){
			int row = this.playerList[index].getRow();
			int col = this.playerList[index].getCol();
			this.room[row][col] = this.playerList[index].getSymbol();
		}
		System.out.println();
		for(int row = 0; row < this.room.length; row++){
			for(int col = 0; col < this.room.length; col++){
				System.out.print(" " + this.room[row][col]);
			}
			System.out.println();
		}
		return "";
	}
}