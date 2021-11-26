package com.example.Cuenta.controller;

import com.example.Cuenta.config.NonExistentException;
import com.example.Cuenta.entity.Cuenta;
import com.example.Cuenta.entity.Movimiento;
import com.example.Cuenta.service.CuentaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cuenta")
public class CuentaController {
    @Autowired
    private CuentaServicio cuentaServicio;

    @GetMapping("/buscarPorId/{idCuenta}")
    public List<Cuenta> getidCuenta(@PathVariable Integer idCuenta) throws NonExistentException {
        List<Cuenta> cuentas = cuentaServicio.getIdCuenta(idCuenta);
        if (cuentas.isEmpty()) {
            throw new NonExistentException();
        }
        return cuentas;
    }



    @GetMapping("/buscarPorUsuario/{usuario}")
    public List<Cuenta> getCuentaUsuario(@PathVariable String usuario) throws NonExistentException {
        List<Cuenta> cuentas = cuentaServicio.getCuentaUsuario(usuario);
        if (cuentas.isEmpty()) {
            throw new NonExistentException();
        }
        return cuentas;
    }

    @PostMapping("/nuevaCuenta")
    public ResponseEntity<String> nuevaCuenta(@RequestBody Cuenta cuenta) {
        return ResponseEntity.ok(cuentaServicio.nuevaCuenta(cuenta));
    }



    @PutMapping("/depositar/{numeroCuenta}/{cantidad}")
    public ResponseEntity<String> getDepositar(@PathVariable Double cantidad, @PathVariable Integer numeroCuenta) {
        double cantidadFinal = cuentaServicio.getCantidadFinalDeposito(cantidad, numeroCuenta);
        String mensaje = "Su saldo actual es " + cantidadFinal;
        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/retirar/{numeroCuenta}/{cantidad}")
    public ResponseEntity<String> getRetirar(@PathVariable Double cantidad, @PathVariable Integer numeroCuenta) {
        double cantidadFinal = cuentaServicio.getCantidadFinalRetiro(cantidad, numeroCuenta);
        String mensaje = "Su saldo actual es " + cantidadFinal;
        return ResponseEntity.ok(mensaje);
    }

    @DeleteMapping("/eliminarCuenta/{idCuenta}")
    public ResponseEntity<String> eliminarCuenta(@PathVariable ("idCuenta") Integer idCuenta){
        cuentaServicio.eliminarCuenta(idCuenta);
        return ResponseEntity.ok("Se elimino la cuenta correctamente");
    }

}

