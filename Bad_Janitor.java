import java.util.Random;

public class Bad_Janitor extends Player{
  
  // Drops garbage which creates a barrier the player cannot pass.
  // Moves about a room randomly, dropping garbage where he just was.
  
  Room[][] world;
  
  public Bad_Janitor(int row, int col, Room[][] world){
    this.symbol = 'J';
    this.row = row;
    this.col = col; 
    this.world = world;
  }
  
  public void move(){    
    
    // Get current location
    int x_current = this.getCol();
    //System.out.println(this.getCol());
    
    int y_current = this.getRow();
    //System.out.println(this.getRow());
    
    // Drop garbage at previous location
    this.drop_garbage( this.getRow(), this.getCol() );
    
    // Generate 2 random numbers between 0 and 1
    Random rand = new Random();
    int x_dist = rand.nextInt(2)+1;
    int y_dist = rand.nextInt(2)+1;
    
    // The 2 random numbers below will determine the direction
    // Bad_Janitor moves
    int x_dir = rand.nextInt(2);
    int y_dir = rand.nextInt(2);

    // Determine movement of Janitor based on previous RNG's
    int x_move = x_current;
    int y_move = y_current;
    
    if( x_dir == 0 ){ x_move += x_dist; }
    else{ x_move -= x_dist; }
    
    if( y_dir == 0 ){ y_move += y_dist; }
    else{ y_move -= y_dist; }
    
    // Move Janitor based on x_move and y_move
    // Movements are bounded by the size of the room
    
    if( this.getCol() >= 3 && this.getCol() <= 12 ){
      this.setCol(x_move);
    }else if( this.getCol() > 12 ){
      this.setCol( x_current - 2 );
    }else if(this.getCol() < 3 ){
      this.setCol( x_current + 2 );}
    
    if( this.getRow() >= 3 && this.getRow() <= 12 ){
      this.setRow(y_move);
    }else if( this.getRow() > 12 ){
      this.setRow( y_current - 2);
    }else if( this.getRow() < 3 ){
      this.setRow( y_current + 2 );}
    
  }
  
  // Janitor constructs a new garbage
  private void drop_garbage(int x , int y){
    Garbage dropped = new Garbage(x,y);
    Room[][] map = this.world;
    try{ map[0][2].addThing(dropped); }
    catch(ArrayIndexOutOfBoundsException e){
      map[0][1].addThing(dropped);
    }
  }
   
}