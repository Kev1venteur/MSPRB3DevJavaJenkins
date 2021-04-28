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

                System.out.println(ligne);
                personneHTML = "agents/"+ligne+".html";
                personne = ligne;

                File fichier = new File(personneHTML);

                if(!fichier.exists()){
                    fichier.createNewFile();
                }

                FileWriter fichierEcriture = new FileWriter(fichier.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fichierEcriture);

                bw.write("<!DOCTYPE html>\n");
                bw.write("<html lang=\"en\">\n" + "<body>\n" + "<h1>"+personne+"</h1>\n");

                lectureAgentStuff = new BufferedReader(new FileReader("fiches_agents/"+personne+".txt"));

                while ((ligneAgentStuff = lectureAgentStuff.readLine()) != null)
                    bw.write(ligneAgentStuff+"\n<br>");
                    //System.out.println(ligneAgentStuff);
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
