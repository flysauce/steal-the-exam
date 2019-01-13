import java.util.Random;

public class TAs extends Player{

	public int Mark = 75;
	public int turn = 0;

	public TAs(){
		this.row = 1;
		this.col = 1;
		this.symbol = 'R';
	}

	public void Attack(User player){
		if(player.col == this.col & player.getRow() == this.row & player.haveExam() == true){
			Mark = Mark - 20;
			System.out.println("The TA caught you with the exam! Your mark decreased by 20%!");
		}
	}
	
	public boolean dropHint(){
		if(turn % 5 == 0){
			Hint H = new Hint(this.col, this.row);
			return true;
		}else{
			return false;
		}
	}

	public void Homing(Player player){
		if(this.col < player.col){
			this.setCol(col + 1);
		}
		if(this.col > player.col){
			this.setCol(col - 1);
		}
		if(this.row < player.row){
			this.setRow(row + 1);
		}
		if(this.row > player.row){
			this.setRow(row - 1);
		}
	}

	public void pickupHint(Player player){
		if(getSymbol() == 'H'){
			if(Mark <= 95){
				Mark = Mark + 5;
				System.out.println("You found some answers that the TA dropped! Your mark increased by 5%!");
			}else{
				System.out.println("Your mark is already 100%! Great job!");
			}
		}
	}

	public void removeTA(Player player){
		if(this.row == player.row && this.col == player.col){
			this.symbol = '@';
		}
	}

}