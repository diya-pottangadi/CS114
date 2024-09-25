import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Maze Project
//Due September 26, 2024
public class Main {
    
    private static int rows, columns;
    private static char[][] maze;
    private static boolean solved;

    public static void main(String[] args)  {
        readFile();
        if(solved==false){
            System.out.println("The maze has no solution");  
        }
    }

    //method to read the file and call starting position
    public static void readFile(){

        File mazeFile = new File("/Users/diyapottangadi/Desktop/mazeFile.txt");
        try (Scanner scan = new Scanner(mazeFile)) 
        {
            //setting up 2d array for maze
            rows = scan.nextInt();
            columns = scan.nextInt();
            maze = new char[rows][columns];
            
            //inputting contents of maze file into array 
            for (int i = 0; i < rows; i++) {
                maze[i] = scan.nextLine().toCharArray();
            }
        
            //finding starting position, calls solveMaze method
            for(int r=0; r<rows; r++){
                for(int c=0; c<columns; c++){
                    if(maze[r][c] == '+'){
                        solveMaze(r,c); 
                    }
                }
            }  
        } catch(FileNotFoundException e){
            System.out.println("An error occured");
        }
    }
    
    //recursion
    public static void solveMaze(int r, int c){
        
        //checking bounds
        if (r < 0 || c < 0 || r >= maze.length || c >= maze[0].length) {
            return;        
        }

        //checking if the maze is solved
        if (maze[r][c] == '-'){
            solved=true;
        }
        
        //checking if visited or wall
        if(maze[r][c] == '#' || maze[r][c] == '+'){
            return;
        }

        //marking maze
        maze[r][c] = '+'; 

        //recursive calls
        solveMaze(r-1,c); 
        solveMaze(r+1,c);
        solveMaze(r,c-1);
        solveMaze(r, c+1);
        
        if(!solved){
            maze[r][c] = '.';
        }

        if(solved){
            System.out.println("The maze was solved!");
            printMaze();
        }
    
    }

    public static void printMaze(){
        for(int r=0; r<rows; r++)
        {
            for(int c=0; c<columns; c++)
            {
                System.out.print(maze[r][c]);
            }
        }
    }

}


