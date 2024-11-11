package fr.hb.icicafaitduspringavecboot.controller;

import jakarta.servlet.ServletException;
import jdk.jfr.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SecurityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void testLoginSuccess() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getLoginJsonFromData("noah.meyer@gmail.com", "12345"))
        );
        resultActions.andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.token").exists()).andExpect(jsonPath("$.*",hasSize(1)));
    }

    @Test
    @Order(2)
    public void testLoginFail() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getLoginJsonFromData("noah.meyer@gmail.com", "1234"))
        );
        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    @Order(3)
    public void testLoginValidFail() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getLoginJsonFromData("noah.meyer@gmail.com", ""))
        );
        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    @Order(4)
    public void testRegisterSuccess() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getRegisterJsonFromData("Bob", "Bob", "bob.bob@bob.com", "12345", "1942-05-05"))
        );
        resultActions.andExpect(status().is2xxSuccessful());
    }

    @Test
    @Order(5)
    public void testRegisterUniqueFail() throws Exception {
        assertThrows(ServletException.class, () -> mockMvc.perform(post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getRegisterJsonFromData("Bob", "Bob", "bob.bob@bob.com", "12345", "1942-05-05")))
        );
    }

    @Test
    @Order(6)
    public void testRegisterValidFail() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getRegisterJsonFromData("Bob", "Bob", "bob.bobbob.com", "12345", "1942-05-05"))
        );
        resultActions.andExpect(status().is4xxClientError());
    }

    private String getRegisterJsonFromData(String firstName, String lastName, String email, String password, String birthDate) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("firstName", firstName);
        object.put("lastName", lastName);
        object.put("email", email);
        object.put("password", password);
        object.put("birthDate", birthDate);
        return object.toString();
    }


    private String getLoginJsonFromData(String email, String pwd) {
        return String.format("""
                {
                    "username" : "%s",
                    "password" : "%s"
                }
                """, email, pwd);
    }


}
