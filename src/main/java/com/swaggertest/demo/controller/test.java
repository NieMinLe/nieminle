package com.swaggertest.demo.controller;

public class test {

    public static void main(String[] args) {
        System.out.println(numbera(50));
    }

    public static int numbera(int j){
        int count = j;

        if(j>=3){
            j=j/3;
            count += numbera(j);
        }
        return count;
    }

}
