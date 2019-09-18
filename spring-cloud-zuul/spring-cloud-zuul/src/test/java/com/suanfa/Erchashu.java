package com.suanfa;

import java.util.*;

/**
 * @author zhangxk
 * @description
 * @date 2019/6/19
 */
public class Erchashu {
    //使用栈的先进后出实现单链表的反转
    public static ArrayList<Integer> reverseNode(Node listNode) {
        Stack stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode.getData());
            listNode = listNode.getNext();
        }
        ArrayList ret = new ArrayList<>();
        while (!stack.isEmpty()) {
            ret.add(stack.pop());
        }
        return ret;
    }

    public static ArrayList<Integer> shendu(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode tree = stack.pop();
            //先往栈中压入右节点，再压左节点，这样出栈就是先左后右了
            if (tree.right != null) {
                stack.push(tree.right);
            }
            if (tree.left != null) {
                stack.push(tree.left);
            }
            list.add(tree.val);
        }
        return list;
    }

    public static ArrayList<Integer> guangdu(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null)
            return list;
        // 队列添加元素只能从队尾添加，删除只能从队头删除。
        //队列的实现是链表，LinkedList 实现Deque接口，Deque是Queue的一个子接口
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode tree = queue.poll();
            if (tree.left != null)
                queue.offer(tree.left);
            if (tree.right != null)
                queue.offer(tree.right);
            list.add(tree.val);
        }
        return list;
    }

    // 后续非递归遍历
    public static LinkedList houxu(TreeNode root) {
        LinkedList list = new LinkedList();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode tree = stack.pop();
            list.addFirst(tree.val);
            if (tree.left != null) {
                stack.push(tree.left);
            }
            if (tree.right != null) {
                stack.push(tree.right);
            }
        }
        return list;
    }

    // 中序非递归遍历
    public static List zhongxu(TreeNode root) {
        List list = new ArrayList();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return list;
        }
        //所有栈为空，且节点指向为空，即所有节点已经完成遍历
        while (root != null || !stack.isEmpty()) {
            // 一直向左，将沿途节点压入堆栈
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                TreeNode tree = stack.pop();
                list.add(tree.val);
                root = tree.right;
            }
        }
        return list;
    }

    TreeNode kthNode(TreeNode pRoot, int k) {
        if (pRoot == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        int count = 0;
        TreeNode temp = null;
        while (pRoot != null || !stack.isEmpty()) {
            while (pRoot != null) {
                stack.push(pRoot);
                pRoot = pRoot.left;
            }
            TreeNode node = stack.pop();
            count++;
            if (count == k) {
                temp = node;
            }
            pRoot = node.right;
        }
        return temp;
    }


}
