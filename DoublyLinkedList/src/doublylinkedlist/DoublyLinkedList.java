/*
 * William Milligan
 * n00617078
 * Data Structures COP 3538
 * HW 3 Due 2/8/2018
 * This program utilizes a file read in from the project folder to populates a Doubly Linked list with inputs, in this case integers
 * The size of the list is the number of input toeksn in the file. The list is dynamic so more Nodes can be added later, though Nodes 
 * Can only be added to the back of the list.
 * The implementation also includes a method, NegateList which will convert positive integers to negative and negative integers to positive.
 * The toString method for this list is also overloaded to display a representation of each Node as the integer value it stores, 
 * With the symbol " <-> " used to represent the Double Linkages.
 */
package doublylinkedlist;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The DoublyLinkedList class is our project class. It contains main. It is also composed of Node classes. This class is completely dependant on Nodes.
 * @author William Milligan
 */
public class DoublyLinkedList {
    //instance variables
    Node head = null;
    Node tail = null;
    //Size is incremented by addLast()
    int size = 0;
    
    
    
    /**
     *main method, directs the flow of control for the DoublyLinkedList program
     * takes in nothing to String[] args. 
     * FNF exception is never handled, If FNF is thrown the program will terminate
     */
    public static void main(String[] args) throws FileNotFoundException {

    }
    
    /**
     * DoublyLinkedList constructor. Overrides default constructor. Creates a file from the passed in Name and utilizes the Scanner to read String input tokens from the File. 
     * These tokens are parsed to Int and passed into the addLast method. This is the only way to populate the DoublyLinkeList class with Nodes.
     * @param fileName Is a String that is used in the creation of a file. This File is then read by a Scanner class.
     * @throws FileNotFoundException which is never caught. FNF will terminate this program
     */
    public DoublyLinkedList(String fileName) throws FileNotFoundException
    {
        File inputFile = new File(fileName);
        Scanner input = new Scanner(inputFile);
        input.useDelimiter(",");
        while(input.hasNext())
        {
            addLast(Integer.parseInt(input.next().trim()));
        }
    }
    /**
     * Only method for adding Nodes to the DoublyLinkedList. The Doubly Linked List is composed of Nodes.
     * This method takes in an int element and assigns element to the Nodes instance variable storedValue.
     * @param element passes in integer that is assigned to storedValue
     */
    public void addLast(int element)
    {   //temporary Node
        Node newest = new Node(element,null,null);

        if(size == 0)
        {
            head = newest;
        }
        else
        {
            tail.setNext(newest);
            newest.setPrevious(tail);
        }
        tail = newest;
        size++;
        
    }
    /**
     * Overrides toString. Creates a representation an instance of the DoublyLinkedList class for use by standard Out.
     * Each node is represented by its storedVal with a special symbol denoting its linked nature.
     * @return The representation of the DoublyLinkedList
     */
    public String toString()
    {
        String result = "";
        if(size == 0)
            return "";
        if(size == 1)
        {result = (head.getStrovedVal()+"");}
        if(size == 2)
        {
            result = (head.getStrovedVal() + " <->" + tail.getStrovedVal());
        }
        if(size >= 3)
        {
            Node a = head;
            while(a.getNext()!=null)
            {
                result += (a.getStrovedVal() + " <-> ");
                a=a.getNext();
            }
            result += tail.getStrovedVal();
        }
        return result;
    }
    /**
     *  Method accesses each elements stored in each Node of the List and uses multiplication by -1 to reverse the sign.
     */
    public void negateList()
    {
        Node a = head;
        while(a!=null)
        {
            a.setElement(a.getStrovedVal()*-1);
            a = a.getNext();
        }
    }   
    
private static class Node
{   //instance variables for Nested Node class
    private Node next;
    private Node previous;
    private int storedValue;
    
    /**
     * Node constructor
     * @param element is an integer value to be stored i 
     * @param n
     * @param p 
     */
    public Node(int element, Node n, Node p){
        
        next = n;
        previous = p;
        storedValue = element;
        
    }
    /**
     * Mutator method that changed the Node object reference in the global variable next to the passed in Node who's informal name is e
     * @param e The node that the global object reference "next" is to be changed to
     */
    public void setNext(Node e){
        next = e;
    }
    /**
     * Mutator method that changed the Node object reference in previous to the passed in Node
     * @param r is of Node data type and is passed in from calling method.
     */
    public void setPrevious(Node p){
        previous = p;
    }
    /**
     * Mutator method that changes the value of the int element of a specific Node
     * @param c is an int passed into the node to mutate the private variable element
     */
    public void setElement(int c)
    {
        storedValue  = c;
    }
    /**
     * Method the returns object reference next
     * @return next, the link to the right in the chain
     */
    public Node getNext()
    {
        return next;
    }
    /**
     * Method that returns the object reference to previous
     * @return previous, the link to the object "left" of the current node
     */
    public Node getPrevious(){
        return previous;
    }
    /**
     * Method the fetches the int storedValue
     * @return storedValue, is an int encapsulated in each Node.
     */
    public int getStrovedVal(){
        return storedValue;
    }
}//end Node Class
            




}




