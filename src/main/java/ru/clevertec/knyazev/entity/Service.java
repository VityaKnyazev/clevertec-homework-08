package ru.clevertec.knyazev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Service {

    private UUID id;

    private Person person;

    private String name;

    private String description;

    private BigDecimal price;
}
