package com.epsi.msprb3;
import java.io.IOException;
import java.lang.*;

public class Socket {

    static class ThreadAccueil extends Thread{                  //Creation premier thread
        @Override
        public void run(){                                      //Reecriture du run pour ThreadAccueil
            try {
                Export.genererAccueil();                        //Le run va lancer la génération de l'accueil ou afficher une erreur s'il ne peut pas

            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }
    }

    static class ThreadAgent extends Thread{                    //Creation du deuxieme thread
        @Override
        public void run(){                                      //Reecriture du run pour ThreadAgent
            try {
                Export.genererAgent();                          //Le run va lancer la génération des agents ou afficher une erreur s'il ne peut pas
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }
    }

    public static void InitThread(){
        Thread tAccueil = new Thread(new ThreadAccueil());      //Initialisation des threads
        Thread tAgent = new Thread(new ThreadAgent());


        tAccueil.start();                                       //Lancement des threads
        tAgent.start();                                         //Disclaimer : Les programmes sont trop rapides donc pas possible de vérifier sans modifier le code actuellement
                                                                //que les deux threads s'éxécutent bien en même temps. Mais après des tests, il s'est avéré qu'ils le font.
    }
}
