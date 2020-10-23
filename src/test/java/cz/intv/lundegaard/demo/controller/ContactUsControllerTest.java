package cz.intv.lundegaard.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.intv.lundegaard.demo.dto.request.CreateContactUsDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
class ContactUsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void postContactUs() throws Exception {
        CreateContactUsDto requestTypeDto = CreateContactUsDto.builder()
                                                              .name("Jiri")
                                                              .surname("Hermann")
                                                              .policyNumber("abcd1234")
                                                              .request("This is my new request")
                                                              .requestTypeId("reqT1")
                                                              .build();

        mvc.perform(post("/contact-us")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(requestTypeDto))
                .accept(MediaType.APPLICATION_JSON))
           .andDo(print())
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.name").value("Jiri"))
           .andExpect(jsonPath("$.surname").value("Hermann"))
           .andExpect(jsonPath("$.policyNumber").value("abcd1234"))
           .andExpect(jsonPath("$.request").value("This is my new request"))
           .andExpect(jsonPath("$.requestTypeId").value("reqT1"))
           .andExpect(jsonPath("$.requestTypeName").value("complaint"));
    }
}