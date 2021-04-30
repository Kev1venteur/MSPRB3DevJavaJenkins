package com.epsi.msprb3;
import java.io.IOException;
import java.lang.*;

public class Socket {

    static class Thread1 implements Runnable{
        @Override
        public void run(){
            try {
                Export.genererAccueil();
                for(int i=0;i<10;i++){
                    System.out.println("Premier thread");
                }

            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }
    }
    static class Thread2 implements Runnable{
        @Override
        public void run(){
            try {
                Export.genererAgent();
                for(int a=0;a<10;a++){
                    System.out.println("Deuxieme thread");
                }
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }
    }

    public static void InitThread(){
        Thread t1 = new Thread(new Thread1());
        Thread t2 = new Thread(new Thread2());

        t1.start();
        t2.start();
    }
}
