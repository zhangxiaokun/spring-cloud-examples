package com.suanfa;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangxk
 * @description
 * @date 2019/9/11
 */
public class QueueArray<E> {
    //数组存放队列中元素个数
    private Object[] arr;
    private int head;
    private int tail;
    private int size;

    public QueueArray(int initCapacity) {
        arr = new Object[initCapacity];
        head = 0;
        tail = 0;
        size = 0;
    }

    public boolean push(Object object) {
        if (tail == arr.length) {
            expendSpace();
        }
        arr[tail] = object;
        tail++;
        size++;
        return true;
    }

    public Object poll() {
        if (head == tail) {
            return null; //队列为空
        }
        Object obj = arr[head];
        head++;
        size--;
        return obj;
    }
    public int size(){
        return size;
    }
    public void expendSpace() {
        Object[] a = new Object[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            a[i] = arr[i];
        }
        arr = a;
    }

    public static int getDuplicate(int [] array){
        if(array == null){
            return -1;
        }
        if(array.length<2){
            return -1;
        }
        for(int i =0;i<array.length;i++){
            if(array[i]<0 || array[i]>array.length -1)
            return -1;
        }
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i <array.length ; i++) {
            if(map.containsKey(array[i])){
                map.put(array[i],map.get(array[i])+1);
            }else{
                map.put(array[i],1);
            }
        }
        int maxNum = Collections.max(map.values());
        int value = -1;
        for(Map.Entry<Integer,Integer> entry: map.entrySet()) {
            if (entry.getValue() == maxNum) {
                value = entry.getKey();
                System.out.println("value:" + value);
                System.out.println("maxNum:" + entry.getValue());
            }
        }
        return value;
    }

    public static void main(String[] args) {
      /*  QueueArray queue = new QueueArray(2);
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.size);
        while(queue.size()>0){
            System.out.println("出队："+queue.poll());
        }
        System.out.println(queue.size);*/
      int[] arr = {1,2,2,2,3,4,4};
        int duplicate = getDuplicate(arr);
        System.out.println("重复最多数字是："+duplicate);
    }
}
