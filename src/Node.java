public class Node {

    private int ID;
    private int leftID;
    private int rightID;
    private String description;
    private String question;

    private Node leftNode;
    private Node rightNode;

    public Node(int ID, int leftID, int rightID, String description, String question) {
        this.ID = ID;
        this.leftID = leftID;
        this.rightID = rightID;
        this.description = description;
        this.question = question;
    }

    public Node() {}

    public int getID() {return ID;}
    public void setID(int ID) {this.ID = ID;}
    public int getLeftID() {return leftID;}
    public void setLeftID(int leftID) {this.leftID = leftID; }
    public int getRightID() {return rightID;}
    public void setRightID(int rightID) {this.rightID = rightID;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description; }
    public String getQuestion() {return question;}
    public void setQuestion(String question) {this.question = question;}
    public Node getLeftNode() {return leftNode;}
    public void setLeftNode(Node yes) {this.leftNode = yes;}
    public Node getRightNode() {return rightNode;}
    public void setRightNode(Node no) {this.rightNode = no;}


    @Override
    public String toString() {
        return "nodeID:" + ID +
                ", leftID:" + leftID +
                ", rightID:" + rightID +
                ", description:'" + description + '\'' +
                ", question:'" + question + '\'';
    }


}
