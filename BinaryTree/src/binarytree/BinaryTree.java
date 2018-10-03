package binarytree;
import java.io.*;

/**
 * Project class for program. Contains methods that create binary trees from files and iterates through the trees.
 * @author William Milligan
 * Collaborated with Rabia Ali
 */
public class BinaryTree {
	private Node root = null;
	private int size = 0;
	
        /**
         * Nested Node class. Contains references to three nodes and a character type.
         */
	private static class Node {
		private Character element;
		private Node left, right, parent;
		
                /**
                 * Node Constructor
                 * @param e is a character that is passed in, neglects to set other Node members
                 */
		Node(Character e) {
			element = e;		
		}
		/**
                 * Getter method for element
                 * @return element data member
                 */
		public Character getElement() {
			return element;
		}
		/**
                 * Getter method for left Node member
                 * @return left Node member
                 */
		public Node leftChild() {
			return left;
		}
		/**
                 * Getter method for right Node member
                 * @return right Node member
                 */
		public Node rightChild() {
			return right;
		}
		/**
                 * Getter method for parent Node member
                 * @return Node parent
                 */
		public Node parent() {
			return parent;
		}
                /**
                 * Setter method for right data member
                 * @param e is the Node that right should be changed too
                 */
                public void setRight(Node e){
                    right = e;
                }
                /**
                 * Setter method for left data member
                 * @param e is the Node that left should be changed too
                 */
                public void setLeft(Node e){
                    left = e;
                }
                /**
                 * Setter method for parent data member
                 * @param e is the Node that parent should be changed too
                 */
                public void setParent(Node e){
                    parent = e;
                }
	}
	
	/**
         * Constructor for Binary tree class Contains the logic for constructing the specific Binary tree.
         * @param fileName, is a string representation of the name of a file
         * @throws FileNotFoundException uncaught exception if the file name passed in by main method is not found
         * @throws IOException uncaught exception if the FileReader/BufferedReader fails to be created.
         */
	BinaryTree (String fileName) throws FileNotFoundException, IOException {
            FileReader input = new FileReader(fileName);
            BufferedReader br = new BufferedReader(input);
            String tree;
            Node traversal;
            Node newest = null;
            //Reads each line of a file
            while((tree = br.readLine()) != null)
            {
                tree=tree.trim();
                traversal = root;
                //Reads each data entry from the input file and process the data.
                for(int x = 0; x< tree.length();x++)
                {
                    char target = tree.charAt(x);
                    
                    // creates the node
                    if(x == 0)
                    {
                        newest = new Node(tree.charAt(x));
                    }
                    else if( target=='2' && x !=0 )
                    {// assignes node to root. Should only be called once
                        root = newest;
                        size++;
                    }
                    else if(x== tree.length() -1)
                    {//assigns new node Only should read the last line of string tree.
                        if(target == '0')
                        {//left
                            traversal.setLeft(newest);
                            newest.setParent(traversal);
                            size++;
                        }
                        if(target=='1')
                        {//right
                            traversal.setRight(newest);
                            newest.setParent(traversal);
                            size++;
                        }
                    }
                    else if(x != tree.length()-1)
                    {//traverses a temporary Node to the parent node of the new Node. Does not read the last line of the string Tree
                        if(target== '1')
                        {
                            traversal = traversal.rightChild();
                        }
                        if(target=='0')
                        {
                            traversal = traversal.leftChild();
                        }
                    } 
                }
            }
	}
	/**
         * Getter method for root data member
         * @return Root global data member
         */
	Node getRoot() {
		return root;
	}
	
        /**
         * Uses recursion to create a string that represents the In Order Traversal of an already created Binary tree.
         * @param n represents one Node with in the tree. For Correct results initial calling method should start at root.
         * @return A String that represents the In Order Traversal pattern
         */
	public String inOrderTraversalRecursive(Node n) {
            String result = "";
            Node traversal = n;
           if(size<= 0)
           { return "Error No Tree Has Been Created";}
            if(traversal.leftChild() != null)
            {
                 result += inOrderTraversalRecursive(traversal.leftChild());
            }
              result += (traversal.getElement() + " ");
            
            if(traversal.rightChild() != null)
            {
                result += inOrderTraversalRecursive(traversal.rightChild());
            }
          return result;
	}
	
