package com.example.Cuenta.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "Movimiento")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idMovimiento;
    private String tipoMovimiento;
    private Double Monto;
    private String fechaIni;
    private String fechaFin;

    private Integer numeroCuenta;
}
