public abstract class Roomable{

	char symbol;
	int row;
	int col;
	
	public char getSymbol(){
		return this.symbol;
	}
	
	public int getRow(){
		return this.row;
	}
	
	public int getCol(){
		return this.col;
	}
	
	public void setRow(int row){
		this.row = row;
	}
	
	public void setCol(int col){
		this.col = col;
	}
}