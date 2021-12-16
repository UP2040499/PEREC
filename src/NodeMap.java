import java.io.FileNotFoundException;



public class NodeMap {

    private Node head;
    private Node currentNode;
    private NodeCollection currentMap;

/****************************************************/
/**************      NAVIGATE       *****************/
/****************************************************/
/****************************************************/
    public Node currentNode() { return currentNode;}

    public void noDecision(){
        currentNode = currentNode.getLeftNode();
    }

    public void decision(int decision) {
        switch (decision) {
            case 1:
                currentNode = currentNode.getLeftNode();
                break;
            case 2:
                currentNode = currentNode.getRightNode();
                break;
        }
    }
/****************************************************/
/**************         BUILD      ******************/
/****************************************************/
/****************************************************/

    public NodeMap(String pathname)  {
        try {
            currentMap = new NodeCollection(pathname);
            head = currentMap.get(0);
        } catch (FileNotFoundException e) {
            //message
            return;
        }
        buildMap(currentMap);
        currentNode = head;
    }

    public void NextMap(String pathname)  {
        try {
            currentMap = new NodeCollection(pathname);
            head = currentMap.get(0);
        } catch (FileNotFoundException e) {
            //message
            return;
        }
        buildMap(currentMap);
        currentNode = head;
    }


    private void buildMap(NodeCollection nodeCollection)   {
        if (nodeCollection == null) {return;}
        for(Node source : nodeCollection.arrayList()){
            if (source == null){
                break;
            }
            int yesID = source.getLeftID();
            int noID = source.getRightID();
            Node yesNode = nodeCollection.locateNodeBy(yesID);
            Node noNode = nodeCollection.locateNodeBy(noID);
            source.setLeftNode(yesNode);
            source.setRightNode(noNode);
        }
    }

    public String toString(){
        String string = "";
        string += yesPath() + "\n";
        string += noPath() + "\n";
        return string;
    }

    public String yesPath(){
        Node node = head;
        StringBuilder string = new StringBuilder("YES PATH\n");
        while(node != null) {
            string.append(node).append("\n");
            node = node.getLeftNode();
            if (node.getID() == 0) { node = null;}
        }
        return string.toString();
    }

    public String noPath(){
        Node node = head;
        StringBuilder string = new StringBuilder("NO PATH\n");
        while(node != null) {
            string.append(node).append("\n");
            node = node.getRightNode();
            if (node.getID() == 0) { node = null;}
        }
        return string.toString();
    }


    public int lastNode() {
        return currentMap.Size()-1;
    }
}