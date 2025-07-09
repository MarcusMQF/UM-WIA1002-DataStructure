public class CircularLinkedListDemo {

    public static class Node<E>{
        private E data;
        private Node<E> next;

        public Node(E data){
            this.data = data;
            this.next = null;
        }

        public E getData(){
            return data;
        }

        public Node<E> getNext(){
            return next;
        }

        public void setNext(Node<E> next){
            this.next = next;
        }
    }

    public static class CircularLinkedList<E>{
        private Node<E> head;
        private int size;

        public CircularLinkedList(){
            this.head = null;
            this.size = 0;
        }

        public void add(E data){
            Node<E> newNode = new Node<>(data);
            if(head == null){
                head = newNode;
                head.setNext(head);
            }else{
                Node<E> current = head;
                while(current.getNext() != head){
                    current = current.getNext();
                }
                current.setNext(newNode);
                newNode.setNext(head);
            }
            size++;
        }

        // Add a node at a specific index
        public void addAtIndex(E data, int index) {
            if (index < 0 || index > size) {
                return; // Invalid index
            }
            Node<E> newNode = new Node<>(data);
            if (index == 0) {
                if (head == null) {
                    head = newNode;
                    head.setNext(head);
                } else {
                    Node<E> last = head;
                    while (last.getNext() != head) {
                        last = last.getNext();
                    }
                    newNode.setNext(head);
                    last.setNext(newNode);
                    head = newNode;
                }
            } else {
                Node<E> current = head;
                for (int i = 0; i < index - 1; i++) {
                    current = current.getNext();
                }
                newNode.setNext(current.getNext());
                current.setNext(newNode);
            }
            size++;
        }

        // Remove a node at a specific index
        public boolean removeAtIndex(int index) {
            if (index < 0 || index >= size || head == null) {
                return false; // Invalid index or empty list
            }
            if (size == 1) {
                head = null;
            } else if (index == 0) {
                Node<E> last = head;
                while (last.getNext() != head) {
                    last = last.getNext();
                }
                head = head.getNext();
                last.setNext(head);
            } else {
                Node<E> current = head;
                for (int i = 0; i < index - 1; i++) {
                    current = current.getNext();
                }
                current.setNext(current.getNext().getNext());
                if (index == size - 1) {
                    current.setNext(head); // Ensure circularity
                }
            }
            size--;
            return true;
        }

        // Get data at a specific index
        public E get(int index) {
            if (index < 0 || index >= size || head == null) {
                return null; // Invalid index or empty list
            }
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current.getData();
        }

        // Get size of the list
        public int size() {
            return size;
        }

        // Print the list
        public void printList() {
            if (head == null) {
                System.out.println("List is empty");
                return;
            }
            Node<E> current = head;
            do {
                System.out.print(current.getData() + " ");
                current = current.getNext();
            } while (current != head);
            System.out.println();
        }


    }
    public static void main(String[] args) {
        // Create a new circular linked list
        CircularLinkedList<Integer> list = new CircularLinkedList<>();

        // Add elements
        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println("Initial list (at 01:54 PM +08, Wednesday, July 09, 2025):");
        list.printList(); // Expected: 10 20 30

        // Add at index 1
        list.addAtIndex(15, 1);
        System.out.println("After adding 15 at index 1:");
        list.printList(); // Expected: 10 15 20 30

        // Remove at index 2
        list.removeAtIndex(2);
        System.out.println("After removing element at index 2:");
        list.printList(); // Expected: 10 15 30

        // Get element at index 1
        Integer value = list.get(1);
        System.out.println("Element at index 1: " + value); // Expected: 15

        // Print size
        System.out.println("Size of list: " + list.size()); // Expected: 3
    }
}
