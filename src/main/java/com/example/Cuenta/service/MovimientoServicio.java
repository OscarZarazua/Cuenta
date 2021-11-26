package com.example.Cuenta.service;

import com.example.Cuenta.entity.Movimiento;
import com.example.Cuenta.repository.MovimientoRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoServicio {
    @Autowired
    private MovimientoRepositoryDao movimientoRepositoryDao;

    public void nuevoMovimiento(Movimiento movimiento) {
            movimientoRepositoryDao.save(movimiento);
    }

    public List<Movimiento> getIdMovimiento(Integer idMovimiento) {
        return movimientoRepositoryDao.findByIdMovimiento(idMovimiento);
    }
}
