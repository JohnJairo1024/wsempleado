package co.com.ws.service;

import co.com.ws.entity.Empleado;
import co.com.ws.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpleadoService implements EmpleadoServiceImpl {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> getAllEmpleados() {
        List<Empleado> list = new ArrayList<>();
        empleadoRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    public Empleado getEmpleadoById(long idEmpleado) {
        Empleado empleado = empleadoRepository.findByIdEmpleado(idEmpleado);
        return empleado;
    }

    @Override
    public void nuevoEmpleado(Empleado empleado) {
        empleadoRepository.save(empleado);
    }

    @Override
    public void actualizarEmpleado(Empleado empleado) {
        empleadoRepository.save(empleado);
    }

    @Override
    public void eliminarEmpleado(Empleado empleado) {
        empleadoRepository.delete(empleado);
    }
}
