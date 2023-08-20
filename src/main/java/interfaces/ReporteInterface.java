package interfaces;

import java.util.List;
import entidades.ComprobanteDePago;

public interface ReporteInterface {
  
  List<ComprobanteDePago> obtenerComprobantes(String fechaInicio, String fechaFin);

}
