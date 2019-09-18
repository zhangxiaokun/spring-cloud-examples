package com.suanfa;

import com.google.gson.Gson;

import java.util.*;

/**
 * @author zhangxk
 * @description
 * @date 2019/6/19
 */
public class Node {
    private Object data;
    private Node next;

    public Node(Integer integer) {
        this.data = integer;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node Merge(Node list1, Node list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if ((Integer) list1.data <= (Integer) list2.data) {
            list1.next = Merge(list1.next, list2);
            return list1;
        } else {
            list2.next = Merge(list1, list2.next);
            return list2;
        }
    }

    public static List<Integer> topKFun(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        //遍历map，用最小堆保存频率最大的k个元素,用优先队列实现最小堆
        PriorityQueue<Integer> qp = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        for (Integer key : map.keySet()) {
            if (qp.size() < k) {
                qp.add(key);
            } else if (map.get(key) > map.get(qp.peek())) {
                qp.poll();
                qp.add(key);
            }
        }
        List<Integer> list = new ArrayList<>();
        while (!qp.isEmpty()) {
            list.add(qp.poll());
        }
        return list;
    }

    public static List<Integer> getTop(int[] arr, int k) {
        PriorityQueue<Integer> qp = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        for (Integer key : arr) {
            if (qp.size() < k) {
                qp.add(key);
            } else if (key > qp.peek()) {
                qp.poll();
                qp.add(key);
            }
        }
        LinkedList<Integer> list = new LinkedList<>();
        while (!qp.isEmpty()) {
            list.addFirst(qp.poll());
        }
        return list;
    }

    public Node getXJNode(Node headA, Node headB) {
        if (headA == null || headB == null) {
            return null;
        }
        Node pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    public static void main(String[] args) {
      /*  int[] arr = {1, 2, 3, 3, 3, 4, 4, 5, 6};
        List list = getTop(arr, 4);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }*/
        int[] arr1 = {1, 2, 3};
        List<int[]> ints = Arrays.asList(arr1);
        Set<Integer> set = new HashSet();
        set.add(1);
        System.out.println(new Gson().toJson(set));
        System.out.println(set.size());
        boolean add = set.add(1);
        System.out.println(add);
        boolean add2 = set.add(4);
        System.out.println(set.size());
        System.out.println(add2);
        System.out.println(set.add(4));
    }

    public boolean hasCycle(Node head) {
        Set<Node> node = new HashSet<>();
        while (head != null) {
            boolean b = node.add(head);
            if (!b) {
                return true;
            }
            head = head.next;
        }
        return false;
    }
}
