/*
I certify, that this computer program submitted by me is all of my own work.
Signed: Dylan Theis 9/27/2024

Author: Dylan Theis
Date: Fall 2024
Class: CSC420
Project: RandomList w/ Generics
Description: A linked list called RandomList, the adds, removes and gets will be random. Also includes a
printList function to display all the elements in the list, as well as a get function that
will get an element at a specific location. 
*/

import java.util.Random;

// Create our generic RandomList class
public class theisRandomList<E> {
    // Node class with generic data, previous, and next node
    private class Node {
        E data;
        Node prev;
        Node next;

        // Constructor
        Node(E data) {
            this.data = data;
        }
    }

    // Creating fields
    private Node head;
    private Node tail;
    private int size = 0;
    private Random random = new Random();

    // RandomList constructor
    public theisRandomList() {
        System.out.println("Dylan Theis - theisd@csp.edu");
        System.out.println("I certify that this is my own work.");
    }

    // Constructor to call RandomList and add element to the list using randomAdd
    public theisRandomList(E element) {
        this();
        randomAdd(element);
    }

    // randomAdd adds element randomly in the list
    public void randomAdd(E element) {
        // Create new node
        Node newNode = new Node(element);
        // If the list is empty
        if (head == null) {
            // Our new node becomes the head and the tail
            head = tail = newNode;
            // If list is not empty
        } else {
            // Randomly decide where to add (start or end)
            // If true add node to the start
            if (random.nextBoolean()) {
                // Make newNodes next to current head
                newNode.next = head;
                // Current heads previous points to newNode
                head.prev = newNode;
                // Head is our newNode
                head = newNode;
                // If false add new node to the end
            } else {
                // Make nodes previous pointer to the current tail
                tail.next = newNode;
                // Current tails next pointer is set to newNode
                newNode.prev = tail;
                // Tail points to our newNode
                tail = newNode;
            }
        }
        // Increment size by one for the new element
        size++;
    }

    // randomRemove randomly remove an element from the list
    public E randomRemove() {
        // If list is empty
        if (head == null) {
            return null;
        } else {
            // Remove a random node
            int index = random.nextInt(size);
            return remove(index);
        }
    }

    // Remove element at specific index
    public E remove(int index) {
        // If index is out of bounds
        if (index < 0 || index >= size) {
            return null;
        }
        // Start at the head
        Node current = head;
        // Loop through list until index
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        // Remove the node at index

        // If node is not the head
        if (current.prev != null) {
            // Change next pointer of previous node
            current.prev.next = current.next;
        } else {
            // If at the head, make the head the next node
            head = current.next;
        }
        // If node is not the tail
        if (current.next != null) {
            // Change previous pointer of next node
            current.next.prev = current.prev;
        } else {
            // If at the tail, make the tail the previous node
            tail = current.prev;
        }
        // Decrement size after removal and return data in the removed element
        size--;
        return current.data;
    }

    // Get a random element from the list
    public E randomGet() {
        // If list is empty
        if (head == null) {
            return null;
        } else {
            // Get random index
            int index = random.nextInt(size);
            return get(index);
        }
    }

    // Get element at specific index
    public E get(int index) {
        // If index out of bounds return null
        if (index < 0 || index >= size) {
            return null;
        }
        // Start at the head
        Node current = head;
        // Loop that starts at the head and goes until the element at the index
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        // Return data in node at index
        return current.data;
    }

    // Print the nodes in the list
    public void printList() {
        // Start at the head
        Node current = head;
        int index = 1;
        System.out.println("Displaying List using element's default toString() method:");
        // For each node print index and the node, increasing the index and moving the pointer to the next
        while (current != null) {
            System.out.println("\t" + index++ + "> " + current.data);
            current = current.next;
        }
    }

    // Print the reverse of the list
    public void printReverseList() {
        // Start at the tail
        Node current = tail;
        int index = size;
        System.out.println("Displaying Reverse List using element's default toString() method:");
        // For each node print index and the node, decreasing the index and moving the pointer to the previous
        while (current != null) {
            System.out.println("\t" + index-- + "> " + current.data);
            current = current.prev;
        }
    }

    // Test using strings and integers
    public static void main(String[] args) {
        // Add our strings to test
        System.out.println("New RandomList with Strings");
        theisRandomList<String> stringList = new theisRandomList<>();
        stringList.randomAdd("One");
        stringList.randomAdd("Two");
        stringList.randomAdd("Three");
        stringList.randomAdd("Four");
        stringList.randomAdd("Five");
        stringList.randomAdd("Six");
        stringList.randomAdd("Seven");
        stringList.randomAdd("Eight");
        stringList.randomAdd("Nine");
        stringList.randomAdd("Ten");

        // Print our string linked list
        stringList.printList();
        // Print the string linked list backwards
        stringList.printReverseList();

        // Remove a random element
        System.out.println("Removed Element: " + stringList.randomRemove());
        // Print our list to show element is removed
        stringList.printList();
        // Print again to show it isn't added back
        stringList.printList();

        // Get a random element
        System.out.println("Random Element: " + stringList.randomGet());
        // Get specific elements at index 9, 5, 2, 4, 10
        System.out.println("Specific Element: " + stringList.get(9));
        System.out.println("Specific Element: " + stringList.get(5));
        System.out.println("Specific Element: " + stringList.get(2));
        System.out.println("Specific Element: " + stringList.get(4));
        System.out.println("Specific Element: " + stringList.get(10));

        // Print our linked list with integers
        System.out.println("\nNew RandomList with Integers");
        theisRandomList<Integer> intList = new theisRandomList<>();
        // For our 10 element linked list randomly add each integer at a random node
        for (int i = 1; i <= 10; i++) {
            intList.randomAdd(i);
        }
        // Print our integer list
        intList.printList();
        // Randomly select 2 nodes to be removed
        System.out.println("Removed Item: " + intList.randomRemove());
        System.out.println("Removed Item: " + intList.randomRemove());
        // Print the list after the two nodes have been removed
        System.out.println("List after Removals");
        intList.printList();
        // Select a random node
        System.out.println("Random Item: " + intList.randomGet());
    }
}
