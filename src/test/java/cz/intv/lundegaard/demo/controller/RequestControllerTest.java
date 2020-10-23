package cz.intv.lundegaard.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.intv.lundegaard.demo.dto.request.CreateRequestTypeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SqlGroup({
                  @Sql(scripts = {"/sql/init/clean-up.sql", "/sql/init/init.sql"},
                       executionPhase = BEFORE_TEST_METHOD),
                  @Sql(scripts = {"/sql/init/clean-up.sql"}, executionPhase = AFTER_TEST_METHOD)
          })
@SpringBootTest
@AutoConfigureMockMvc
class RequestControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void createRequestType() throws Exception {

        CreateRequestTypeDto requestTypeDto = CreateRequestTypeDto.builder()
                                                                  .name("request type damage")
                                                                  .description("request type " +
                                                                          "damage" +
                                                                          " desc")
                                                                  .build();

        mvc.perform(post("/request-type")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(requestTypeDto))
                .accept(MediaType.APPLICATION_JSON))
           .andDo(print())
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.name").value("request type damage"))
           .andExpect(jsonPath("$.description").value("request type damage desc"));
    }

    @Test
    void createRequestTypeInvalidInput() throws Exception {

        CreateRequestTypeDto requestTypeDto = CreateRequestTypeDto.builder()
                                                                  .name("requestType4")
                                                                  .description("requestType4 desc")
                                                                  .build();

        mvc.perform(post("/request-type")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(requestTypeDto))
                .accept(MediaType.APPLICATION_JSON))
           .andDo(print())
           .andExpect(status().isBadRequest());
    }

    @Test
    void updateRequestType() throws Exception {
        CreateRequestTypeDto requestTypeDto = CreateRequestTypeDto.builder()
                                                                  .name("updatedRequest1")
                                                                  .description(
                                                                          "updatedRequest1 desc")
                                                                  .build();

        mvc.perform(put("/request-type/reqT1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(requestTypeDto))
                .accept(MediaType.APPLICATION_JSON))
           .andDo(print())
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.name").value("updatedRequest1"))
           .andExpect(jsonPath("$.description").value("updatedRequest1 desc"));
    }

    @Test
    void updateRequestTypeNotFound() throws Exception {
        CreateRequestTypeDto requestTypeDto = CreateRequestTypeDto.builder()
                                                                  .name("updatedRequest1")
                                                                  .description(
                                                                          "updatedRequest1 desc")
                                                                  .build();

        mvc.perform(put("/request-type/reqT500")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(requestTypeDto))
                .accept(MediaType.APPLICATION_JSON))
           .andDo(print())
           .andExpect(status().isNotFound());
    }

    @Test
    void getRequestType() throws Exception {
        mvc.perform(get("/request-type/reqT2"))
           .andDo(print())
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.name").value("Contract adjustment"))
           .andExpect(jsonPath("$.description").value("Contract adjustment desc"));
    }

    @Test
    void getRequestTypeNotFound() throws Exception {
        mvc.perform(get("/request-type/reqT500"))
           .andDo(print())
           .andExpect(status().isNotFound());
    }

    @Test
    void deleteRequestType() throws Exception {
        mvc.perform(delete("/request-type/reqT2"))
           .andDo(print())
           .andExpect(status().isOk());

        mvc.perform(get("/request-type/reqT2"))
           .andDo(print())
           .andExpect(status().isNotFound());
    }

    @Test
    void deleteRequestTypeNotFound() throws Exception {
        mvc.perform(delete("/request-type/reqT500"))
           .andDo(print())
           .andExpect(status().isNotFound());
    }

    @Test
    void getAllRequestTypes() throws Exception {
        mvc.perform(get("/request-type"))
           .andDo(print())
           .andExpect(status().isOk())
           .andExpect(jsonPath("$", hasSize(3)));
    }
}