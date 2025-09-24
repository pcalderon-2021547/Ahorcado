package modelo;

public class Usuario {
  
    private int codigoUsuario;
    private String nombreUsuario;
    private String email;
    private String contra;

    public Usuario(int codigoUsuario, String nombreUsuario, String email, String contra) {
        this.codigoUsuario = codigoUsuario;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.contra = contra;
    }

    public Usuario() {
    }
    

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    @Override
    public String toString() {
        return "Usuario{" + "codigoUsuario=" + codigoUsuario + ", nombreUsuario=" + nombreUsuario + ", email=" + email + ", contra=" + contra + '}';
    }
    
    
}
