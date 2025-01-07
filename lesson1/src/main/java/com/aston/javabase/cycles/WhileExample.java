package com.aston.javabase.cycles;

public class WhileExample {

    public static void main(String[] args) {

        int countDown = 10;

        while (countDown >= 0) {
            System.out.println("До старта: " + countDown);
            countDown --;
        }

        System.out.println("Поехали !");
    }
}


//До старта: 10
//До старта: 9
//До старта: 8
//До старта: 7
//До старта: 6
//До старта: 5
//До старта: 4
//До старта: 3
//До старта: 2
//До старта: 1
//До старта: 0
//Поехали !
