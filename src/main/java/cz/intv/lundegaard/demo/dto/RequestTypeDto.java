package cz.intv.lundegaard.demo.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RequestTypeDto {
    String id;
    String name;
    String description;
}
