package com.example.Cuenta.controller;

import com.example.Cuenta.config.NonExistentException;
import com.example.Cuenta.entity.Cuenta;
import com.example.Cuenta.entity.Movimiento;
import com.example.Cuenta.service.CuentaServicio;
import com.example.Cuenta.service.MovimientoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimiento")
public class MovimientoController {

    @Autowired
    private MovimientoServicio movimientoServicio;
    @Autowired
    private CuentaServicio cuentaServicio;

    @PostMapping("/nuevoMovimiento")
    public ResponseEntity<String> nuevoMovimiento(@RequestBody Movimiento movimiento) {
        if (cuentaServicio.getNumeroCuenta(movimiento.getNumeroCuenta()) == null) {
            return ResponseEntity.ok("El numero de cuenta no existe");
        } else {
            if (movimientoServicio.getIdMovimiento(movimiento.getIdMovimiento()).isEmpty()) {
                movimientoServicio.nuevoMovimiento(movimiento);
                return ResponseEntity.ok("Lista de Movimientos " + movimiento.toString());
            } else {
                return ResponseEntity.ok("Movimiento ya existente");
            }
        }
    }

    @GetMapping("/buscarPorIdMovimiento/{idMovimiento}")
    public List<Movimiento> getidMovimiento(@PathVariable Integer idMovimiento) {
        List<Movimiento> movimientos = movimientoServicio.getIdMovimiento(idMovimiento);
        return movimientos;
    }
}
