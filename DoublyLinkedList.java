
import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.lang.Exception;

/**
 * A simple implementation of Doubly Linked List
 * @author Ken Ren
 * @version 1.0
 */
public class DoublyLinkedList<T> extends AbstractSequentialList<T> implements
List<T>, Cloneable, Serializable {
    Node head = new Node(null);
    Node tail = new Node(null);
    
    /**
     * Default Constructor gives empty Linked List
     */
    public DoublyLinkedList() {
        head.setNext(tail);
        head.setPrevious(null);
        tail.setPrevious(head);
    }

    /**
     * Add given value to a index
     * @param index the Index of the object being added
     * @param o the object being added
     */
    public void add(int index, T o) throws IndexOutOfBoundsException{
        Node a = new Node(o);
        a.setPrevious(getHelper(index));
        a.setNext(getHelper(index).getNext());
        a.getNext().setPrevious(a);
        a.getPrevious().setNext(a);
    }

    /**
     * Remove a element at given index, return the object being deleted
     * @param index the Index of the element being deleted
     * @return the object being deleted
     */
    public T remove(int index){
        Node current = this.getHelper(index);
        Node deleted = current.getNext();
        current.setNext(current.getNext().getNext());
        current.getNext().setPrevious(current);
        return (T) deleted.getData();
    }

    /**
     * Get element at given index
     * @param index the index of seeking element
     * @return data on given index
     */
    public T get(int index){
       return getHelper(index).getData();
    }

    /**
     * A Get helper returns the Node(pointer) of given index
     * @param index index that being searched
     * @return the node on given index
     */
    private Node getHelper(int index){
        if(index > this.size() || index<0){
            throw new IndexOutOfBoundsException();
        }
        Node current = head;
        if(index == 0){
            return current;
        }
        for(int i=0;i<index;i++){
            current = current.getNext();
        }
        return current;
    }

    /**
     * Adding a collection on given index
     * @param index adding to this index
     * @param c the collection being added
     */
    public boolean addAll(int index, Collection<? extends T> c){
        Object[] array = c.toArray();
        for(int i=0;i<c.size();i++){
            add(index, (T) array[i]);
            index++;
        }
        return true;
    }

    /**
     * Push a value in Linked List (Using it as Stack)
     * @param value the value being added
     */
    public void push(T value){
        this.add(0, value);
    }

    /**
     * Pop a value in Linked List (Using it as Stack)
     * @return the first value in the list
     */
    public T pop(){
        return this.remove(0);
    }

    /**
     * A Node class as the basic unit of the list
     * @author Ken Ren
     * @version 1.0
     */
    private static final class Node<T> {

        private T data; //data holder for the node
        private Node<T> next; //link to the next node
        private Node<T> previous; //link to the previous node
    
        /**
         * Constructor of Node, give a new Node with given data
         * @param data the data of the Node
         */
        public Node(T data) {
        this.data = data;
        this.next = null;
        }
    
        /**
         * Set the Next Node of the Current Node
         * @param n Pointer of the next Node
         */
        public void setNext(Node n){
            this.next = n;
        }
    
        /**
         * Get the Next Node of the Current Node
         * @return Pointer of the next Node
         */
        public Node getNext(){
            return this.next;
        }
    
        /**
         * Set the Previous Node of the Current Node
         * @param n Pointer of the Previous Node
         */
        public void setPrevious(Node n){
            this.previous = n;
        }
    
        /**
         * Get the Previous Node of the Current Node
         * @return Pointer of the Previous Node
         */
        public Node getPrevious(){
            return this.previous;
        }
    
        /**
         * Get the Data in Given Node
         * @return Data of the Node
         */
        public T getData(){
            return this.data;
        }
    }
    
    //Following Lines are for Testing, unquote them if you want quickly test the code.
    
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        for (int i=0;i<=20;i++){
            list.add(i, (int)i);
        }
        list.remove(9);
        list.remove(7);
        ArrayList<String> cars = new ArrayList<String>();
        cars.add("Volvo");
        cars.add("BMW");
        cars.add("Ford");
        cars.add("Mazda");
        list.addAll(1, cars);
        list.printlist();
        list.push((int) 99);
        list.printlist();
        System.out.println("Poped: "+list.pop());
        list.printlist();
        try {
            list.add(30,4);
        } catch (Exception e) {
            System.out.println("Successfully Catched Excepted Exception");
        }
        System.out.println("Size of the List: "+list.size());
    }
    

    /**
     * Print the List in Command Line, only for Testing purpose.
     */
    private void printlist(){
        String s="Head - ";
        Node current = this.head.getNext();
        while(current.next!=null){
            s = s + current.getData() + " - ";
            current = current.getNext();
        }
        s = s + "Tail";
        System.out.println(s);
    }

    /**
     * Give the size of the list exclude Head and Tail
     * @return the Size of the List
     */
    @Override
    public int size() {
        Node current = this.head.getNext();
        if(current.getData()==null){
            return 0;
        }
        int size = 0;
        while(current.next!=null){
            size++;
            current = current.getNext();
        }
        return size;
    }

    /**
     * Not Implemented
     */
    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

}


