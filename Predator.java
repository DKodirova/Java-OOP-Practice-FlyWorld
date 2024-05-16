import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

//public class Predator {
abstract class Predator {
    int moves = 0;


    protected GridLocation location;
    protected FlyWorld world;
    protected BufferedImage image;

    public Predator(GridLocation loc, FlyWorld fw, String imgFile)
    {
        this.location = loc;
        this.world = fw;

        try
        {
            image = ImageIO.read(new File(imgFile));
        }
        catch (IOException ioe)
        {
            System.out.println("Unable to read image file: " + imgFile);
            System.exit(0);
        }
        location.setPredator(this);
    }

    public BufferedImage getImage()
    {
        return image;
    }

    public GridLocation getLocation()
    {
    return location;
    }

    public boolean isPredator()
    {
    return true;
    }

    /**
    * Returns a string representation of this Frog showing
    * the location coordinates and the world.
    *
    * @return the string representation
    */


    public abstract GridLocation[] generateLegalMoves();

    public void update()
    {
        int r = location.getRow();
        int c = location.getCol();
        
        GridLocation[] legalLocations = generateLegalMoves();

        GridLocation rand = null;
        Random randLocation = new Random();

        if(moves > 0){
            int idx = randLocation.nextInt(moves);
            rand = legalLocations[idx];

            int r_new = rand.getRow();
            int c_new = rand.getCol();
            
            world.getLocation(r, c).removePredator();
            world.getLocation(r_new,c_new).setPredator(this);
            location = world.getLocation(r_new,c_new);
        }else{
            throw new NullPointerException();

        }
    }

    public abstract boolean eatsFly();
 

    
}
