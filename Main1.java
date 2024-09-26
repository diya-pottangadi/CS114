import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Maze Project
// Due September 26, 2024
public class Main1 {
    
    private static int rows, columns;
    private static char[][] maze;
    private static boolean solved;

    public static void main(String[] args) {
        readFile();
        
        if (!solved) {
            System.out.println("The maze has no solution");
        }
        printMaze();
    }

    // Method to read the file and call starting position
    public static void readFile() {
        File mazeFile = new File("/Users/diyapottangadi/Desktop/mazeFile.txt");
        
        try (Scanner scan = new Scanner(mazeFile)) {
            // Setting up 2D array for maze
            rows = scan.nextInt();
            columns = scan.nextInt();
            maze = new char[rows][columns];
            scan.nextLine(); // Move to the next line
            
            // Inputting contents of maze file into array 
            for (int i = 0; i < rows; i++) {
                String line = scan.nextLine();
                for (int j = 0; j < Math.min(columns, line.length()); j++) {
                    maze[i][j] = line.charAt(j);
                }
            }
    
            // Finding starting position and calling solveMaze method
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < columns; c++) {
                    if (maze[r][c] == '+') {
                        solveMaze(r, c); 
                    }
                }
            }  
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
    
    // Recursion
    public static void solveMaze(int r, int c) {
        // Checking bounds
        if (r < 0 || c < 0 || r >= maze.length || c >= maze[0].length) {
            return;        
        }

        // Checking if the maze is solved
        if (maze[r][c] == '-') {
            solved = true;
            System.out.println("The maze was solved!");
            return;
        }
        
        // Checking if visited or wall
        if (maze[r][c] == 'X' || maze[r][c] == '+' || maze[r][c] == '.') {
            return;
        }

        // Mark the current path
        if (maze[r][c] == ' ') {
            maze[r][c] = '+'; // Marking the path
        }     

        // Recursive calls
        solveMaze(r - 1, c); // Up
        solveMaze(r + 1, c); // Down
        solveMaze(r, c - 1); // Left
        solveMaze(r, c + 1); // Right
        
        // Backtrack if not solved
        if (!solved) {
            maze[r][c] = '.'; // Mark as dead end
        }
    }
    
    public static void printMaze() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                System.out.print(maze[r][c]);
            }
            System.out.println();
        }
    }
}
