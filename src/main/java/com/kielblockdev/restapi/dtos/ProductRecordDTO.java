package com.kielblockdev.restapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

//@NotBlank and @NotNull to validade the data.
//DTOs should be a record.
public record ProductRecordDTO(@NotBlank String name, @NotNull BigDecimal value) {

}
