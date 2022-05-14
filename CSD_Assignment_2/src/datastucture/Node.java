package datastucture;

public class Node<T extends Comparable<T>> {

    public T val;
    public Node<T> left;
    public Node<T> right;

    public Node() {
    }

    public Node(T el) {
        this.val = el;
    }

    public Node(T el, Node lt, Node rt) {
        this.val = el;
        this.left = lt;
        this.right = rt;
    }
}
