import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class TestConsole {

    public static void Start() {
        ArrayList<String> easyFiles = getFilenames("easy");
        ArrayList<String> hardFiles = getFilenames("hard");
        ArrayList<String> mediumFiles = getFilenames("medium");

        NodeMap map = loadMap("medium", mediumFiles);

        Console c = new Console(map, easyFiles, mediumFiles, hardFiles);

    }

    public static ArrayList<String> getFilenames(String difficulty){
        File folder = new File("src/maps/"+difficulty);
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> filenames = new ArrayList<String>();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                filenames.add(file.getName());
            }
        }
        return filenames;
    }

    public static NodeMap loadMap(String difficulty, ArrayList<String> filenames){
        File directory=new File("src/maps/"+difficulty);
        int fileCount=directory.list().length;

        Random rand = new Random();

        if(fileCount > 1){
            int randIndex = rand.nextInt(fileCount);
            String filename = filenames.get(randIndex);
            filenames.remove(randIndex);
            return new NodeMap("src/maps/"+difficulty+"/"+filename);
        } else if(fileCount == 1){
            String filename = filenames.get(0);
            filenames.remove(0);
            return new NodeMap("src/maps/"+difficulty+"/"+filename);
        } else{
            System.out.println("No maps in src/maps"+difficulty+" directory.");
            return null;
        }
    }

    public static void main(String[] args) {
        Start();
        /*
        try {
            Start();
        }
        catch(Exception FileNotFoundException){
            System.out.println("Maps could not be found.");
        }
         */
    }


}
