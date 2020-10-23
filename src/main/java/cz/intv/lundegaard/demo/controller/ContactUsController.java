package cz.intv.lundegaard.demo.controller;

import cz.intv.lundegaard.demo.dto.ContactUsDto;
import cz.intv.lundegaard.demo.dto.request.CreateContactUsDto;
import cz.intv.lundegaard.demo.service.CreateContactUsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/contact-us")
public class ContactUsController {

    private final CreateContactUsService createContactUsService;

    public ContactUsController(CreateContactUsService createContactUsService) {
        this.createContactUsService = createContactUsService;
    }

    @PostMapping
    public ResponseEntity<ContactUsDto> postContactUs(
            @RequestBody @Valid CreateContactUsDto createContactUsDto) {
        ContactUsDto contactUsDto = createContactUsService.postContactUs(createContactUsDto);
        return ResponseEntity.ok(contactUsDto);
    }
}
