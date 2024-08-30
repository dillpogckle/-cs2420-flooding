import java.util.ArrayList;

public class LinkedList<T>{
    private Node head;
    private Node tail;

    // nodes for linked list, has data of type T and pointer to next value
    private class Node {
        T data;
        Node next;
        public Node(T data){
            this.data = data;
        }
    }

    // inserts a new node at the tail
    public void insert(T data){
        Node node = new Node(data);
        // handles empty list
        if (tail == null){
           tail = node;
           head = tail;
        }
        //  handles all other cases
        else {
            tail.next = node;
            tail = tail.next;
        }
    }

   // removes the node at head and returns true, return false if list is empty
    public boolean remove(){
        // empty list
        if (head == null){
            return false;
        }
        // other scenarios
        else{
            head = head.next;
            return true;
        }
    }

   // prints linked list to console
    @Override
    public String toString() {
        Node curr = head;
        if (curr == null){
            return "Empty List";
        }
        StringBuilder queue = new StringBuilder();
        while (curr != tail){
            queue.append(curr.data).append("->");
            curr = curr.next;
        }
        queue.append(curr.data);
        return queue.toString();
    }
}
