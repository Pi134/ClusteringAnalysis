import java.util.Random;

/*
This class contains a protein in a cell. The protein is represented by x and y coordinates.
 */
public class Protein {
    // The x and y positions of the protein (in nm)
    public double x;
    public double y;

    // The minimal distance (in nm) between this protein and the closest protein in the same cell
    public double minDistance;

    private static Random random = new Random();

    // Measures the distance (in nm) between this protein and a given protein
    public double distanceFrom(Protein p2)
    {
        return Math.sqrt(Math.pow(this.x-p2.x, 2)+Math.pow(this.y-p2.y, 2));
    }

    // Generates a random location for this protein (used for random testing, see class RandomProteinGenerator for explanation)
    public void random(double maxX, double maxY)
    {
        this.x = random.nextDouble()*maxX;
        this.y = random.nextDouble()*maxY;
    }
}