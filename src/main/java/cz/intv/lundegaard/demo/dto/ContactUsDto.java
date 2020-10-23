package cz.intv.lundegaard.demo.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ContactUsDto {
    String id;
    String name;
    String surname;
    String policyNumber;
    String request;
    String requestTypeId;
    String requestTypeName;
}
