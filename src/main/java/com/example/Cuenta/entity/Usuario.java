package com.example.Cuenta.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private Long idusuario;
    private String nombre;
    private String apellidoPat;
    private String apellidoMat;
    private Integer edad;
    private String email;
    private String telefono;
    private String username;
    private String password;
}
