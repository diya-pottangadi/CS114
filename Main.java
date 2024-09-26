import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Maze Project
//Due September 26, 2024

public class Main {
    
    private static int rows, columns;
    private static char[][] maze;
    private static boolean solved = false;

    public static void main(String[] args)  {
        readFile();
        
        if(solved==false){
            System.out.println("The maze has no solution");  
        }
    }

    //method to read the file and call starting position
    public static void readFile(){

        File mazeFile = new File("maze.dat");
        
        try (Scanner scan = new Scanner(mazeFile)) 
        {
            //setting up 2d array for maze
            rows = scan.nextInt();
            columns = scan.nextInt();
            maze = new char[rows][columns];
            scan.nextLine();
            
            //inputting contents of maze file into array 
            for (int i = 0; i < rows; i++) {
                maze[i] = scan.nextLine().toCharArray();
            }
        
            //finding starting position, calls solveMaze method
            for(int r=0; r<rows; r++){
                for(int c=0; c<columns; c++){
                    if(maze[r][c] == '+'){
                        solveMaze(r,c); 
                        return;
                    }
                }
            }  
        } catch(FileNotFoundException e){
            System.out.println("An error occured");
        }
    }
    
    //recursion
    public static void solveMaze(int r, int c){
        
        /* //troubleshooting code
        System.out.println(r + " " + c);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e)  {
        }
        */

        //checking if the maze is solved
        if (maze[r][c] == '-') {
            solved = true;
            System.out.println("The maze was solved!");
            printMaze();
            return;
        }    

        //marking path
        if (maze[r][c] == ' ') {
            maze[r][c] = '+';
        }     

        //recursive calls & checks
        if(checkBounds(r, c-1))
            solveMaze(r,c-1);
        if(checkBounds(r, c+1))
            solveMaze(r, c+1);
        if(checkBounds(r+1, c))
            solveMaze(r+1,c);
        if(checkBounds(r-1, c))
            solveMaze(r-1,c); 
        
        //backtracking
        if(!solved){
            maze[r][c] = '.';
        }
    }
    
    public static boolean checkBounds(int r, int c){

        //checking bounds
        if(r < 0 || c < 0 || r >= maze.length || c >= maze[0].length)
            return false;

        //checking if visited or wall
        else return !(maze[r][c] == 'X' || maze[r][c] == '.' || maze[r][c] == '+');

    }

    public static void printMaze(){
        for(int r=0; r<rows; r++)
        {
            for(int c=0; c<columns; c++){
                System.out.print(maze[r][c]);
            }
            System.out.println();
        }
    }
}