package datastucture;

import data.Vocabulary;
import static java.lang.Integer.max;
import java.util.ArrayList;
import java.util.Arrays;

public class BST<T extends Comparable<T>> {

    public Node<T> root;

    public BST() {
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void deleteAll() {
        root = null;
    }

    public void add(T el) {
        Node<T> p = new Node(el);
        if (isEmpty()) {
            root = p;
            return;
        }
        Node<T> tmp = root;
        while (tmp != null) {
            if (p.val.compareTo(tmp.val) <= 0) {
                if (tmp.left == null) {
                    tmp.left = p;
                    if (!isBalanced(root)) {
                        balanceTree();
                    }
                    return;
                }
                tmp = tmp.left;
            } else {
                if (tmp.right == null) {
                    tmp.right = p;
                    if (!isBalanced(root)) {
                        balanceTree();
                    }
                    return;
                }
                tmp = tmp.right;
            }
        }
    }

    public Node rightMost(Node tmp) {
        if (tmp.left != null) {
            tmp = tmp.left;
            while (tmp.right != null) {
                tmp = tmp.right;
            }
        }
        return tmp;
    }

    private Node leftMost(Node tmp) {
        if (tmp.right != null) {
            tmp = tmp.right;
            while (tmp.left != null) {
                tmp = tmp.left;
            }
        }
        return tmp;
    }

    private Node rightMostOf(T val) {
        Node tmp = root;
        while (tmp.val.compareTo(val) != 0 && tmp != null) {
            if (tmp.val.compareTo(val) == 1) {
                tmp = tmp.left;
            }
            if (tmp.val.compareTo(val) == -1) {
                tmp = tmp.right;
            }
        }
        System.out.println(tmp.val);
        return rightMost(tmp);
    }

    public Node leftMostOf(T val) {
        Node tmp = root;
        while (tmp.val.compareTo(val) != 0 && tmp != null) {
            if (tmp.val.compareTo(val) == -1) {
                tmp = tmp.left;
            }
            if (tmp.val.compareTo(val) == 1) {
                tmp = tmp.right;
            }
        }
        System.out.println(tmp.val);
        return leftMost(tmp);
    }

    public void deleteNode(T val) {
        deleteNode(root, val);
        if (!isBalanced(root)) {
            balanceTree();
        }
    }

    private Node deleteNode(Node tmp, T val) {
        if (isEmpty()) {
            return tmp;
        }

        if (tmp.val.compareTo(val) > 0) {
            tmp.left = deleteNode(tmp.left, val);
        } else if (tmp.val.compareTo(val) < 0) {
            tmp.right = deleteNode(tmp.right, val);
        } else {
            if (tmp.left == null) {
                return tmp.right;
            } else if (tmp.right == null) {
                return tmp.left;
            }

            tmp.val = rightMost(tmp.right).val;
            tmp.right = deleteNode(tmp.right, (T) tmp.val);
        }

        return tmp;
    }

    public Node search(T key) {
        return searchNode(root, key);
    }

    private Node searchNode(Node root, T key) {
        if (root == null || root.val.compareTo(key) == 0) {
            return root;
        }
        if (root.val.compareTo(key) < 0) {
            return searchNode(root.right, key);
        }
        return searchNode(root.left, key);
    }

    private ArrayList sortArr(ArrayList<T> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr.get(j).compareTo(arr.get(j + 1)) > 0) {
                    T temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
        }
        return arr;
    }

    public int size() {
        return numberNode(root);
    }

    private int numberNode(Node tmp) {
        if (tmp == null) {
            return 0;
        }
        return 1 + numberNode(tmp.left) + numberNode(tmp.right);
    }

    public void ArrToSBT(ArrayList<T> arr) {
        arr = sortArr(arr);
        root = SortArrToSBT(arr, 0, arr.size() - 1);
    }

    private Node SortArrToSBT(ArrayList<T> arr, int begin, int end) {

        if (begin > end) {
            return null;
        }

        int mid = (begin + end) / 2;

        Node node = new Node(arr.get(mid));

        node.left = SortArrToSBT(arr, begin, mid - 1);
        node.right = SortArrToSBT(arr, mid + 1, end);

        return node;
    }

