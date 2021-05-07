import java.util.ArrayList;

public class Node {

	private int id;
	private Node left;
	private Node right;
	private Node parent;
	private ArrayList<Product> product;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Node getLeft() {
		return left;
	}
	
	public void setLeft(Node left) {
		this.left = left;
	}
	
	public Node getRight() {
		return right;
	}
	
	public void setRight(Node right) {
		this.right = right;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public ArrayList<Product> getProduct() {
		return product;
	}
	
	public void setProduct(ArrayList<Product> product) {
		this.product = product;
	}
	
	static Node root;
	
	Node (int id) {
		this.setId(id);
	}
	
	static void insert (int id) {
		insert(id, root);
	}
	
	static void insert (int id, Node node) {
		
		if (root == null) {
            root = new Node(id);
        } else {

            Node focusNode = root;
            Node parent;

            while (true) {
            	
                parent = focusNode;

                if (id < focusNode.getId()) {

                    focusNode = focusNode.getLeft();

                    if (focusNode == null) {

                        parent.setLeft(new Node(id));
                        
                        return;
                    }

                } else {

                    focusNode = focusNode.getRight();

                    if (focusNode == null) {
                    	
                        parent.setRight(new Node(id));
                        
                        return;
                    }
                }
            }
        }
	}
	
	public Node find (int id) {

        Node focusNode = root;

        while (focusNode.getId() != id) {
            
            focusNode = id < focusNode.getId() ? focusNode.left : focusNode.right;
            
            if (focusNode == null) return null;
        }
        
        return focusNode;
    }
}
