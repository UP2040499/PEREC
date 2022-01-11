import java.util.Scanner;

public class Console {

     Scanner io;

    public Console(NodeMap map){

        io = new Scanner(System.in);

        long startTime =  System.currentTimeMillis();
        boolean isGameOver = false;

        while (!isGameOver) {
            boolean isDead = map.currentNode() == null;

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
                long elapsedMinutes = endTime / 60000;
                if(elapsedMinutes <= map.getHardTime()){
                    //go to a random hard map
                    //what if run out of hard maps?
                    //perhaps use an array to store already used maps to guarantee no repetition
                } else if(elapsedMinutes <= map.getMediumTime()){
                    //go to a random medium map
                }else{
                    //go to a random easy map
                }
                // Maps will be sorted in to hard/medium/easy folders.
                // Then to pick the map choose one at random from the hard/medium/easy folder
                map.NextMap("src/dataCorrected.csv");
                startTime = System.currentTimeMillis(); //new start time for next map
            } else{
                // This will also catch other issues but is meant to be accessed when isDead==true
                isGameOver = gameOver("You died!");
            }
            // Out of time check
            if(System.currentTimeMillis() - startTime >= map.getEasyTime() && !isDead){
                isGameOver = gameOver("You ran out of time!");
            }
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
