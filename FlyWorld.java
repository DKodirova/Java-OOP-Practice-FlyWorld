import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.Color;


/**
 * Contains information about the world (i.e., the grid of squares)<br>
 * and handles most of the game play work that is NOT GUI specific
 */
public class FlyWorld
{

    protected int numRows;
    protected int numCols;

    protected GridLocation [][] world;

    protected GridLocation start;
    protected GridLocation goal;

    protected Predator[] predator;
    protected int predatorIdx;

    protected Fly mosca;

    /**
     * Reads a file containing information about<br>
     * the grid setup.  Initializes the grid<br>
     * and other instance variables for use by<br>
     * FlyWorldGUI and other pieces of code.
     *
     *@param fileName the file containing the world grid information
     */
    public FlyWorld(String fileName){
        try(Scanner scanner = new Scanner(new File(fileName))){
            String input_0 = scanner.nextLine();
            String[] input_arr = input_0.split(" ");
            numRows = Integer.parseInt(input_arr[0]);
            numCols = Integer.parseInt(input_arr[1]);
            int row=0;
            world = new GridLocation[numRows][numCols];
            predator = new Predator[numRows*numCols];
            predatorIdx = 0;

            while(scanner.hasNextLine()){
                char[] input1 = scanner.nextLine().toCharArray();
                
                for(int col=0; col<input1.length; col++){
                    if(input1[col]=='s'){
                        start = new GridLocation(row,col);
                        world[row][col] = start;
                        mosca = new Fly(start, this);
                        start.setBackgroundColor(Color.GREEN);
                    }else if(input1[col]=='h'){
                        goal = new GridLocation(row,col);
                        world[row][col] = goal;
                        goal.setBackgroundColor(Color.RED);
                    }else if(input1[col]=='.'){
                        world[row][col] = new GridLocation(row,col);
                    }else if(input1[col]=='f'){
                        GridLocation frogLocation = new GridLocation(row, col);
                        predator[predatorIdx] = new Frog(frogLocation, this);
                        world[row][col] = frogLocation;
                        predatorIdx++;
                    }else if(input1[col]=='a'){
                        GridLocation spiderLocation = new GridLocation(row, col);
                        predator[predatorIdx] = new Spider(spiderLocation, this);
                        world[row][col] = spiderLocation;
                        predatorIdx++;
                    }

                }
                row++;
            }            
        }catch(FileNotFoundException fnfe){
            System.err.println("File does not exist");
            System.exit(1);
        }


        // The following print statements are just here to help you know 
        // if you've done part 1 correctly.  You can comment them out or 
        // delete them before you make your final submission
        //System.out.println("numRows: " + this.numRows + "   numCols: " + this.numCols);
        //System.out.println("start: " + this.start + "   goal: " + this.goal);
        // System.out.println("Mosca: " + this.mosca.toString());
        //System.out.println(mosca);
    }

    /**
     * @return int, the number of rows in the world
     */
    public int getNumRows(){
        return numRows;
    }

    /**
     * @return int, the number of columns in the world
     */
    public int getNumCols(){
        return numCols;
    }

    /**
     * Deterimes if a specific row/column location is<br>
     * a valid location in the world (i.e., it is not out of bounds)
     *
     * @param r a row
     * @param c a column
     *
     * @return boolean
     */
    public boolean isValidLoc(int r, int c){
        return !(r < 0 || r >= numRows || c < 0 || c >= numCols);
    }

    /**
     * Returns a specific location based on the given row and column
     *
     * @param r the row
     * @param c the column
     *
     * @return GridLocation
     */
    public GridLocation getLocation(int r, int c){
        return world[r][c];
    }

    /**
     * @return FlyWorldLocation, the location of the fly in the world
     */
    public GridLocation getFlyLocation(){
        return mosca.getLocation();
    }

    /**
     * Moves the fly in the given direction (if possible)
     * Checks if the fly got home or was eaten
     *
     * @param direction the direction, N,S,E,W to move
     *
     * @return int, determines the outcome of moving fly<br>
     *              there are three possibilities<br>
     *              1. fly is at home, return ATHOME (defined in FlyWorldGUI)<br>
     *              2. fly is eaten, return EATEN (defined in FlyWorldGUI)<br>
     *              3. fly not at home or eaten, return NOACTION (defined in FlyWorldGUI)
     */
    public int moveFly(int direction){
        mosca.update(direction);
        GridLocation loc = getFlyLocation();
        if(loc.equals(goal)){
            return FlyWorldGUI.ATHOME;
        }

        for(int i=0; i<predatorIdx; i++){
            if(predator[i].eatsFly()){
                return FlyWorldGUI.EATEN;
            }
        }
        return FlyWorldGUI.NOACTION;
    }

    /**
     * Moves all predators. After it moves a predator, checks if it eats fly
     *
     * @return boolean, return true if any predator eats fly, false otherwise
     */
    public boolean movePredators(){
        boolean output = false;
        int d =0;
        while(d<predatorIdx){
            predator[d].update();
            if(predator[d].eatsFly()){
                output = true;
            }
            d++;
        }

        return output;
    }
}
