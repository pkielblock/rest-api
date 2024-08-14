package com.kielblockdev.restapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "TB_PRODUCTS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/*
* Serializable converts its current state into a default pattern, then provides it into a byte stream
* that can be written in disk or transmitted.
* */
public class ProductModel implements Serializable {
    //@Serial necessary to tell if it is compatible with the .class to be deserialized.
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProduct;
    private String name;
    private BigDecimal value;
}
