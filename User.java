public class User extends Player{

	Thing[] inv = new Thing[1];
	
	public User(){
		this.symbol = '@';
		this.row = 7;
		this.col = 7;
	}
	
	public void pickUp(Thing thing){
			this.inv[0] = thing;
	}
	
	public void printInv(){
		System.out.println("Inventory:");
		if(this.inv[0] instanceof Exam){
			System.out.println("Exam");
		}
		else{
			System.out.println("Empty");
		}
	}
	
	public boolean haveExam(){
		if(this.inv[0] != null){
			return true;
		}
		else{
			return false;
		}
	}
}