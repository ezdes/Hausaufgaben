package LinkedList;

public class CustomLinkedList<E> {

    private Node<E> first;
    private Node<E> last;
    private Node<E> current;
    private Integer index = 0;

    public void add(E e) {
        Node<E> l = last;
        Node<E> newNode = new Node<E>(e, null, l, index);
        index++;
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
    }

    public void print() {
        current = first;
        while (current != null) {
            System.out.print(current.element + " ");
            current = current.getNext();
        }
    }

    public Integer size() {
        Integer size = 0;
        current = first;
        while (current != null) {
            size++;
            current = current.getNext();
        }
        return size;
    }

    public E get(Integer index) {
        E e = null;
        if (!check(index)) {
            current = first;
            while (current != null) {
                if (current.getIndex().equals(index)) e = current.element;
                current = current.getNext();
            }
        } else {
            throw new IndexOutOfBoundsException();
        }

        return e;
    }

    private Boolean check(Integer index) {
        return index < 0 || index + 1 > size();
    }

    private static class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> prev;
        private Integer index;

        public Node(E element, Node<E> next, Node<E> prev, Integer index) {
            this.element = element;
            this.next = next;
            this.prev = prev;
            this.index = index;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }
    }
}
