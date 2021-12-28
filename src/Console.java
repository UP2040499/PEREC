import java.util.Scanner;

public class Console {

     Scanner io;

    public Console(NodeMap map){

        io = new Scanner(System.in);

        long startTime = System.currentTimeMillis();

        while (map.currentNode() != null) {

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
            } else {
                // This is where it needs to evaluate whether to go for hard, easy or medium.
                long endTime = System.currentTimeMillis() - startTime;
                long elapsedMinutes = endTime / 60000;
                // Maps will be sorted in to hard/medium/easy folders.
                // Then to pick the map choose one at random from the hard/medium/easy folder
                map.NextMap("src/dataCorrected.csv");
            }
        }
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
