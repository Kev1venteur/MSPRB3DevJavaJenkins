package com.epsi.msprb3;
import java.io.IOException;
import java.lang.*;

public class Socket {
    public static void InitThread(){
        new Thread(new Thread1()).start();
        new Thread(new Thread2()).start();
    }
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
                for(int i=0;i<10;i++){
                    System.out.println("Deuxième thread");
                }
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }
    }
}
