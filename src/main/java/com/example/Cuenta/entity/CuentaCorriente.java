package com.example.Cuenta.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class CuentaCorriente extends Cuenta {

    //Metodo depositar
    public Double getDepositar(Double cantidad, Double balance) {
        balance += cantidad;
        return balance;
    }

    //Metodo retirar
    public Double getRetirar(Double cantidad, Double balance) {
        balance -= cantidad;
        return balance;
    }

}