        /**
         * Uses recursion to create a string that represents the  Pre-order Traversal of an already created Binary tree.
         * @param n represents one Node wtih in the tree. For Correct results initial calling method should start at root.
         * @return A String that represents the Pre-order Traversal pattern
         */
	public String preOrderTraversalRecursive(Node n) {
            String result = "";
             Node traversal = n;
            
            if(size<= 0)
           { return "Error No Tree Has Been Created";}
            
            result += (traversal.getElement() + " ");
            
              
            if(traversal.leftChild() != null)
            {
                 result +=preOrderTraversalRecursive(traversal.leftChild());
            }
            
            if(traversal.rightChild() != null)
            {
                result +=preOrderTraversalRecursive(traversal.rightChild());
            }
          return result;
	}
	
        /**
         * Uses a Queue to create the stack for a stair case traversal. Does not require recurssion
         * @param n represents one Node wtih in the tree. For Correct results initial calling method should start at root.
         * @return A string that represents the stair care traversal pattern
         */
	public String stairCaseTraversalNonRecursive(Node n) {
            String result = "";
            Node temp = getRoot();
            Queue q = new Queue();
            
            q.enqueue(temp);
            while( !q.isEmpty())
            {
                temp = q.deque();
                if(temp.leftChild() != null)
                {
                    q.enqueue(temp.leftChild());
                }
                if(temp.rightChild() != null)
                {
                    q.enqueue(temp.rightChild());
                }
                result += (temp.getElement() + " ");
            }       
            return result;
	}		
	
        /**
         * Recursive method that counts the number of "Leaf" Nodes in the tree
         * @param n represents one Node wtih in the tree. For Correct results initial calling method should start at root.
         * @return The int number of leaves, 
         */
	public int numberOfLeavesRecursive(Node n) {	
            int x = 0;	
            if(n==null)
                return 0;
            if((n.leftChild() == null) && (n.rightChild() == null))
                return 1;
            else
               x = (numberOfLeavesRecursive(n.rightChild()) + numberOfLeavesRecursive(n.leftChild()));
            return x;
        }
	/**
         * Main method establishes the flow of control for the program
         * @param args Does not use command line arguments
         * @throws FileNotFoundException FileNotFoundException uncaught exception if the file name passed in by main method is not found
         * @throws IOException uncaught exception if the FileReader/BufferedReader fails to be created.
         */
	public static void main(String[] args) throws FileNotFoundException, IOException {

            BinaryTree myTree = new BinaryTree("B1.txt");
            System.out.println("Inorder traversal: "  + myTree.inOrderTraversalRecursive(myTree.getRoot()));
            System.out.println("Preorder traversal: " + myTree.preOrderTraversalRecursive(myTree.getRoot()));
            System.out.println("Staircase traversal: "+ myTree.stairCaseTraversalNonRecursive(myTree.getRoot()));
            System.out.println("Number of leaves: "+ myTree.numberOfLeavesRecursive(myTree.getRoot()));
	}
        /**
         * Nested Class creates a special type of array
         */
    public class Queue{
        int CAPACITY = 100;
        Node [] q;
        int top = -1;
        int front = 0;
        int size = 0;
       
        public Queue(){
            q = new Node[CAPACITY];
        }
        public Queue(int limit)
        {
            q = new Node[limit];
        }
        public boolean isEmpty(){
            return (size == 0);
        }
        public Node deque(){
            Node temp = q[front];
            front++;
            size--;
            return temp;
        }
        
        public void enqueue(Node c){
            if( c == null)
            {
                return;
            }
            top++;
            q[top]= c;
            size++;
        }
        
        public Node top(){
            
            return q[top];
        }
        
       
    }
    
}
        
