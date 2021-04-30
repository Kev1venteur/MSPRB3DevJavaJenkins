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
                System.out.println(1);
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
                System.out.println(2);
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }
    }
}
