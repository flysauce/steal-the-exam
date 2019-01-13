import java.util.Scanner;

public class DemoKevin{

 public static void turn(String input, User player, Room room){
  int[] nextLocation = new int[2];
  nextLocation[0] = player.getRow();
  nextLocation[1] = player.getCol();
  if(input.equals("exit")){
   System.exit(0);
  }
  else if(input.equals("inv")){
   player.printInv();
  }
  else if(input.equals("how")){
   System.out.println("This is a demo of Kevin's player and thing:\n" +
   "Player - Bad Janitor (Bad_Janitor class) \n" +
   "Thing - Garbage\n" +
   "Garbage is dropped by the Bad Janitor each time he moves\n" +
   "Garbage is impassable by the User, but the Janitor can 'burrow' through.");
   
  }
  else if(input.equals("help") || input.equals("h")){
   System.out.println("w - move up\n" +
   "a - move left\n" +
   "s - move down\n" +
   "d - move right\n" +
   "inv - check inventory\n" +
   "how - how to win\n" +
   "exit - exit program");
  }
  else if(input.equals("w")){
   nextLocation[0] -= 1;
   move(nextLocation, player, room);
  }
  else if(input.equals("a")){
   nextLocation[1] -= 1;
   move(nextLocation, player, room);
  }
  else if(input.equals("s")){
   nextLocation[0] += 1;
   move(nextLocation, player, room);
  }
  else if(input.equals("d")){
   nextLocation[1] += 1;
   move(nextLocation, player, room);
  }
  else{
   System.out.println("Invalid move.");
  }
 }
 
 public static void move(int[] nextLocation, User player, Room room){
  int row = nextLocation[0];
  int col = nextLocation[1];
  if(room.getSymbol(nextLocation) == ' '){
   player.setRow(row);
   player.setCol(col);
  }
  else if(room.getSymbol(nextLocation) == 'D'){
   Exit tempExit = room.getExit(row, col);
   room.removePlayer(player);
   Room nextRoom = tempExit.getNextRoom();
   nextRoom.addPlayer(player);
   player.setRow(tempExit.getNextRow());
   player.setCol(tempExit.getNextCol());
  }
  else{}
 }
 
 // Demo main
 public static void main(String[] args){
  
  //creating world
  World world = new World(1);
  Room[][] map = world.getWorld();

  //creating and adding players
  User player = new User();
  map[0][0].addPlayer(player);
  
  Bad_Janitor Scruffy = new Bad_Janitor(4,4,world.getWorld());
  Room Scruffys_room = map[0][1];
  Scruffys_room.addPlayer( Scruffy );
  
  //creating and adding exits
  Exit door0 = new Exit(7, 14, map[0][1], 7, 1);
  map[0][0].addExit(door0);
  Exit door1 = new Exit(7, 0, map[0][0], 7, 13);
  map[0][1].addExit(door1);
  
  //game loop
  while(true){
   Room currentRoom = world.getCurrentRoom();
   String input;
   Scanner keyboard = new Scanner(System.in);
   System.out.println(currentRoom);
   System.out.println("'w', 'a', 's', 'd', 'inv', 'how', 'help'(or 'h') or 'exit' --> *enter*");
   input = keyboard.nextLine();
   turn(input, player, currentRoom);
   if(currentRoom == Scruffys_room){
    Scruffy.move();
   }
  }
 }
}