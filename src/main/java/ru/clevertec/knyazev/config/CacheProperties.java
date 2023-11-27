package ru.clevertec.knyazev.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CacheProperties {

    private String algorithm;

    private Integer size;
}
