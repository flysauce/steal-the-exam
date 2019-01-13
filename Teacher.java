import java.util.Random;

public class Teacher extends Player{
	
	public Teacher(){
		this.symbol = 'X';
		this.row = 12;
		this.col = 12;
	}
	
	public void move(User player, Room room){
		int[] playerLocation = new int[]{player.getRow(), player.getCol()};
		int[] nextLocation = new int[]{this.row, this.col};
		if(player.haveExam() == true){
			if(playerLocation[0] != nextLocation[0]){
				if(playerLocation[0] > nextLocation[0]){
					this.row = nextLocation[0] + 1;
				}
				else if(playerLocation[0] < nextLocation[0]){
					this.row = nextLocation[0] - 1;
				}
			}
			else{
				if(playerLocation[1] > nextLocation[1]){
					this.col = nextLocation[1] + 1;
					}
				else if(playerLocation[1] < nextLocation[1]){
					this.col = nextLocation[1] - 1;
				}
			}
			if(player.getRow() == this.row && player.getCol() == this.col){
				System.out.println("\n\nYOU HAVE BEEN CAUGHT RED HANDED!");
				System.out.println("GAME OVER!\n\n");
				System.exit(0);
			}
		}
		else{
			Random rand = new Random();
			int random = rand.nextInt(4);
			if(random == 0){
				nextLocation[0] += 1;
			}
			else if(random == 1){
				nextLocation[0] -= 1;
			}
			else if(random == 2){
				nextLocation[1] += 1;
			}
			else if(random == 3){
				nextLocation[1] -= 1;
			}
			if(room.getSymbol(nextLocation) == ' '){
				this.row = nextLocation[0];
				this.col = nextLocation[1];
			}
		}
	}
}