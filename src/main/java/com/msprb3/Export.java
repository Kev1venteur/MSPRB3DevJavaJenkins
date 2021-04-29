package com.msprb3;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Export {
    public static void genererAccueil() throws IOException {
        BufferedReader lecture;
        String ligne1;

        try{
            File fichierIndex = new File("index.html");

            if(!fichierIndex.exists()){
                fichierIndex.createNewFile();
            }

            FileWriter fichierIndexEcriture = new FileWriter(fichierIndex.getAbsoluteFile());
            BufferedWriter bufferIndex = new BufferedWriter(fichierIndexEcriture);

            bufferIndex.write("<!DOCTYPE html>\n "+
                    "<html lang='en'>\n"+
                    "<head>\n <title>MSPR - JAVA</title>\n" +
                    "<meta charset='utf-8'>\n "+
                    "<link rel='stylesheet' href='style/accueil.css'>\n"+
                    " </head>\n "+
                    "<img class='Logo' src='annexes/images/LogoGOSecuri.jpg' alt='Logo GOSecuri'>\n"+
                    "<h1>MSPR - JAVA Accueil</h1>\n"+
                    "<body>\n"+
                    "<div class='List'>\n");


            lecture = new BufferedReader(new FileReader("fiches_agents/staff.txt"));

            List liste = new ArrayList();
            while ((ligne1 = lecture.readLine()) != null){
                liste.add(ligne1);
                Collections.sort(liste);
            }

            for (Object o : liste) {
                bufferIndex.write("<a href='agents/" + o + ".html'>" + o + "</a><br>\n");
            }


            bufferIndex.write("</div>\n"+
                    "</body>\n"+
                    "<footer>\n MSPR - JAVA © 2021 Copyright\n</footer>\n"+
                    "</html>");
            bufferIndex.close();

        }
        catch (FileNotFoundException exc){
            System.out.println("Erreur !");

        }
    }

    public static void genererAgent() throws IOException {
        BufferedReader lecture;
        BufferedReader lectureAgentStuff;
        String ligne;
        String ligneAgentStuff;
        String personneHTML;
        String personne;

        try{
            lecture = new BufferedReader(new FileReader("fiches_agents/staff.txt"));

            while ((ligne = lecture.readLine()) != null){

                //System.out.println(ligne);
                personneHTML = "agents/"+ligne+".html";
                personne = ligne;

                File fichier = new File(personneHTML);

                if(!fichier.exists()){
                    fichier.createNewFile();
                }

                FileWriter fichierEcriture = new FileWriter(fichier.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fichierEcriture);

                bw.write("<!DOCTYPE html>\n");
                bw.write("<html lang='fr'>\n");
                bw.write("<head>\n " +
                        "<title>"+personne+"</title>\n"+
                        "<meta charset='utf-8'>\n"+
                        "<link rel='stylesheet' href='../style/agent.css'>\n"+
                        "</head>\n");
                bw.write("<div class='Bouton'><button class='favorite styled' type='button' ht onclick=window.location.href='../index.html'>Retour</button>\n</div>\n");
                bw.write("<div class='Imageprofile'>\n<img class='ImageIdentite' src='../fiches_agents_photos/"+personne+".jpg' alt='Photo identite'>\n</div>\n");
                bw.write("<body>\n");
                lectureAgentStuff = new BufferedReader(new FileReader("fiches_agents/"+personne+".txt"));
                int i = 0;
                bw.write("<h1>");
                while ((ligneAgentStuff = lectureAgentStuff.readLine()) != null){
                    if ((i==0) || (i==1)) {
                        bw.write(ligneAgentStuff + " ");
                    }
                    if (i==2) {
                        bw.write("</h1>");
                        bw.write(" \n");
                        bw.write("<br>");
                        bw.write("<div class='Poste'>");
                        bw.write("<p>Poste : <strong>"+ligneAgentStuff+"</strong></p>");
                        bw.write("</div>");
                        bw.write("\n");
                        bw.write("<p>Equipements :\n</p>");
                    }

                    if (i>4) {
                        bw.write("<br>");
                        bw.write("<div class='Materiel'>");
                        bw.write("<p> - "+ligneAgentStuff+" ✅</p>");
                        bw.write("</div>");
                        bw.write("\n");
                    }
                    i++;
                    //done
                }

                lectureAgentStuff.close();

                bw.write("</body>\n"+
                        "<footer>\n MSPR - JAVA © 2021 Copyright\n</footer>\n"+
                        "</html>");

                bw.close();
            }

            System.out.println("Done !");
            lecture.close();

        }catch(FileNotFoundException exc){
            System.out.println("Pouet !");
        }

    }
}
