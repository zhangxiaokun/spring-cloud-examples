package com.suanfa;

import java.util.List;

/**
 * @author zhangxk
 * @description
 * @date 2019/9/11
 */
public class StackArray {
    //栈初始大小
    private final int len = 2;
    private Object[] elements;
    private int top;

    public StackArray() {
        top = -1;
        elements = new Object[len];
    }

    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top < 0;
    }

    public void expendSpace() {
        Object[] a = new Object[elements.length * 2];
        for (int i = 0; i < elements.length; i++) {
            a[i] = elements[i];
        }
        elements = a;
    }

    public void push(Object e) {
        if (size() == elements.length) {
            expendSpace();//栈满了，扩容
        }
        elements[++top] = e;
    }

    public Object pop() throws Exception {
        if (size() < 1) {
            throw new Exception("栈为空");
        }
        Object obj = elements[top];
        elements[top--] = null;
        return obj;
    }

    public Object peek() throws Exception {
        if (isEmpty()) {
            throw new Exception("栈为空");
        }
        return elements[top];
    }

    public static void main(String[] args) throws Exception {
      /*  StackArray stack = new StackArray();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        while (!stack.isEmpty())
            System.out.println("出栈：" + stack.pop());
        System.out.println(stack.size());*/
        int a = 0;
        for (int i = 0; i < 99; i++) {
             a++;
        }
        System.out.println(a);
        int b = 0;
        for (int i = 0; i < 99; i++) {
            b = ++b;
        }
        System.out.println(b);
        List<Integer>[] list = new List[10];
    }

}
