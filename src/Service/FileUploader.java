package Service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.html.HTML;

public class FileUploader {
    private static String ScriptsURL = "http://http://localhost/images/";
    
    public static String upload(String path){
        try {
            HttpURLConnection httpUrlConnection = (HttpURLConnection)new URL(ScriptsURL+"upload.php").openConnection();
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setRequestMethod("POST");
            httpUrlConnection.setRequestProperty("Content-Type", "mutipart/form-data");
            BufferedInputStream fis;
            try (OutputStream os = httpUrlConnection.getOutputStream()) {
                Thread.sleep(1000);
                fis = new BufferedInputStream(new FileInputStream(path));
                int totalByte = fis.available();
                int byteTrasferred;
                for (int i = 0; i < totalByte; i++) {
                    os.write(fis.read());
                    byteTrasferred = i + 1;
                }
            }
            String returnedPath;
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            httpUrlConnection.getInputStream()))) {
                String s = null;
                returnedPath = "";
                while ((s = in.readLine()) != null) {
                    if(s!=null)
                        returnedPath+=s;
                }
            }
            fis.close();
            return returnedPath;
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(FileUploader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static boolean delete(String filename){
        try {
            URL url = new URL(ScriptsURL+"delete.php?filename="+filename);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            
            return (connection.getResponseCode() == 200);
        } catch (MalformedURLException ex) {
            Logger.getLogger(FileUploader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUploader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
