import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Console {

    Scanner io;
    NodeMap map;
    ArrayList<String> easyFiles;
    ArrayList<String> mediumFiles;
    ArrayList<String> hardFiles;

    public Console(NodeMap map, ArrayList<String> easyFiles, ArrayList<String> mediumFiles, ArrayList<String> hardFiles){

        this.easyFiles = easyFiles;
        this.mediumFiles = mediumFiles;
        this.hardFiles = hardFiles;

        io = new Scanner(System.in);
        this.map = map;

        long startTime =  System.currentTimeMillis();
        boolean isDead = false;

        while (!isDead) {
            isDead = map.currentNode() == null;

            print(map.currentNode().getDescription());
            print(map.currentNode().getQuestion());
            if (map.currentNode().getID() != map.lastNode()){
                if (map.currentNode().getQuestion().equals("-")) {
                    pressEnterToContinue();
                    map.noDecision();
                } else {
                    map.decision(
                            // when implemented in GUI this prompt won't be necessary
                            fromConsoleGetInt("(press 1 for left or 2 for right)")
                    ) ;
                }
            } else if(!isDead){
                // This is where it needs to evaluate whether to go for hard, easy or medium.
                long endTime =  (System.currentTimeMillis() - startTime);
                long elapsedSeconds = endTime / 1000;
                if(elapsedSeconds <= map.getHardTime()){
                    //go to a random hard map
                    //what if run out of hard maps?
                    //perhaps use an array to store already used maps to guarantee no repetition
                    //or have arrays for each difficulty and pop each map off the array once they have been selected.
                    isDead = loadNewMap("hard", hardFiles);
                    startTime = System.currentTimeMillis();
                } else if(elapsedSeconds <= map.getMediumTime()){
                    //go to a random medium map
                    isDead = loadNewMap("medium", mediumFiles);
                    startTime = System.currentTimeMillis();
                }else{
                    //go to a random easy map
                    isDead = loadNewMap("easy", easyFiles);
                    startTime = System.currentTimeMillis();
                }
            }
            // Outside of mutual exclusion
            if(System.currentTimeMillis() - startTime >= map.getEasyTime()*1000 && !isDead){
                isDead = gameOver("You ran out of time!");
            }
        }
    }
    public boolean loadNewMap(String difficulty, ArrayList<String> filenames){
        File directory=new File("src/maps/"+difficulty);
        int fileCount=directory.list().length;

        Random rand = new Random();

        if(fileCount > 1){
            int randIndex = rand.nextInt(fileCount);
            String filename = filenames.get(randIndex);
            filenames.remove(randIndex);
            this.map = new NodeMap("src/maps/"+difficulty+"/"+filename);
            return false;
        } else if(fileCount == 1){
            String filename = filenames.get(0);
            filenames.remove(0);
            this.map = new NodeMap("src/maps/"+difficulty+"/"+filename);
            return false;
        } else{
            return gameOver("You have completed the game!");
        }
    }
    public boolean gameOver(String prompt) {
        print("Game over! "+prompt);
        lineBreak();
        return true;
    }
    public  void print(String  info){System.out.println(info);}
    public  void lineBreak(){
        System.out.println("\n---------------");
    }
    public  void pressEnterToContinue(){
        print("Press Enter key to continue...");
        try { System.in.read();}
        catch(Exception e) {}
    }
    public  int fromConsoleGetInt(String prompt){
        print(prompt);
        return io.nextInt();
    }


}
