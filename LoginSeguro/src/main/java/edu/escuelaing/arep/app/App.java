package edu.escuelaing.arep.app;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import static spark.Spark.*;
import com.google.common.hash.Hashing;
import com.google.gson.Gson;

public class App 
{
    /**
     * Metodo principal main encargado de recibir peticiones de inicio de sesion del usuario.
     * @param args Argumentos que entran a la clase principal.
     */
    public static void main( String[] args ) {
        port(getPort());
        URLReader.ssl();
        Gson gson=new Gson();
        Map<String,String> users=new HashMap<>();
        users.put("holi123@mail.com",Hashing.sha256().hashString("123", StandardCharsets.UTF_8).toString());
        staticFileLocation("/public");
        secure("keystores/ecikeystore.p12", "holi123", null, null);
        get("/hello", (req, res) -> "Hello World");
        get("/", (req, res) -> {
            res.redirect( "index.html");
            return "";
        });
        post("/login", (req, res) ->{
            req.session(true);
            User user = gson.fromJson(req.body(), User.class);
            if(Hashing.sha256().hashString(user.getPassword(), StandardCharsets.UTF_8).toString().equals(users.get(user.getEmail()))){
                req.session().attribute("User",user.getEmail());
                req.session().attribute("Loged",true);
            }
            else{
                return "Usuario o contraseña incorrecta, por favor ingresar de nuevo las credenciales";
            }
            return "";
        });
        get("/information", (req, res) -> {
            String resp = URLReader.readURL("https://ec2-3-95-67-168.compute-1.amazonaws.com:8001/information");
            System.out.println(resp);
            return resp;
        });
    }
    /**
     * Metodo encargado de ejecutar el programa de manera local con un puerto predeterminado.
     * @return Puerto asignado por defecto para ejecutar el programa de forma local, que es 5000.
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }
}