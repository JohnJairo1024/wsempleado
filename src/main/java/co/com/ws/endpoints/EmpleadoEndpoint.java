package co.com.ws.endpoints;

import co.com.ws.entity.Empleado;
import co.com.ws.service.EmpleadoServiceImpl;
import co.com.ws.util.FechaUtil;
import localhost.empleado_ws.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Endpoint
public class EmpleadoEndpoint {
    private static final String NAMESPACE_URI = "http://localhost/empleado-ws";


    @Autowired
    private EmpleadoServiceImpl empleadoService;

    /**
     * @param request
     * @return
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmpleadoByIdRequest")
    @ResponsePayload
    public GetEmpleadoByIdResponse getEmpleado(@RequestPayload GetEmpleadoByIdRequest request) {
        GetEmpleadoByIdResponse response = new GetEmpleadoByIdResponse();
        EmpleadoInfo empleadoInfo = new EmpleadoInfo();
        BeanUtils.copyProperties(empleadoService.getEmpleadoById(request.getIdEmpleado()), empleadoInfo);
        response.setEmpleadoInfo(empleadoInfo);
        return response;
    }

    /**
     * @return
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllEmpleadosRequest")
    @ResponsePayload
    public GetAllEmpleadosResponse getAllEmpleados() {
        GetAllEmpleadosResponse response = new GetAllEmpleadosResponse();
        List<EmpleadoInfo> empleadoInfos = new ArrayList<>();
        List<Empleado> allEmpleados = empleadoService.getAllEmpleados();
        // Recorremos los empleados
        allEmpleados.forEach(empleado -> {
            EmpleadoInfo empleadoInfo = new EmpleadoInfo();
            empleadoInfo.setIdEmpleado(empleado.getIdEmpleado());
            empleadoInfo.setNombre(empleado.getNombres());
            empleadoInfos.add(empleadoInfo);
        });
        response.getEmpleadoInfo().addAll(empleadoInfos);
        return response;
    }

    /**
     * @param request
     * @return
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "nuevoEmpleadoRequest")
    @ResponsePayload
    public NuevoEmpleadoResponse nuevoEmpleado(@RequestPayload NuevoEmpleadoRequest request) {
        NuevoEmpleadoResponse response = new NuevoEmpleadoResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        Empleado empleado = new Empleado();
        empleado.setNombres(request.getNombres());
        empleado.setApellidos(request.getApellidos());
        empleado.setTipoDocumento(request.getTipoDocumento());
        empleado.setNumeroDocumento(request.getNumeroDocumento());

        //Convertir de XMLGregorianCalendar to Date en Java
        Date fechaNacimiento = FechaUtil.toDate(request.getFechaNacimiento());
        empleado.setFechaNacimiento(fechaNacimiento);

        //Convertir de XMLGregorianCalendar to Date en Java
        Date fechaVinculacion = FechaUtil.toDate(request.getFechaVinculacion());
        empleado.setFechaNacimiento(fechaVinculacion);

        empleado.setCargo(request.getCargo());
        empleado.setSalario(request.getSalario());
        empleadoService.nuevoEmpleado(empleado);
        EmpleadoInfo empleadoInfo = new EmpleadoInfo();
        BeanUtils.copyProperties(empleado, empleadoInfo);
        response.setEmpleadoInfo(empleadoInfo);
        serviceStatus.setStatusCode("SUCCESS");
        serviceStatus.setMessage("Nuevo empleado creado exitosamente...");
        response.setServiceStatus(serviceStatus);
        return response;
    }

    /**
     * @param request
     * @return
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "actualizarEmpleadoRequest")
    @ResponsePayload
    public ActualizarEmpleadoResponse actualizarEmpleado(@RequestPayload ActualizarEmpleadoRequest request) {
        Empleado empleado = new Empleado();
        BeanUtils.copyProperties(request.getEmpleadoInfo(), empleado);
        empleadoService.actualizarEmpleado(empleado);
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode("SUCCESS");
        serviceStatus.setMessage("Empleado actualizado exitosamente...");
        ActualizarEmpleadoResponse response = new ActualizarEmpleadoResponse();
        response.setServiceStatus(serviceStatus);
        return response;
    }

    /**
     * @param request
     * @return
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "eliminarEmpleadoRequest")
    @ResponsePayload
    public EliminarEmpleadoResponse eliminarEmpleado(@RequestPayload EliminarEmpleadoRequest request) {
        Empleado empleado = empleadoService.getEmpleadoById(request.getIdEmpleado());
        ServiceStatus serviceStatus = new ServiceStatus();
        if (empleado == null) {
            serviceStatus.setStatusCode("FAIL");
            serviceStatus.setMessage("Empleado no disponible...");
        } else {
            empleadoService.eliminarEmpleado(empleado);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Empleado eliminado exitosamente...");
        }
        EliminarEmpleadoResponse response = new EliminarEmpleadoResponse();
        response.setServiceStatus(serviceStatus);
        return response;
    }


}
