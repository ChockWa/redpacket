package com.hdh.redpacket.socket;

public class Client {

    public static void main(String[] args) {

        for(int i =0;i<100;i++){
            Thread thread = new Thread(new ClientThread("thread--"+i));
            thread.start();
        }





//        Thread clientThread1 = new Thread(new ClientThread("thread-1"));
//        Thread clientThread2 = new Thread(new ClientThread("thread-2"));
//        Thread clientThread3 = new Thread();
//        Thread clientThread4 = new Thread();
//        Thread clientThread5 = new Thread();
//        Thread clientThread6 = new Thread();
//        Thread clientThread7 = new Thread();
//        Thread clientThread8 = new Thread();
//        Thread clientThread9 = new Thread();
//
//        clientThread1.run();
//        clientThread2.run();
//        clientThread3.run();
//        clientThread4.run();
//        clientThread5.run();
//        clientThread6.run();
//        clientThread7.run();
//        clientThread8.run();
//        clientThread9.run();
//
//
//
//        new ClientThread("thread-3")
//        new ClientThread("thread-4")
//        new ClientThread("thread-5")
//        new ClientThread("thread-6")
//        new ClientThread("thread-7")
//        new ClientThread("thread-8")
//        new ClientThread("thread-9")



    }
}
