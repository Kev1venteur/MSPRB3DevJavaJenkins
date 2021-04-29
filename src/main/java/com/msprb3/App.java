package com.msprb3;

import java.io.*;


public class App {
    public static void main(String[] argsv) throws IOException {
        Export.genererAccueil();  //On appelle la génératon de la page index
        Export.genererAgent();    //On appelle la génération des pages agents
    }
}
