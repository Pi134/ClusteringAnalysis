import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
This class creates a set of files that are the same amount, size and layout as the original protein files.
Those files are used as a control group, so we can see if the proteins we got from the experiment are more or less clustered than randomly generated ones.
 */
public class RandomProteinGenerator {
    public static void main(String[] args) throws IOException {

        int i = 0;
        // Go over all protein files and create for each of them a file of the same size which consists of proteins whose coordinates are randomly generated
        for(File pixelPlacements : Util.findFilesInDirectory())
        {
            // Reads the file and checks the file's size
            System.out.println(pixelPlacements.getCanonicalPath());

            FileReader filereader = new FileReader(pixelPlacements);
            CSVReader csvReader = new CSVReader(filereader);

            // The size of the protein file
            int fileSize = (csvReader.readAll().size()) - 1;

            System.out.println(fileSize);

            // Creates an array of randomly placed proteins the size of the current protein file
            ArrayList<Protein> randomProteins = randomArray(fileSize,41000.0,41000.0);

            // Creates a new protein file of the same layout as the files from the experiment
            File file = new File("C:\\Users\\aviga\\Documents\\Avigail_108T_Ras_grb2\\Clustering Analysis\\histogram program significance check\\sparse\\" + String.valueOf(i) + ".csv");
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);

            String[] header = {"", "x[nm]", "y[nm]"};
            writer.writeNext(header);

            // Writes the coordinates of the randomly created proteins in randomProteins into the new file
            for(Protein protein : randomProteins)
            {
                String[] randomProtein = {"", String.valueOf(protein.x), String.valueOf(protein.y)};
                writer.writeNext(randomProtein);
            }
            writer.close();
            i++;
        }
    }

    // Creates an array of proteins, whose locations are randomly generated between 0 and given values
    public static ArrayList<Protein> randomArray(int size, double maxX, double maxY)
    {
        ArrayList<Protein> proteins = new ArrayList<>();
        for(int i = 0; i < size; i++)
        {
            Protein protein = new Protein();
            protein.random(maxX, maxY);
            proteins.add(protein);
        }
        return proteins;
    }

}