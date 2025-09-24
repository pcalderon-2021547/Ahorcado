package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author informatica
 */
public class UsuarioDAO {
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    
     public Usuario validar(String email, String contra) {
        Usuario usuario = new Usuario();
        String sql = "SELECT * FROM Usuario WHERE email = ? AND contra = ?";
        try {
            con = cn.Conexion();    
            ps = con.prepareCall(sql);
            ps.setString(1, email);
            ps.setString(2, contra);
            rs = ps.executeQuery();
            while (rs.next()) {
                usuario.setNombreUsuario("nombreUsuario");
                usuario.setEmail("email");
                usuario.setContra("contra");
            }
        } catch (SQLException e) {
            System.out.println("El usuario o contraseña son incorrectos");
        }
        return usuario;
}
}
