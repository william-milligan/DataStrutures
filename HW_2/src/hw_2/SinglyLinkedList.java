/*
 * Author William Milligan
 * SinglyLinkedList project// HW 2
 * Allows for the creation and manipulation of a user created singly linked list
 * Data Structures COP 3538
 * Collaboration with Rabia Ali
 */
package hw_2;

/**
 * Singly Linked List Class contains nested Node Class. 
 * @author William Milligan N00617078
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
    
      
    public class SinglyLinkedList {
        
        Node head;
        Node tail;
        Scanner input;
        int size = 0;
   /**
    * constructor for SinglyLinkedList takes in string to be used as fileName
    * @param fileName is a String that is passed in from the main method. Should match the name of the file that contain the data used to create 
    * the Singly Linked Lists.
    */
    public SinglyLinkedList(String fileName)
    {
        
        File file = new File(fileName);
        
        try
        {
            input = new Scanner(file);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found 1");
        }
        input.hasNext();
        int count = 0;
        input.useDelimiter(",");
        //Scans document to figure out proper size of array
        while(input.hasNext())
        {//Counts input tokens to use variable count to determine size of the array
            count++;
           input.next();
        }
        
        input.close();
            try 
            {
                input=new Scanner(file);
            } 
            catch (FileNotFoundException ex)
            {
                System.out.println("File Not Found");
            }
        input.useDelimiter(",");
        int[] data = new int[count];
        String converter;
        int loop = 0;
        while(input.hasNext())
        {//converts String input tokens deliniated by commas into int values and makes sure there are no leading spaces.
            converter =(input.next().trim());
            data[loop] = Integer.parseInt(converter);
            loop++;
        }
        
        // Instantiates Nodes in the order they occure in the array with the same int storedValue using addLast method
        //Called method located at line 148
        Node a;
        for(int x = 0; x< data.length;x++)
        {
            a =new Node(data[x], null);
            addLast(a);
        }
    }//end Constructor
    
    
    /**
     * Nested Class Node
     * Contains constructors, getters, and setters for Node class 
     * Node Class stores an int value in global variable storedValue
     * Node Class stores and object reference to another Node. That represents the next link in a singly linked list.
     * @author William Milligan N00617078
     */
    public static class Node
    {
        private int storedValue;
        private Node next = null;
       /**
        * constructor for node Class. Singly Linked Lists are composed of Nodes
        * @param value takes in an int value to store in element part of Node
        * @param e is a reference object to the next Node in the Singly Linked List. May point to null at end of list
        */
        
        public Node(int value, Node e)
        {
            storedValue = value;
            next = e;
        }

        /**
         * Accessor Method for private variable storedValue
         * @return int value in storedValue
         */
        public int getStoredValue()
        {
            return storedValue;
        }
        /**
         * Acessor Method for Next reference in Node
         * @return Node Next which points to the next node in list
         */
        public Node getNext()
        {
            return next;
        }

        /**
         * Mutator Method for private reference Next
         * @param e takes in Node E and sets private reference next to passed in reference
         */
        public void setNext(Node e)
        {
            next = e;
        }
        
        public String toString()
        {
            return ("" + getStoredValue());
        }
    }//end Node Class

        //Continuation of SinglyLinkedList functions :  Constructor is at line 26
        /**
         * Short hand method to see if the size of a given list is zero. Size is only incremented by add first and add last method.
         * @return The boolean value of the expression size == 0
         */
        public boolean isEmpty()
        {
            return (size ==0);
        }
        
        /**
         * Add Last takes in a Node created in the calling method and appends the node to the tail of the calling linked list.
         * @param e Represents the Passed in Node created by the calling method
         */
        public void addLast(Node e)
        {
            if(isEmpty())
            {
                head = e;
                tail = head;
                tail.setNext(null);
                size++;
            }
            
            else if(tail == null)
            {//Cases where the tail has not bee initilized
                head.setNext(e);
                tail = e;
                size++;
            }
            else
            {// Cases where the head and the tail are already initialized
                tail.setNext(e);
                tail = e;
                size++;
            }
        }
       
        /**
         * Iterates through each node in the list and displays the value of int stored value with some formatting
         */
        public String toString()
        {
          Node a = head;
          String s = "";
          if(size == 1)
              System.out.println(head);
          if(size>=2)
          {
              do
              {
                  s += (a + " -> ");
                  a=a.getNext();

                  if(a.getNext()==null)
                  {
                      s += a;
                  }
              }
              while(a.getNext() != null);
          }
          return s;
          
        }
        /**
         * Reverses the links of a Singly Linked List and swaps head with tail.
         * if A list pointed 1 -> 2 -> 3 After calling this method the same list will point 1 <- 2 <- 3 with 3 being the head
         * Uses three temporary nodes that initailze to head
         * Contains logice for cases if size == 1 || size == 0, size == 2 , size ==3, and size > 3
         */
        public void reverseList()
        {
            Node temp = head;
            Node temp2 = head;
            Node temp3 = head;
           
            if(this.isEmpty() || size == 1)
           {
               return;
           }
           if(size == 2)
           {
               tail.setNext(head);
               head = tail;
               tail = temp;
               tail.setNext(null);
               
           }
           if(size ==3)
           {
                temp2 = temp.getNext();
                temp3 = temp2.getNext();
                temp2.setNext(temp); 
                temp3.setNext(temp2);
                temp.setNext(null);
                head = temp3;
                tail = temp;
           }
           if (size>3)
           {
            temp2 = temp.getNext();
            while(temp3.getNext() != null)
            {
                    temp3 = temp2.getNext();
                    temp2.setNext(temp);
                    temp = temp2;
                    temp2 = temp3;
                    temp3 = temp3.getNext();
                    
                if(temp3.getNext() == null)
                { //controlls the last stage of the loop
                    temp3.setNext(temp2);
                    temp2.setNext(temp);
                        break;
                }
            }
            //swaps head an tail
            temp = head;
            head = tail;
            tail = temp;
            tail.setNext(null);
           }
        }
        
       /**
        * Adds nodes from passed in Singly Linked List "anotherList" to the end of the calling Singly Linked List
        * @param anotherList is another Singly Linked List that must be created and passed in before calling the method.
        */
        public void appendList(SinglyLinkedList anotherList)
        {   //checks to ensure another list has been populated
            if(anotherList.size == 0)
            {
                return;
            }
            if(anotherList.size == 1)
            {
               
                tail.setNext(anotherList.head);
                tail = tail.getNext();
                size++;
            }
            if(anotherList.size > 1)
            {
                Node temp = head;
                tail.setNext( anotherList.head);
                for(   ; temp != null;  temp = temp.getNext())
                {
                    if(temp.getNext() == null)
                    {
                        tail = temp;
                    }
                }
            }
        }
        /**
         * Scans each element of the linked list and checks for equality with int element.
         * @param element The value that the user is checking the Linked List for occurences of
         * @return The number of times that element occures in the scanned Linked list
         */
        public int countOccurence(int element){
            Node iterator = head;
            int count = 0;
            for(   ; iterator != null;  iterator = iterator.getNext())
            {
                if(iterator.getStoredValue()==element)
                    count++;
                
            }
            return count;
        }
        
    }//end Class
















