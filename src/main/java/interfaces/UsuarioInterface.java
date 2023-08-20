package interfaces;

import java.util.List;
import entidades.Empleado;

public interface UsuarioInterface {
  
  public List<Empleado> obtenerEmpleados();
  
  public int crearUsuario(Empleado usuario, String plainPsw);
}
