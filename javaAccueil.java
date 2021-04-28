import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class javaAccueil {
    public static void main(String[] argsv) throws IOException {
        BufferedReader lecture;
        String ligne;

        try{
            lecture = new BufferedReader(new FileReader("fiches_agents/staff.txt"));
            System.out.println("<!DOCTYPE html>\n");
            System.out.println("<html lang=\"en\">\n<body>\n<h1>Accueil !</h1>");
            while ((ligne = lecture.readLine()) != null)
                System.out.println("<a href='agents/"+ligne+".html'>"+ligne+"</a><br>");
            lecture.close();
            System.out.println("</body></html>");
        }
        catch (FileNotFoundException exc){
            System.out.println("Erreur d'ouverture");

        }
    }
}
