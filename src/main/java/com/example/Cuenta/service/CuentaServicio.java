package com.example.Cuenta.service;

import com.example.Cuenta.entity.*;
import com.example.Cuenta.repository.CuentaRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CuentaServicio {
    @Autowired
    private CuentaRepositoryDao cuentaRepositoryDao;

    @Autowired
    RestTemplate restTemplate = new RestTemplate();

    public String nuevaCuenta(Cuenta cuenta) {
        List<Usuario> usuarios = getUsuarioss();
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(cuenta.getUsuario())) {
                if (cuentaRepositoryDao.findByNumeroCuenta(cuenta.getNumeroCuenta()) == null) {
                    cuentaRepositoryDao.save(cuenta);
                    return "Cuenta creada";
                }else{
                    return "Cuenta ya existente";
                }
            }
        }return "No existe el usuario";
    }

    public List<Cuenta> getIdCuenta(Integer idCuenta) {
        return cuentaRepositoryDao.findByIdCuenta(idCuenta);
    }

    public List<Cuenta> getCuentaUsuario(String usuario) {
        return cuentaRepositoryDao.findByUsuario(usuario);
    }

    public Double getCantidadFinalDeposito(Double cantidad, Integer numeroCuenta) {
        Cuenta cuenta = cuentaRepositoryDao.findByNumeroCuenta(numeroCuenta);
        if (cuenta.getTipoCuenta().equalsIgnoreCase("Cuenta Corriente")) {
            CuentaCorriente cuentaCorriente = new CuentaCorriente();
            cuenta.setBalance(cuentaCorriente.getDepositar(cantidad, cuenta.getBalance()));
            cuentaRepositoryDao.save(cuenta);
            return cuenta.getBalance();
        } else {
            CajaAhorro cajaAhorro = new CajaAhorro();
            cuenta.setBalance(cajaAhorro.getDepositar(cantidad, cuenta.getBalance()));
            cuentaRepositoryDao.save(cuenta);
            return cuenta.getBalance();
        }
    }

    public Double getCantidadFinalRetiro(Double cantidad, Integer numeroCuenta) {
        Cuenta cuenta = cuentaRepositoryDao.findByNumeroCuenta(numeroCuenta);
        if (cuenta.getTipoCuenta().equalsIgnoreCase("Cuenta Corriente")) {
            CuentaCorriente cuentaCorriente = new CuentaCorriente();
            cuenta.setBalance(cuentaCorriente.getRetirar(cantidad, cuenta.getBalance()));
            cuentaRepositoryDao.save(cuenta);
            return cuenta.getBalance();
        } else {
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

    public List<Usuario> getUsuarioss() {
        ResponseEntity<Usuario[]> usuarioResponseEntity = restTemplate.getForEntity("http://localhost:8080/api/auth/", Usuario[].class);
        Usuario[] usuario = usuarioResponseEntity.getBody();
        List<Usuario> usuarios = Arrays.asList(usuario);
        return usuarios;
    }
}
