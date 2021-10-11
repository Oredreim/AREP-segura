package edu.escuelaing.arep.app;
import java.io.*;
import java.net.*;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class URLReader {
    /**
     * Metodo encargado de crear un archivo, con clave de representacion, en el que se inicializa y se obtiene el singleton de TrustManagerFactory.
     */
    public static void ssl() {
        try {
            File trustStoreFile = new File("keystores/myTrustStore");
            char[] trustStorePassword = "567890".toCharArray();
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(new FileInputStream(trustStoreFile), trustStorePassword);
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(trustStore);
            for(TrustManager t: tmf.getTrustManagers()){
                System.out.println(t);
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
            SSLContext.setDefault(sslContext);
        } catch (KeyStoreException ex) {
            Logger.getLogger(URLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(URLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(URLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(URLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(URLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(URLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Metodo encargado de crear el objeto que representa una URL, y crea su respectivo objeto URLConnection.
     * @param sitetoread Parametro encargado de leer el sitio web o URL registrada en el navegador.
     * @return Retorna la URL del sitio web de forma segura (HTTPS).
     */
    public static String readURL(String sitetoread) {
        try {
            URL siteURL = new URL(sitetoread);
            URLConnection urlConnection = siteURL.openConnection();
            System.out.println("-------message-body------");
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"utf-8"));
            StringBuilder response = new StringBuilder();
            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine.trim());
            }
            return response.toString();
        } catch (IOException x) {
            System.err.println(x);
        }
        return "";
    }
}