package game;

import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.SwingUtilities;

public class Maps {
	static final int EMPTY=0;
	static final int FULL =1;
	static final int SPAWNROOM=2;
	static final int VISITED=3;
	static final int size = 7;
	
	
	int roomCount=0;
	
	int [][] mapp = new int [size][size];
	
	int randomRoomsNums = (int)(Math.random() * 5) + 10;
	Queue <Point> generated = new LinkedList <Point>();
	Queue <Point> visited = new LinkedList <Point>();
	Point p;
	Point v;

	Maps(){
		//set the spawn room at the center of the map
		mapp[size/2][size/2]=SPAWNROOM;
		//store the coordinate as a point and add it to a queue
		Point center = new Point(size/2,size/2);
		generated.add(center);
		
		check();
//		printMap();
	}

	void check() {
		/*
		 * All the points in the queue are the rooms that are generated (1's)
		 * As long there are points in the queue, retrieve the first point and remove it from the queue
		 * Use the point and randomly generate rooms on four sides of the point
		 */
		
		while(randomRoomsNums > roomCount) {
			if(generated.size() > 0) {
				p = generated.poll();
				
				if(makeRandomRoom()) {
					continue;
				}
				
			}else if(generated.size() <= 0 && visited.size() > 0){
				v = visited.poll();
				mapp[v.x][v.y]=1;
				roomCount ++;
				generated.add(v);
			}else {
				break;
			}

		}
	}
	
	/*generate rooms around coordinates
	 * If condition matches, randomly decide to generate a room or not,
	 * change the coordinates to 1 if a room is generated, if not, change to 3,
	 * add the coordinates into the queue if it's 1
	 * 1 = room made
	 * 2 = spawn room
	 * 3 = visited
	 * 0 = empty
	 */
	
	boolean makeRandomRoom() {
		//generate room around 1's (room existed) randomly	
		//Left side of the point
		if(p. x - 1 >= 0 && mapp[p.x -1][p.y] == EMPTY) {
			if (Math.random() < 0.5) {
				mapp[p.x-1][p.y]=FULL;
				roomCount ++;
				generated.add(new Point(p.x-1,p.y));//add the coordinates to the generated queue

			} else {
				mapp[p.x-1][p.y]=VISITED; // coordinate checked, do not make a room here
				visited.add(new Point(p.x-1, p.y));//add all the visited points to the visited queue
			}
		}
		
		//Right side of the point
		if(p.x + 1 < size && mapp[p.x + 1][p.y] == EMPTY) {
			if (Math.random() < 0.5) {
				mapp[p.x+1][p.y]=FULL;
				roomCount ++;
				generated.add(new Point(p.x+1,p.y));
				
			}else {
				mapp[p.x+1][p.y]=VISITED;
				visited.add(new Point(p.x+1, p.y));
			}
		}

		//Up side of the point
		if(p.y - 1 >= 0 && mapp[p.x][p.y - 1] == EMPTY) {
			if(Math.random() < 0.5) {
				mapp[p.x][p.y-1]= FULL;
				roomCount ++;
				generated.add(new Point (p.x, p.y-1));
			}else {
				mapp[p.x][p.y-1]=VISITED;
				visited.add(new Point(p.x, p.y-1));
			}
		}

		//Down side of the point
		if(p.y + 1 < size && mapp[p.x][p.y + 1] == EMPTY) {
			if(Math.random() < 0.5) {
				mapp[p.x][p.y+1]= FULL;
				roomCount ++;
				generated.add(new Point (p.x, p.y+1));
			}else {
				mapp[p.x][p.y+1]=VISITED;
				visited.add(new Point(p.x, p.y+1));
			}
		}

		//After all the rooms are generated, check them all at once 
		//If equals to 1, return true
		//else return other situations false
		if(mapp[p.x][p.y]==FULL) {
			return true;

		}else if(mapp[p.x][p.y]==VISITED) {
			return false;
		}
		return false;
	}
//	
//	void printMap() {
////		for (int row = 0; row < size; row++) {
////			for (int col = 0; col < size; col++) {
////				if(mapp[row][col]==VISITED) {
////					mapp[row][col]=EMPTY;
////				}
////			}
////			System.out.println();
////
////		}
////		System.out.println("Roomcount = "+roomCount);
////		System.out.println();
//		for (int row = 0; row < size; row++) {
//			System.out.println(Arrays.toString(mapp[row]));
//		}
//		System.out.println();
//	}
	
	Room[][] convertMap() {
		Room[][] map=new Room[size][size];
		
		int count=0;
		boolean ladderSpawned=false;
		
		for (int i=0;i<size;i++) {
			for (int j=0;j<size;j++) {
				if (mapp[i][j]==1) {
					count++;
					map[i][j]=new Room();
					if(!ladderSpawned && (int)(Math.random()*randomRoomsNums+1)==1) {
						map[i][j].ladder=true;
						ladderSpawned=true;
						System.out.println("ladder room at x="+(i+1)+", y="+(j+1)+" *source code in Maps.java\n");
					}
					if(!ladderSpawned && count==randomRoomsNums) {
						map[i][j].ladder=true;
						System.out.println("ladder room at x="+(i+1)+", y="+(j+1)+" *source code in Maps.java\n");
					}
					
				}
				if (mapp[i][j]==2) { //set default spawn room at center
//					count++;
					map[i][j]=new Room();
					map[i][j].explored=true;
					map[i][j].currentRoom=true;
				}
				
			}
			
		}
		return map;
	}

}