    public ArrayList toArray() {
        ArrayList<T> tmp = new ArrayList<>();
        toArray(root, tmp);
        return tmp;
    }

    private ArrayList<T> toArray(Node tmp, ArrayList<T> arr) {
        if (tmp == null) {
            return arr;
        }

        toArray(tmp.left, arr);
        arr.add((T) tmp.val);
        toArray(tmp.right, arr);
        return arr;

    }

    public void balanceTree() {
        ArrayList<T> tmp = toArray();
        tmp = sortArr(tmp);
        ArrToSBT(tmp);
    }

    public boolean isBalanced(Node node) {
        int lh;

        int rh;

        if (node == null) {
            return true;
        }

        lh = height(node.left);
        rh = height(node.right);

        return Math.abs(lh - rh) <= 1
                && isBalanced(node.left)
                && isBalanced(node.right);
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }

        return 1 + Math.max(height(node.left), height(node.right));
    }

    public void LNR(Node tmp) {
        if (tmp != null) {
            LNR(tmp.left);
            System.out.print(tmp.val + " ");
            LNR(tmp.right);
        }
    }

    @Override
    public String toString() {
        ArrayList<T> arr = (ArrayList<T>) toArray();
        String tmp = "";
        for (int i = 0; i < arr.size(); i++) {
            tmp = tmp.concat(arr.get(i).toString() + "\n");
        }
        return tmp;
    }

    public void printTrack(T Tsrc, T Tdest) {
        if (isEmpty()) {
            System.out.println("Empty tree.");
            return;
        }
        if (Tsrc == null || Tdest == null) {
            System.out.println("Error.");
        } else {
            Node<T> src = new Node<T>(Tsrc);
            Node<T> dest = new Node<T>(Tdest);
            if (src.val.compareTo(dest.val) == 0) {
                System.out.println(src.val);
            } else {
                ArrayList<T> Psrc = new ArrayList<>();
                ArrayList<T> Pdest = new ArrayList<>();
                Node<T> tmp = root;
                //Lưu lại đường đi từ root tới điểm source
                while (tmp != null && tmp.val.compareTo(src.val) != 0) {
                    if (src.val.compareTo(tmp.val) > 0) {
                        Psrc.add(tmp.val);
                        tmp = tmp.right;
                    } else {
                        Psrc.add(tmp.val);
                        tmp = tmp.left;
                    }
                }

                if (tmp == null) {
                    System.out.println(src.val.toString() + " does not exist.");
                    return;
                } else {
                    Psrc.add(tmp.val);
                }
                //Lưu lại đường đi từ root tới điểm destination
                tmp = root;
                while (tmp != null && tmp.val.compareTo(dest.val) != 0) {
                    if (dest.val.compareTo(tmp.val) > 0) {
                        Pdest.add(tmp.val);
                        tmp = tmp.right;
                    } else {
                        Pdest.add(tmp.val);
                        tmp = tmp.left;
                    }
                }

                if (tmp == null) {
                    System.out.println(dest.val.toString() + " does not exist.");
                    return;
                } else {
                    Pdest.add(tmp.val);
                }
                //Tìm điểm chung đầu tiên của đường đi tới 2 điểm 
                int posi = -1, posj = -1;
                for (int i = Psrc.size() - 1; i >= 0 && posi == -1; i--) {
                    for (int j = Pdest.size() - 1; j >= 0; j--) {
                        if (Psrc.get(i).compareTo(Pdest.get(j)) == 0) {
                            posi = i;
                            posj = j;
                            break;
                        }
                    }
                }
                // In ra đường đi

                for (int i = Psrc.size() - 1; i > posi; i--) {
                    System.out.print(Psrc.get(i).toString() + " => ");
                }

                for (int j = posj; j < Pdest.size() - 1; j++) {
                    System.out.print(Pdest.get(j).toString() + " => ");
                }
                System.out.println(Pdest.get(Pdest.size() - 1).toString());

            }
        }
    }
}

