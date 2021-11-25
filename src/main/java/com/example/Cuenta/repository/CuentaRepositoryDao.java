package com.example.Cuenta.repository;

import com.example.Cuenta.entity.Cuenta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CuentaRepositoryDao extends CrudRepository<Cuenta, Integer> {

    List<Cuenta> findByIdCuenta(Integer idCuenta);

    List<Cuenta> findByUsuario(String cuentaUsuario);

    Cuenta findByNumeroCuenta(Integer numeroCuenta);
}
