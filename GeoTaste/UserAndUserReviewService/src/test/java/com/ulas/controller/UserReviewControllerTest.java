package com.ulas.controller;

/**import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ulas.controller.contract.UserReviewControllerContract;
import com.ulas.dto.UserReviewDTO;
import com.ulas.enums.EnumRating;
import com.ulas.request.UserReviewRequest;
import com.ulas.request.UserReviewUpdateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,properties = {"RESTAURANT_SERVICE_URL=http://localhost:8086/api/v1/restaurants"})
@ActiveProfiles("test")
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:postgresql://localhost:5432/testdb",
        "spring.datasource.username=postgres",
        "spring.datasource.password=root"
})
 class UserReviewControllerTest {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private UserReviewControllerContract userReviewControllerContract;



    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    }

    @Test
    void shouldSaveUserReview() throws Exception {
        LocalDateTime testDate = LocalDateTime.of(2025, 3, 15, 14, 30);
        UserReviewRequest request = new UserReviewRequest(1L, "2", "Çok güzel", testDate, EnumRating.FIVE);

        UserReviewDTO expectedUserReviewDTO = new UserReviewDTO(1L, 1L, "2", "Çok güzel", testDate, EnumRating.FIVE);


        when(userReviewControllerContract.saveUserReview(any(UserReviewRequest.class))).thenReturn(expectedUserReviewDTO);

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user_reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messages").value("User Review saved successfully."));
    }
    @Test
    void shouldUpdateUserReview() throws Exception {
        Long userReviewId = 1L;
        LocalDateTime testDate = LocalDateTime.of(2025, 3, 15, 14, 30);
        UserReviewUpdateRequest updateRequest = new UserReviewUpdateRequest("Güncellenmiş çok güzel bir yorum", EnumRating.FIVE);


        UserReviewDTO expectedUserReviewDTO = new UserReviewDTO(1L, 1L, "2", "Çok güzel", testDate, EnumRating.FIVE);

        when(userReviewControllerContract.updateUserReview(any(UserReviewUpdateRequest.class), eq(userReviewId))).thenReturn(expectedUserReviewDTO);

        String requestJson = objectMapper.writeValueAsString(updateRequest);

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/user_reviews/" + userReviewId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messages").value("User Review update successfully"));
    }
    @Test
    void shouldDeleteUserReview() throws Exception {
        Long userReviewId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/user_reviews/" + userReviewId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messages").value("User Review deleted successfully."));
    }



}
 **/