import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.valueOf;

public class NodeCollection {

    private ArrayList<Node> nodes;
    private long[] parameters;
/****************************************************/
/*************    SUPPORT MAP BUILDER  **************/
/****************************************************/
/****************************************************/
    public Node locateNodeBy(int nodeID) {
        for (Node n : nodes) {
            if (nodeID == n.getID()) {
                return n;
            }
        }
        return new Node();
    }
    public ArrayList<Node> arrayList(){
        return nodes;
    }
    public long[] getParameters(){
        return parameters;
    }
    public Node get(int index){ return nodes.get(index); }
/****************************************************/
/**************         BUILD      ******************/
/****************************************************/
/****************************************************/
    public NodeCollection(String pathname)  throws FileNotFoundException {
        java.io.File prc = new java.io.File(pathname);
        Scanner fileRef = new Scanner(prc);
        nodes = new ArrayList<Node>();
        Node node;
        String[] firstLine = fileRef.nextLine().split(","); // uses first line for parameters of map, does not get put into node map
        for(int i = 0; i < 3; i++) {
            parameters[i] = Long.parseLong(firstLine[i]);
        }

        while (fileRef.hasNext()) {
            String line = fileRef.nextLine();
            node = mapFields(line);
            nodes.add(node);
        }
        fileRef.close();
    }

    //@NotNull
    private Node mapFields(String line) {
        // Parsing each line so that embedded commas in the csv do not get split and cause weirdness
        List<String> stringArray = new ArrayList<String>();
        boolean inQuotes = false;
        StringBuilder b = new StringBuilder();
        for (char c : line.toCharArray()) {
            switch (c) {
                case ',':
                    if (inQuotes) {
                        b.append(c);
                    } else {
                        stringArray.add(b.toString());
                        b = new StringBuilder();
                    }
                    break;
                case '"':
                    inQuotes = !inQuotes;
                    break;  // don't add the double quotes
                default:
                    b.append(c);
                    break;
            }
        }
        stringArray.add(b.toString());
        Node n = new Node();
        n.setID(valueOf(stringArray.get(0)));
        try {
            n.setLeftID(valueOf(stringArray.get(1)));
            n.setRightID(valueOf(stringArray.get(2)));
            n.setDescription(stringArray.get(3));
            n.setQuestion(stringArray.get(4));
        } catch (Exception NumberFormatException){
            // some way of setting the current node to null
            try {
                n.setDescription(stringArray.get(3));
                n.setQuestion(stringArray.get(4));
            } catch(Exception e){
                return null;    // set node to null if line is completely blank apart from ID
            }
        }
        return n;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        for(Node n : nodes){
            str.append(n.toString()).append("\n");
        }
        return str.toString();
    }

    public int Size(){
        return nodes.size();
    }
}
