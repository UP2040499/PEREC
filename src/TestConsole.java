


import java.io.FileNotFoundException;

public class TestConsole {

    public static void main(String[] args) throws FileNotFoundException {

        NodeMap map = new NodeMap("src/maps/medium/chessRoom.csv");

        Console c = new Console(map);

    }


}
