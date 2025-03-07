package org.example;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.oldjopa.tpo1.task2.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * Unit test for simple App.
 */
class Task2Test {
    private BinaryTree<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new BinaryTree<>();
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);
    }

    @Test
    void testInsert() {
        tree.insert(90);
        assertTrue(tree.find(90));
    }

    @Test
    void testInsertExisting() {
        tree.insert(70);
        assertTrue(tree.find(70));
    }

    @Test
    void testDeleteNodeWithOneChild() {
        tree.delete(80);
        tree.delete(70);
        assertFalse(tree.find(70));
        assertFalse(tree.find(80));
    }

    @Test
    void testDeleteLeafNode() {
        tree.delete(20);
        assertFalse(tree.find(20));
    }

    @Test
    void testDeleteNodeWithTwoChildren() {
        tree.delete(30);
        assertFalse(tree.find(30));
    }

    @Test
    void testDeleteRootNode() {
        tree.delete(50);
        assertFalse(tree.find(50));
    }

    @Test
    void testInorderTraversal() {
        List<Integer> targetList = Stream.iterate(20, x -> x+10).limit(7).toList();
        tree.inorder();
        assertEquals(targetList, tree.inorder());
    }

    @Test
    void testFindExistingElement() {
        assertTrue(tree.find(40));
    }

    @Test
    void testFindNonExistingElement() {
        assertFalse(tree.find(100));
    }

    @Test
    void testFindAfterDelete() {
        tree.delete(40);
        assertFalse(tree.find(40));
    }
}
