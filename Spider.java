


public class Spider extends Predator {

    public Spider(GridLocation loc, FlyWorld fw)
    {
        super(loc, fw, "spider.png");
    }

    public String toString(){
        return "Spider in world:  " + this.world + "  at location (" + this.location.getRow() + ", " + this.location.getCol() + ")";
    }

    @Override
    public  GridLocation[] generateLegalMoves(){
        moves = 0;

        GridLocation spiderLocation = getLocation();
        int spiderR = spiderLocation.getRow();
        int spiderC = spiderLocation.getCol();
        GridLocation[] legalLocations=new GridLocation[2];

        GridLocation flyLocation = world.getFlyLocation();
        int flyR = flyLocation.getRow();
        int flyC = flyLocation.getCol();

        if(flyR<spiderR){
            if(world.isValidLoc(spiderR-1,spiderC) && !world.getLocation(spiderR-1,spiderC).hasPredator()){
                legalLocations[moves] = new GridLocation(spiderR-1,spiderC);
                moves++;
                }
        }if(flyR>spiderR){
            if(world.isValidLoc(spiderR+1,spiderC) && !world.getLocation(spiderR+1,spiderC).hasPredator()){
                legalLocations[moves] = new GridLocation(spiderR+1,spiderC);
                moves++;
                }
            }
        
        if(flyC<spiderC){
            if(world.isValidLoc(spiderR,spiderC-1) && !world.getLocation(spiderR,spiderC-1).hasPredator()){
                legalLocations[moves] = new GridLocation(spiderR,spiderC-1);
                moves++;
                }
        }if (flyC>spiderC){
            if(world.isValidLoc(spiderR,spiderC+1) && !world.getLocation(spiderR,spiderC+1).hasPredator()){
                legalLocations[moves] = new GridLocation(spiderR,spiderC+1);
                moves++;
                } 
            }
        return legalLocations;
    }



    public boolean eatsFly()
    {
        return (world.getFlyLocation().getRow() == getLocation().getRow()) && (world.getFlyLocation().getCol() == getLocation().getCol());
    } 


}
