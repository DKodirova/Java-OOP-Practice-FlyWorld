public class Frog extends Predator 
{
    
    /**
     * Creates a new Frog object.<br>
     * The image file for a frog is frog.jpg<br>
     *
     * @param loc a GridLocation
     * @param fw the FlyWorld the frog is in
     */
    public Frog(GridLocation loc, FlyWorld fw)
    {
        super(loc, fw, "frog.png");
    }

    public String toString(){
        return "Frog in world:  " + this.world + "  at location (" + this.location.getRow() + ", " + this.location.getCol() + ")";
    }



    /**
     * Generates a list of <strong>ALL</strong> possible legal moves<br>
     * for a frog.<br>
     * You should select all possible grid locations from<br>
     * the <strong>world</strong> based on the following restrictions<br>
     * Frogs can move one space in any of the four cardinal directions but<br>
     * 1. Can not move off the grid<br>
     * 2. Can not move onto a square that already has frog on it<br>
     * GridLocation has a method to help you determine if there is a frog<br>
     * on a location or not.<br>
     *
     * @return GridLocation[] a collection of legal grid locations from<br>
     * the <strong>world</strong> that the frog can move to
     */
    @Override
     public GridLocation[] generateLegalMoves(){
        moves = 0;
        GridLocation frogLocation = getLocation();
        int r = frogLocation.getRow();
        int c = frogLocation.getCol();
        GridLocation[] legalLocations=new GridLocation[4];
        
        if(world.isValidLoc(r+1,c) && !world.getLocation(r+1,c).hasPredator()){
            legalLocations[moves] = new GridLocation(r+1,c);
            moves++;
        }if(world.isValidLoc(r-1,c) && !world.getLocation(r-1,c).hasPredator()){
            legalLocations[moves] = new GridLocation(r-1,c);
            moves++;
        }if(world.isValidLoc(r,c+1) && !world.getLocation(r,c+1).hasPredator()){
            legalLocations[moves] = new GridLocation(r,c+1);
            moves++;
        }if(world.isValidLoc(r,c-1) && !world.getLocation(r,c-1).hasPredator()){
            legalLocations[moves] = new GridLocation(r,c-1);
            moves++;
        }
        return legalLocations;
    }

    /**
     * This method updates the frog's position.<br>
     * It should randomly select one of the legal locations(if there any)<br>
     * and set the frog's location to the chosen updated location.
     */

    /**
     * This method helps determine if a frog is in a location<br>
     * where it can eat a fly or not. A frog can eat the fly if it<br>
     * is on the same square as the fly or 1 spaces away in<br>
     * one of the cardinal directions
     *
     * @return boolean true if the fly can be eaten, false otherwise
     */ 
    public boolean eatsFly()
    {
        GridLocation flyLocation = world.getFlyLocation();
        int flyR = flyLocation.getRow();
        int flyC = flyLocation.getCol();

        GridLocation frogLocation = getLocation();
        int frogR = frogLocation.getRow();
        int frogC = frogLocation.getCol();

        boolean condition1 = (flyR == frogR) && (flyC == frogC);
        boolean condition2 = (flyR == frogR+1) && (flyC == frogC);
        boolean condition3 = (flyR == frogR-1) && (flyC == frogC);
        boolean condition4 = (flyR == frogR) && (flyC == frogC+1);
        boolean condition5 = (flyR == frogR) && (flyC == frogC-1);
        
        return condition1 || condition2 || condition3 || condition4 ||condition5;

    }   
}
