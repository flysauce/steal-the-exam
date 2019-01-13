import java.util.Scanner;

public class DemoGame{
	
	public static void checkWin(User player, Room room, World world){
		Room[][] map = world.getWorld();
		if(player.haveExam() == true && room == map[0][0]){
			System.out.println("\n\nYOU HAVE SUCCESSFULLY STOLEN THE EXAM!");
			System.out.println("YOU WIN!\n\n");
			System.exit(0);
		}
	}
	
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
			System.out.println("In order to beat the game, you must:\n" +
			"1 - get to the professors office\n" +
			"2 - steal the exam\n" +
			"3 - get back to the starting room\n" +
			"4 - without being caught or getting locked in");
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
		else if(room.getSymbol(nextLocation) == 'E'){
			pickUp(nextLocation, player, room, 'E');
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
	
	//pick up the exam
	public static void pickUp(int[] nextLocation, User player, Room room, char symbol){
		int row = nextLocation[0];
		int col = nextLocation[1];
		Thing tempThing = room.getThing(symbol);
		player.pickUp(tempThing);
		room.removeThing(tempThing);
		player.setRow(row);
		player.setCol(col);
		if(symbol == 'E'){
			System.out.println("\n\nYou found the exam!\nNow get back to the front door!\nWithout getting caught or stuck!\n\n");
		}
	}
	
	//program
	public static void main(String[] args){
		
		int turn = 0;
		
		//welcome screen + quick intro
		System.out.println("\n\nWELCOME TO STEAL THE EXAM!\n\n");
		System.out.println("Type one of the following followed by enter to play:");
		System.out.println("'w', 'a', 's', 'd', 'inv', 'how', 'help'(or 'h') or 'exit' --> *enter*");
		System.out.println("Invalid input counts as a turn.\n\n");
		
		//creating world
		World world = new World();
		Room[][] map = world.getWorld();

		//creating and adding players
		User player = new User();
		map[0][0].addPlayer(player);
		Teacher teacher = new Teacher();
		Room teacherRoom = map[1][1];
		teacherRoom.addPlayer(teacher);
		Bad_Janitor janitor = new Bad_Janitor(7, 7, world.getWorld());
		map[0][2].addPlayer(janitor);
		Room janitorRoom = map[0][2];
		TAs ta = new TAs();
		Room taRoom = map[1][2];
		taRoom.addPlayer(ta);
		
		//creating and adding things
		Exam exam = new Exam();
		map[1][0].addThing(exam);
		
		//creating and adding exits
		Exit door0 = new Exit(7, 14, map[0][1], 7, 1);
		map[0][0].addExit(door0);
		Exit door1 = new Exit(7, 0, map[0][0], 7, 13);
		Exit door2 = new Exit(14, 7, map[1][1], 1, 7);
		Exit door3 = new Exit(7, 14, map[0][2], 7, 1);
		map[0][1].addExit(door1);
		map[0][1].addExit(door2);
		map[0][1].addExit(door3);
		Exit door4 = new Exit(7, 0, map[0][1], 7, 13);
		Exit door5 = new Exit(14, 7, map[1][2], 1, 7);
		map[0][2].addExit(door4);
		map[0][2].addExit(door5);
		Exit door6 = new Exit(7, 14, map[1][1], 7, 1);
		map[1][0].addExit(door6);
		Exit door7 = new Exit(7, 0, map[1][0], 7, 13);
		Exit door8 = new Exit(0, 7, map[0][1], 13, 7);
		Exit door9 = new Exit(7, 14, map[1][2], 7, 1);
		map[1][1].addExit(door7);
		map[1][1].addExit(door8);
		map[1][1].addExit(door9);
		Exit door10 = new Exit(7, 0, map[1][1], 7, 13);
		Exit door11 = new Exit(0, 7, map[0][2], 13, 7);
		map[1][2].addExit(door10);
		map[1][2].addExit(door11);
		
		//game loop
		while(true){
			Room currentRoom = world.getCurrentRoom();
			checkWin(player, currentRoom, world);
			String input;
			Scanner keyboard = new Scanner(System.in);
			System.out.println(currentRoom);
			System.out.println("'w', 'a', 's', 'd', 'inv', 'how', 'help'(or 'h') or 'exit' --> *enter*");
			input = keyboard.nextLine();
			turn(input, player, currentRoom);
			if(currentRoom == teacherRoom){
				teacher.move(player, currentRoom);
			}
			if(currentRoom == janitorRoom){
				janitor.move();
			}
			if(currentRoom == taRoom){
				if(turn % 2 == 0){
					Hint H = new Hint(ta.getCol(), ta.getRow());
					taRoom.addThing(H);
				}
				ta.Attack(player);
				ta.Homing(player);
				ta.pickupHint(player);
				ta.removeTA(player);
				turn++;
			}
		}
	}
}