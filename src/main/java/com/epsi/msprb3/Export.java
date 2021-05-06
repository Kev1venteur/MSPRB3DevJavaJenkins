package com.epsi.msprb3;

import java.lang.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;                                                                     //Importation des librairies utiles au projets
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.security.*;
import java.security.MessageDigest;

public class Export {
    public static void genererAccueil() throws IOException {
        BufferedReader lecture;         //Définition des variables
        String ligne1;

        try {                                                                                    //Lancement du code
            File fichierIndex = new File("/var/www/html/msprb3/index.html");                                //Définition de quel fichier va être utiliser

            if (!fichierIndex.exists()) {                                                         //Verification si le fichier existe,
                fichierIndex.createNewFile();                                                   //Dans le cas contraire, il est créé
            }

            FileWriter fichierIndexEcriture = new FileWriter(fichierIndex.getAbsoluteFile(), StandardCharsets.UTF_8);   //Recuperation du chemin exacte du fichier définit précédemment dans la variable fichierIndex
            BufferedWriter bufferIndex = new BufferedWriter(fichierIndexEcriture);              //Stockage dans un buffer

            bufferIndex.write("<!DOCTYPE html>\n " +
                    "<html lang='en'>\n" +
                    "<head>\n <title>GO Securi - JAVA</title>\n" +
                    "<meta charset='utf-8'>\n " +
                    "<link rel='stylesheet' href='/style/accueil.css'>\n" +                       //Ecriture du code HTML
                    " </head>\n " +
                    "<div class='Logo'><img class='Logo' src='/images/LogoGOSecuri.jpg' alt='Logo GOSecuri'></div><br>\n" +
                    "<div><h1>GO Securi Accueil</h1></div><br>\n" +
                    "<body>\n" +
                    "<div class='List'>\n");


            lecture = new BufferedReader(new InputStreamReader(new FileInputStream("website/fiches_agents/staff.txt"), StandardCharsets.UTF_8));    //Lecture du fichier staff.txt ligne par ligne

            List liste = new ArrayList();                                                       //Creation d'une liste qui va stocker nos agents
            while ((ligne1 = lecture.readLine()) != null) {                                      //Tant qu'il y a des lignes, la boucle continue
                liste.add(ligne1);                                                              //Ajout d'une ligne du fichier txt dans la ligne
                Collections.sort(liste);                                                        //Rangement de la liste par ordre alphabétique
            }

            for (Object o : liste) {
                bufferIndex.write("<a href='/agents/" + o + ".html'>" + o + "</a><br>\n");   //Pour chaque objet dans la liste, écriture de l'objet (pnom des agents)
            }


            bufferIndex.write("</div>\n" +                                                   //Ecriture de l'HTML (fin)
                    "</body>\n" +
                    "<footer>\n GO Securi © 2021 Copyright\n</footer>\n" +
                    "</html>");
            bufferIndex.close();                                                                //Fermeture du buffer ouvert au début

        } catch (FileNotFoundException exc) {                                                      //Si il y a une erreur, affiche Erreur dans le terminal
            exc.printStackTrace();

        }
    }

    public static void genererAgent() throws IOException {
        BufferedReader lecture;
        BufferedReader lectureAgentStuff;
        String ligne;
        String ligneAgentStuff;
        String personneHTML;
        String personne;

        try {
            lecture = new BufferedReader(new InputStreamReader(new FileInputStream("website/fiches_agents/staff.txt"), StandardCharsets.UTF_8));        //Lecture du fichier staff.txt

            while ((ligne = lecture.readLine()) != null) {                                           //Tant qu'il y a des lignes dans staff.txt, il continue la boucle

                //System.out.println(ligne);
                personneHTML = "/var/www/html/msprb3/agents/" + ligne + ".html";                                             //Deux variable, l'une avec la personne et l'autre avec la personne avec un .html ) la fin
                personne = ligne;

                File fichier = new File(personneHTML);                                              //Le nom du fichier sera la personne.html
                File dossierAgent = new File("/var/www/html/msprb3/agents");

                if (!dossierAgent.exists()) {                                                              //Si le dossier n'existe pas, création de ce dernier
                    dossierAgent.mkdir();
                }


                if (!fichier.exists()) {                                                              //Si le fichier n'existe pas, création de ce dernier
                    fichier.createNewFile();
                }

                FileWriter fichierEcriture = new FileWriter(fichier.getAbsoluteFile(), StandardCharsets.UTF_8);             //Recuperation du chemin du fichier
                BufferedWriter bw = new BufferedWriter(fichierEcriture);                            //Ajout dans un buffer

                bw.write("<!DOCTYPE html>\n");
                bw.write("<html lang='fr'>\n");
                bw.write("<head>\n " +
                        "<title>" + personne + "</title>\n" +                                            //Generation du code HTML dans le fichier
                        "<meta charset='utf-8'>\n" +
                        "<link rel='stylesheet' href='/style/agent.css'>\n" +
                        "</head>\n");
                bw.write("<div class='Bouton'><button class='favorite styled' type='button' ht onclick=window.location.href='/index.html'>Retour</button>\n</div>\n");
                bw.write("<div class='Imageprofile'>\n<img class='ImageIdentite' src='/fiches_agents_photos/" + personne + ".jpg' alt='Photo identite'>\n</div>\n");
                bw.write("<body>\n");

                lectureAgentStuff = new BufferedReader(new InputStreamReader(new FileInputStream("website/fiches_agents/" + personne + ".txt"), StandardCharsets.UTF_8));       //Lecture du fichier txt qui appartient à la personne

                int i = 0;
                bw.write("<h1>");
                while ((ligneAgentStuff = lectureAgentStuff.readLine()) != null) {                                       //Tant qu'il y a des lignes, il va continuer la boucle
                    if ((i == 0) || (i == 1)) {                                                         //Pour les 2 première lignes
                        bw.write(ligneAgentStuff + " ");                                        //Ecriture du nom/prenom
                    }
                    if (i == 2) {                                                                     //Pour la 3eme ligne, ecriture du poste
                        bw.write("</h1>");
                        bw.write(" \n");
                        bw.write("<br>");
                        bw.write("<div class='Poste'>");
                        bw.write("<p>Poste : <strong>" + ligneAgentStuff + "</strong></p>");
                        bw.write("</div>");
                        bw.write("\n");
                        bw.write("<p>Equipements :\n</p>");
                    }                                                                               //Pas d'affichage de la 4eme ligne car c'est le mot de passe

                    if (i > 4) {                                                                      //Pour la 5eme ligne et plus, c'est le materiel de la personne qui est écrit
                        bw.write("<br>");
                        bw.write("<div class='Materiel'>");
                        bw.write("<p> - " + ligneAgentStuff + " ✅</p>");
                        bw.write("</div>");
                        bw.write("\n");
                    }
                    i++;
                    //done
                }
                lectureAgentStuff.close();

                bw.write("</body>\n" +
                        "<footer>\n GO Securi © 2021 Copyright\n</footer>\n" +
                        "</html>");
                bw.close();
            }

            System.out.println("Done !");
            lecture.close();

        } catch (FileNotFoundException exc) {                                                              //Si il y a une erreur, envoie une erreur différente que de la page index
            exc.printStackTrace();
        }
    }

