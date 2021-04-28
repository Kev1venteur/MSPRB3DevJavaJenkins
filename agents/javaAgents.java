package agents;

import java.io.*;

public class javaAgents {
    public static void main(String[] args) throws IOException {
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
                bw.write("<html lang=\"en\">\n" + "<body>\n");

                bw.write("<img class='ImageIdentite' src='fiches_agents_photos/"+personne+".jpg' alt='Photo identite'>\n");
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
                        bw.write("<p>"+ligneAgentStuff+"</p>");
                        bw.write("</div>");
                        bw.write("\n");
                    }

                    if (i>4) {
                        bw.write("<br>");
                        bw.write("<div class='Materiel'>");
                        bw.write("<p>"+ligneAgentStuff+"</p>");
                        bw.write("</div>");
                        bw.write("\n");
                    }
                    i++;
                    //done
                }

                lectureAgentStuff.close();

                bw.write("</body>\n</html>");

                bw.close();
            }

            System.out.println("Done !");
            lecture.close();

        }catch(FileNotFoundException exc){
            System.out.println("Pouet !");
        }

    }

}
