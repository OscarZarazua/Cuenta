package com.example.Cuenta.repository;

import com.example.Cuenta.entity.Movimiento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoRepositoryDao extends CrudRepository<Movimiento, Integer> {

    List<Movimiento> findByIdMovimiento(Integer idMovimiento);
}
