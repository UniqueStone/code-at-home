package com.expedia.interview.ex2;

import org.junit.Test;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Exercise2 {

    class SinglyLinkedListNode {
        SinglyLinkedListNode next;
        int value;

        public SinglyLinkedListNode(int value) {
            this.value = value;
        }
    }

    // It's used to recode the node;
    Map<SinglyLinkedListNode, Integer> record = new HashMap<>();
    boolean hasCycle(SinglyLinkedListNode head) {

        if (null == head || null == head.next) {
            return false;
        }

        if (record.containsKey(head)) {
            return true;
        } else {
            record.put(head, head.value);
            return hasCycle(head.next);
        }
    }

    @Test
    public void test() {
        SinglyLinkedListNode head = new SinglyLinkedListNode(12);
        SinglyLinkedListNode node1 = new SinglyLinkedListNode(17);
        SinglyLinkedListNode node2 = new SinglyLinkedListNode(89);
        SinglyLinkedListNode node3 = new SinglyLinkedListNode(19);
        SinglyLinkedListNode node4 = new SinglyLinkedListNode(33);
        SinglyLinkedListNode node5 = new SinglyLinkedListNode(24);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node2;

        boolean result = hasCycle(head);
        System.out.println(result);
    }
}