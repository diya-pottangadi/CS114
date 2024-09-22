import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Maze Project
//September 26, 2024
public class Main {
    
    private static int rows, columns;
    private static char[][] maze;
    private static boolean solved;

    public static void main(String[] args)  {
        fileRead();

        if(solved==true){
            System.out.println("The Maze has been solved!");
        }
    }

    //method to read the file
    public static void fileRead(){

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
        
            //finding starting position, calls mazeSolve method
            for(int r=0; r<rows; r++){
                for(int c=0; c<columns; c++){
                    if(maze[r][c] =='+'){
                        mazeSolve(r,c); 
                    }
                }
            }  
        } catch(FileNotFoundException e){
            System.out.println("An error occured");
        }
    }
    
    //recursion
    public static void mazeSolve(int x, int y){
        /*
          if (maze[r][c] == "-"){
            solved == true;
        }
         */
        //condition to go up, down, left, right
        //condition to check if ended
        //condition to mark visited
        //condition to check dead end
       
    }

    

    


}


