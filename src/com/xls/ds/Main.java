package com.xls.ds;


import java.util.HashMap;

public class Main {


    private static String test5;

    public void fun1() {

    }
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "test1");
        map.put(2, "test2");
        map.put(3, "test3");
        map.put(4, "test4");
        test5 = "test5";
        map.put(5, test5);

        map.put(5, "test5");
        map.put(5, "test5");

        map.put(5, "test5");
        map.put(5, "test5");
    }

}
