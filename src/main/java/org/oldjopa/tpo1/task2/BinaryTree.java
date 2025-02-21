package org.oldjopa.tpo1.task2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class BinaryTree <T extends Comparable<T>> {
    private static class Node<T extends Comparable<T>> {
        T value;
        Node<T> left, right;

        public Node(T value) {
            this.value = value;
            left = right = null;
        }
    }
    Node<T> root;

    public void insert(T value) {
        root = insertRec(root, value);
    }

    private Node<T> insertRec(Node<T> root, T value) {
        if (root == null) {
            root = new Node<>(value);
            return root;
        }
        if ((value.compareTo(root.value)) < 0)
            root.left = insertRec(root.left, value);
        else //if ((value.compareTo(root.value)) >= 0)
            root.right = insertRec(root.right, value);
        return root;
    }

    public void delete(T value) {
        root = deleteRec(root, value);
    }


    private Node<T> deleteRec(Node<T> root, T value) {
        if ((value.compareTo(root.value)) < 0)
            root.left = deleteRec(root.left, value);
        else if (value.compareTo(root.value) > 0)
            root.right = deleteRec(root.right, value);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.value = minValue(root.right);
            root.right = deleteRec(root.right, root.value);
        }

        return root;
    }

    private T minValue(Node<T> root) {
        T minValue = root.value;
        while (root.left != null) {
            minValue = root.left.value;
            root = root.left;
        }
        return minValue;
    }

    public List<T> inorder() {
        return inorderRec(root);
    }

    private List<T> inorderRec(Node<T> root) {
        if (root != null) {
            List<T> result = new ArrayList<>(inorderRec(root.left));
            result.add(root.value);
            result.addAll(inorderRec(root.right));
            return result;
        }
        return new ArrayList<>();
    }

    public boolean find(T value) {
        return findRec(root, value);
    }

    private boolean findRec(Node<T> root, T value) {
        if (root == null) return false;
        if (value.compareTo(root.value) == 0) return true;
        if (value.compareTo(root.value) < 0)
            return findRec(root.left, value);
        else
            return findRec(root.right, value);
    }

}
