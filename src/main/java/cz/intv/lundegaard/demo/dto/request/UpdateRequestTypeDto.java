package cz.intv.lundegaard.demo.dto.request;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Value
@Builder
public class UpdateRequestTypeDto {
    @NotBlank
    @Size(min = 1, max = 50)
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Invalid Input")
    String name;
    @NotBlank
    @Size(min = 1, max = 1000)
    String description;
}
