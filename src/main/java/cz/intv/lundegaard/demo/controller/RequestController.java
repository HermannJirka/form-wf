package cz.intv.lundegaard.demo.controller;

import cz.intv.lundegaard.demo.dto.RequestTypeDto;
import cz.intv.lundegaard.demo.dto.request.CreateRequestTypeDto;
import cz.intv.lundegaard.demo.dto.request.UpdateRequestTypeDto;
import cz.intv.lundegaard.demo.service.RequestTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/request-type")
public class RequestController {

    private final RequestTypeService requestTypeService;

    public RequestController(RequestTypeService requestTypeService) {
        this.requestTypeService = requestTypeService;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RequestTypeDto> createRequestType(@Valid @RequestBody
                                                                    CreateRequestTypeDto createRequestTypeDto) {
        RequestTypeDto requestTypeDto = requestTypeService.createRequestType(createRequestTypeDto);
        return ResponseEntity.ok(requestTypeDto);
    }

    @PutMapping(path = "/{requestTypeId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RequestTypeDto> updateRequestType(@PathVariable String requestTypeId,
                                                            @Valid @RequestBody
                                                                    UpdateRequestTypeDto updateRequestTypeDto) {
        RequestTypeDto requestTypeDto =
                requestTypeService.updateRequestType(requestTypeId, updateRequestTypeDto);
        return ResponseEntity.ok(requestTypeDto);
    }

    @GetMapping(path = "/{requestTypeId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RequestTypeDto> getRequestType(
            @PathVariable("requestTypeId") String requestTypeId) {
        RequestTypeDto requestTypeDto = requestTypeService.getRequestType(requestTypeId);
        return ResponseEntity.ok(requestTypeDto);
    }

    @DeleteMapping(path = "/{requestTypeId}")
    public ResponseEntity<RequestTypeDto> deleteRequestType(
            @PathVariable("requestTypeId") String requestTypeId) {
        requestTypeService.deleteRequestType(requestTypeId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<RequestTypeDto>> getAllRequestTypes() {
        List<RequestTypeDto> requestTypeDtos = requestTypeService.getAllRequestTypes();
        return ResponseEntity.ok(requestTypeDtos);
    }
}
