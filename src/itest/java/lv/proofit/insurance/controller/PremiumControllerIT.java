package lv.proofit.insurance.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PremiumControllerIT {
    
    private static String POLICY = "{\"number\":\"12-12\",\"status\":\"APPROVED\",\"insuranceObjects\":[{\"name\":\"Insurance 0\",\"insuranceSubObjects\":[{\"name\":\"sub1\",\"sum\":100.0,\"risk\":\"FIRE\"},{\"name\":\"sub2\",\"sum\":8.0,\"risk\":\"THEFT\"}]}]}";
    

    @Autowired
    private MockMvc                          mvc;
    
    @Test
    public void test() throws Exception {
        assertTrue(true);
        
        MvcResult mvcResult = this.mvc.perform(post("/calculatePremium")
                .content(POLICY)
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();
        
        log.debug("Server responce is -> {}", mvcResult.getResponse().getContentAsString());
    }

}
