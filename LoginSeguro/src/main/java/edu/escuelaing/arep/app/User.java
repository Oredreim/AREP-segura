package edu.escuelaing.arep.app;

public class User {
    private String email;
    private String password;
    /**
     * Metodo encargado de obtener las credenciales del usuario en cuestion.
     * @param email Correo electronico del usuario correspondiente.
     * @param password Clave del usuario correspondiente.
     */
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    /**
     * Metodo encargado de obtener el correo electronico del usuario correspondiente.
     * @return Retorna el correo electronico del usuario correspondiente.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Metodo encargado de establecer el correo electronico del usuario correspondiente.
     * @param email Correo electronico del ususario correspondiente.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Metodo encargado de obtener la clave del usuario correspondiente.
     * @return Retorna la clave del usuario correspondiente.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Metodo encargado de establecer la clave del usuario correspondiente.
     * @param password Parametro que establece la clave del usuario correspondiente.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}