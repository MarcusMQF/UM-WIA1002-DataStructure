// 2025 Sem2 Final Exam Questions

import java.util.Scanner;

public class DoublyPalindrome {

    public static class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    public static class DoublyLinkedList<E> {
        private Node<E> head;
        private Node<E> tail;
        private int size;

        public DoublyLinkedList() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        // addLast should use tail directly, not loop from head
        public void addLast(E data) {
            Node<E> newNode = new Node<>(data);
            if (head == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;        // link old tail to new node
                newNode.prev = tail;        // link new node back to old tail
                tail = newNode;             // update tail
            }
            size++;
        }

        public boolean isPalindrome() {
            Node<E> left = head;
            Node<E> right = tail;

            // don't modify head and tail directly
            while (left != null && right != null && left != right && right.next != left) {
                // use .equals() instead of != / == for object comparison
                if (!left.data.equals(right.data)) {
                    return false;
                }
                left = left.next;
                right = right.prev;
            }

            return true;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DoublyLinkedList<Character> list = new DoublyLinkedList<>();

        System.out.print("Input: ");
        String word = scanner.nextLine();

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            list.addLast(c);
        }

        System.out.println("Is Palindrome? " + list.isPalindrome());
    }
}
