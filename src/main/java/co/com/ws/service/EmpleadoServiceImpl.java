package co.com.ws.service;

import co.com.ws.entity.Empleado;

import java.util.List;

public interface EmpleadoServiceImpl {

    List<Empleado> getAllEmpleados();

    Empleado getEmpleadoById(long idEmpleado);

    void nuevoEmpleado(Empleado empleado);

    void actualizarEmpleado(Empleado empleado);

    void eliminarEmpleado(Empleado empleado);
}