    public static void genererHtAccess() throws IOException {
        //CREATION MDP DANS FICHIER HTPASSWD et Creation du fichier HTACCES
        try {
            BufferedReader lectureHtAccess;
            String ligneHtAccess;
            BufferedReader lectureAgentStuff;
            String ligneAgentStuff;

            File fichierHtAccess = new File("/var/www/html/msprb3/agents/.htaccess");
            if (!fichierHtAccess.exists()) {                                                              //Si le fichier n'existe pas, création de ce dernier
                fichierHtAccess.createNewFile();
            }
            File fichierHtPassword = new File("/var/www/html/msprb3/agents/.htpasswd");
            if (!fichierHtPassword.exists()) {                                                              //Si le fichier n'existe pas, création de ce dernier
                fichierHtPassword.createNewFile();
            }

            FileWriter fichierEcritureAccess1 = new FileWriter(fichierHtAccess.getAbsoluteFile(), StandardCharsets.UTF_8);             //Recuperation du chemin du fichier
            BufferedWriter bwAccess1 = new BufferedWriter(fichierEcritureAccess1);
            bwAccess1.write("AuthName \"Zone Securisee\"\n" +
                    "AuthType Basic\n" +
                    "AuthUserFile \"/mnt/jenkins/agents/.htpasswd\"\n");

            lectureHtAccess = new BufferedReader(new InputStreamReader(new FileInputStream("website/fiches_agents/staff.txt"), StandardCharsets.UTF_8));   //Recuperation du chemin du fichier
            while ((ligneAgentStuff = lectureHtAccess.readLine()) != null) {
                bwAccess1.write("<FilesMatch \"" + ligneAgentStuff + ".html\">\n" +      //Ecriture de chaque user dans le htaccess
                        " Require user " + ligneAgentStuff + "\n" +
                        "</FilesMatch>\n");
            }
            bwAccess1.close(); // Fermeture du buffer pour validation de l'ecriture

            FileWriter fichierEcritureAccess2 = new FileWriter(fichierHtPassword.getAbsoluteFile(), StandardCharsets.UTF_8);  //Recuperation du chemin du fichier
            BufferedWriter bwAccess2 = new BufferedWriter(fichierEcritureAccess2);
            lectureHtAccess = new BufferedReader(new InputStreamReader(new FileInputStream("website/fiches_agents/staff.txt"), StandardCharsets.UTF_8));

            while ((ligneHtAccess = lectureHtAccess.readLine()) != null) {
                bwAccess2.write(ligneHtAccess + ":");                                                  //ajout user dans fichier htaccess
                lectureAgentStuff = new BufferedReader(new InputStreamReader(new FileInputStream("website/fiches_agents/" + ligneHtAccess + ".txt"), StandardCharsets.UTF_8));       //Lecture du fichier txt qui appartient à la personne
                int i = 0;
                while ((ligneAgentStuff = lectureAgentStuff.readLine()) != null) {
                    if (i == 3) {
                        String encodedString = Base64.getEncoder().encodeToString(MessageDigest.getInstance("SHA1").digest(ligneAgentStuff.getBytes(StandardCharsets.UTF_8)));   //Encodage du mot de passe en sha-1 Base64
                        bwAccess2.write("{SHA}" + encodedString + "\n");  //on ecrit les mots de passe dans le fichier .htpasswd
                    }
                    i++;
                    //done
                }
            }
            bwAccess2.close(); // Fermeture du buffer pour validation de l'ecriture
        } catch (FileNotFoundException | NoSuchAlgorithmException exc) {    //Si il y a une erreur, envoie une erreur différente que de la page index
            exc.printStackTrace();
        }
    }
}