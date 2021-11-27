package com.example.Cuenta.service;

import com.example.Cuenta.entity.CajaAhorro;
import com.example.Cuenta.entity.Cuenta;
import com.example.Cuenta.entity.CuentaCorriente;
import com.example.Cuenta.entity.Movimiento;
import com.example.Cuenta.repository.CuentaRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaServicio {
    @Autowired
    private CuentaRepositoryDao cuentaRepositoryDao;

    public String nuevaCuenta(Cuenta cuenta) {
        if(cuentaRepositoryDao.findByNumeroCuenta(cuenta.getNumeroCuenta()) == null){
            cuentaRepositoryDao.save(cuenta);
            return "Cuenta creada";
        }
        return "Cuenta ya existente";
    }

    public List<Cuenta> getIdCuenta(Integer idCuenta) {
        return cuentaRepositoryDao.findByIdCuenta(idCuenta);
    }

    public List<Cuenta> getCuentaUsuario(String usuario) {
        return cuentaRepositoryDao.findByUsuario(usuario);
    }

    public Double getCantidadFinalDeposito(Double cantidad, Integer numeroCuenta) {
        Cuenta cuenta = cuentaRepositoryDao.findByNumeroCuenta(numeroCuenta);
        if (cuenta.getTipoCuenta().equalsIgnoreCase("Cuenta Corriente")){
            CuentaCorriente cuentaCorriente = new CuentaCorriente();
            cuenta.setBalance(cuentaCorriente.getDepositar(cantidad, cuenta.getBalance()));
            cuentaRepositoryDao.save(cuenta);
            return cuenta.getBalance();
        }
        else {
            CajaAhorro cajaAhorro = new CajaAhorro();
            cuenta.setBalance(cajaAhorro.getDepositar(cantidad, cuenta.getBalance()));
            cuentaRepositoryDao.save(cuenta);
            return cuenta.getBalance();
        }
    }

    public Double getCantidadFinalRetiro(Double cantidad, Integer numeroCuenta) {
        Cuenta cuenta = cuentaRepositoryDao.findByNumeroCuenta(numeroCuenta);
        if (cuenta.getTipoCuenta().equalsIgnoreCase("Cuenta Corriente")){
            CuentaCorriente cuentaCorriente = new CuentaCorriente();
            cuenta.setBalance(cuentaCorriente.getRetirar(cantidad, cuenta.getBalance()));
            cuentaRepositoryDao.save(cuenta);
            return cuenta.getBalance();
        }
        else {
            CajaAhorro cajaAhorro = new CajaAhorro();
            cuenta.setBalance(cajaAhorro.getRetirar(cantidad, cuenta.getBalance()));
            cuentaRepositoryDao.save(cuenta);
            return cuenta.getBalance();
        }
    }

    public void eliminarCuenta(Integer idCuenta) {
        cuentaRepositoryDao.deleteById(idCuenta);
    }


    public Cuenta getNumeroCuenta(Integer numeroCuenta) {
        return cuentaRepositoryDao.findByNumeroCuenta(numeroCuenta);
    }

    public List<Cuenta> getCuentas() {
        return (List<Cuenta>) cuentaRepositoryDao.findAll();
    }
}
