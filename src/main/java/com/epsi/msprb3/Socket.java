package com.epsi.msprb3;
import java.io.IOException;
import java.lang.*;

public class Socket {

    static class ThreadAccueil extends Thread{
        @Override
        public void run(){
            try {
                Export.genererAccueil();
                for(int i=0;i<10000;i++){
                    System.out.println("Premier thread");
                }

            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }
    }

    static class ThreadAgent extends Thread{
        @Override
        public void run(){
            try {
                Export.genererAgent();
                for(int a=0;a<10000;a++){
                    System.out.println("Deuxieme thread");
                }
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }
    }

    public static void InitThread(){
        Thread tAgent = new Thread(new ThreadAccueil());
        Thread tAccueil = new Thread(new ThreadAgent());

        tAgent.start();
        tAccueil.start();
    }
}
