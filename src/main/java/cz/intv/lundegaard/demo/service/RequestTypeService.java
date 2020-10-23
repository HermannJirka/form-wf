package cz.intv.lundegaard.demo.service;

import cz.intv.lundegaard.demo.dto.RequestTypeDto;
import cz.intv.lundegaard.demo.dto.request.CreateRequestTypeDto;
import cz.intv.lundegaard.demo.dto.request.UpdateRequestTypeDto;
import cz.intv.lundegaard.demo.exceptions.NotFoundException;
import cz.intv.lundegaard.demo.model.RequestType;
import cz.intv.lundegaard.demo.repository.RequestTypeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static cz.intv.lundegaard.demo.mapper.RequestTypeMapper.MAPPER;

@Service
@Log4j2
public class RequestTypeService {

    private final RequestTypeRepository requestTypeRepository;

    public RequestTypeService(RequestTypeRepository requestTypeRepository) {
        this.requestTypeRepository = requestTypeRepository;
    }

    @Transactional
    public RequestTypeDto createRequestType(CreateRequestTypeDto createRequestTypeDto) {
        RequestType requestType = MAPPER.toRequestType(createRequestTypeDto);
        RequestType savedRequestType = requestTypeRepository.save(requestType);
        log.info("Request type was save");
        return MAPPER.toRequestTypeDto(savedRequestType);
    }

    @Transactional
    public RequestTypeDto updateRequestType(String requestTypeId,
                                            UpdateRequestTypeDto updateRequestTypeDto) {
        RequestType requestType = requestTypeRepository.findById(requestTypeId)
                                                       .orElseThrow(() -> new NotFoundException(
                                                               "Request" +
                                                                       " type not found"));
        MAPPER.updateRequestType(requestType, updateRequestTypeDto);
        RequestType updatedRequestType = requestTypeRepository.save(requestType);
        log.info("Request type was update");
        return MAPPER.toRequestTypeDto(updatedRequestType);
    }

    @Transactional(readOnly = true)
    public RequestTypeDto getRequestType(String requestTypeId) {
        RequestType requestType = requestTypeRepository.findById(requestTypeId)
                                                       .orElseThrow(() -> new NotFoundException(
                                                               "Request" +
                                                                       " type not found"));
        log.info("Request type was find");
        return MAPPER.toRequestTypeDto(requestType);
    }

    @Transactional
    public List<RequestTypeDto> getAllRequestTypes() {
        return requestTypeRepository.findAll().stream().map(MAPPER::toRequestTypeDto).collect(
                Collectors.toList());
    }

    @Transactional
    public void deleteRequestType(String requestTypeId) {
        RequestType requestType = requestTypeRepository.findById(requestTypeId)
                                                       .orElseThrow(() -> new NotFoundException(
                                                               "Request" +
                                                                       " type not found"));
        log.info("Request was delete");
        requestTypeRepository.delete(requestType);
    }
}
