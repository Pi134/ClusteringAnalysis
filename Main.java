import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
This program reads the protein files from the experiment (1 for each cell).
For each cell, the program calculates for each protein the distance to the closest protein in the cell.
Afterwards, the program creates a file in which it writes for each distance (in nm) between 1 and 4999.5 nm (in jumps of 0.5 nm), how many proteins have their closest protein closer than the given distance.
In addition, the program creates a set of randomly generated protein files and runs the same analysis on them, for a control group.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        int[] histogram = new int[10000];

        // This loop runs on all protein files - 1 for each cell
        for(File pixelPlacements : Util.findFilesInDirectory()) {
            System.out.println(pixelPlacements.getCanonicalPath());

            // Creates a new ArrayList of proteins and puts the data from the files in it
            ArrayList<Protein> proteins = new ArrayList<>();

            FileReader filereader = new FileReader(pixelPlacements);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            csvReader.readNext(); // Read titles

            while ((nextRecord = csvReader.readNext()) != null) {
                Protein protein = new Protein();
                protein.x = Double.parseDouble(nextRecord[1]);
                protein.y = Double.parseDouble(nextRecord[2]);
                proteins.add(protein);
            }
            System.out.println(proteins.size());

            // For each protein in the ArrayList, finds the protein in the cell closest to it
            for (Protein p1 : proteins) {
                double minDistance = Double.MAX_VALUE;
                for (Protein p2 : proteins) {
                    if (p1.distanceFrom(p2) < minDistance && p1 != p2)
                        minDistance = p1.distanceFrom(p2);
                }
                p1.minDistance = minDistance;
            }

            // Adds the minimum distance to the closest protein info about these proteins of this cell to the overall histogram
            for (int i = 0; i < 10000; i++) {
                for (Protein p : proteins) {
                    if (p.minDistance <= (i / 2.0))
                        histogram[i]++;
                }
            }
        }

        for (int i=0; i<histogram.length; ++i) {
            System.out.println(i/2.0 + " " + histogram[i]);
        }
    }

}