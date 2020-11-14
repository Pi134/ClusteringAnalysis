import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;
public class Util {

    //This function returns the protein files from the experiment
    public static List<File> findFilesInDirectory() throws IOException {
        File dir = new File("C:\\Users\\aviga\\Documents\\Avigail_108T_Ras_grb2");
        String[] extensions = new String[] {"csv"};
        List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
        return files;
    }

}
