package com.kielblockdev.restapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientRecordDTO(@NotBlank String name, @NotNull String phone) {
}
