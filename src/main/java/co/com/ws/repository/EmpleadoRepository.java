package co.com.ws.repository;

import co.com.ws.entity.Empleado;
import org.springframework.data.repository.CrudRepository;

public interface EmpleadoRepository extends CrudRepository<Empleado, Long> {

    Empleado findByIdEmpleado(long idEmpleado);

}
