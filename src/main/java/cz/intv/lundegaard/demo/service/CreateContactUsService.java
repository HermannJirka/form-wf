package cz.intv.lundegaard.demo.service;

import cz.intv.lundegaard.demo.dto.ContactUsDto;
import cz.intv.lundegaard.demo.dto.request.CreateContactUsDto;
import cz.intv.lundegaard.demo.exceptions.NotFoundException;
import cz.intv.lundegaard.demo.model.ContactUs;
import cz.intv.lundegaard.demo.model.RequestType;
import cz.intv.lundegaard.demo.repository.ContactUsRepository;
import cz.intv.lundegaard.demo.repository.RequestTypeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static cz.intv.lundegaard.demo.mapper.ContactUsMapper.MAPPER;

@Service
@Log4j2
public class CreateContactUsService {

    private final ContactUsRepository contactUsRepository;
    private final RequestTypeRepository requestTypeRepository;

    public CreateContactUsService(ContactUsRepository contactUsRepository,
                                  RequestTypeRepository requestTypeRepository) {
        this.contactUsRepository = contactUsRepository;
        this.requestTypeRepository = requestTypeRepository;
    }

    @Transactional
    public ContactUsDto postContactUs(CreateContactUsDto createContactUsDto) {
        RequestType requestType =
                requestTypeRepository.findById(createContactUsDto.getRequestTypeId())
                                     .orElseThrow(() -> new NotFoundException(
                                             "Request type not found!"));
        ContactUs contactUs = MAPPER.toContactUs(createContactUsDto);
        contactUs.setRequestType(requestType);
        ContactUs savedContactUs = contactUsRepository.save(contactUs);
        log.info("Contact us was save");
        return MAPPER.toContactUsDto(savedContactUs);
    }
}
