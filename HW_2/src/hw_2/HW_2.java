/*
 * Author William Milligan
 * N00617078
 * SinglyLinkedList project// HW 2
 * Allows for the creation and manipulation of a user created singly linked list
 * Data Structures COP 3538
 * Collaboration with Rabia Ali
 */
package hw_2;

/**
 * This is the project class (term used to describe the class with the main method)
 * The project class is designed to be an aggregate of serval singly linked list. These Lists are in turn composed of Nodes
 * Each Node is designed to hold a primitive integer type and a reference to another Node.
 * With in the project class the methods of the Singly Linked Lists can be called using object references to manipulate the created Lists.
 * @author William Milligan
 */
public class HW_2 {

    /**
     * Is a project class that can create and manipulate any number of Singly Linked Lists
     * the lists are made using data read in from files kept in the project folder. These files are given names such as input1.txt, input2.txt
     * This program contains methods to create, reverse, and append linked lists. It also can scan elements in a list to look for specific primitive data types
     * It also has an overloaded toString method for both Nodes and SinglyLinkedListClass
     * @param No input command line arguments
     */
    public static void main(String[] args) {
        String path = "Input1.txt";
        SinglyLinkedList sLL1 = new SinglyLinkedList(path);
        System.out.println(sLL1);
    
       
        
    }
}
