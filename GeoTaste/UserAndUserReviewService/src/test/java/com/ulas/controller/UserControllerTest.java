package com.ulas.controller;

/**import com.fasterxml.jackson.databind.ObjectMapper;
 import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
 import com.ulas.client.RestaurantServiceClient;
 import com.ulas.controller.contract.UserControllerContract;
 import com.ulas.dto.RestaurantDTO;
 import com.ulas.dto.UserDTO;
 import com.ulas.general.KafkaProducerService;
 import com.ulas.request.UserSaveRequest;
 import com.ulas.request.UserUpdateRequest;
 import com.ulas.service.UserService;
 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.Test;
 import org.junit.jupiter.api.extension.ExtendWith;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
 import org.springframework.boot.test.context.SpringBootTest;
 import org.springframework.boot.test.mock.mockito.MockBean;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.MediaType;
 import org.springframework.test.context.ActiveProfiles;
 import org.springframework.test.context.TestPropertySource;
 import org.springframework.test.context.junit.jupiter.SpringExtension;
 import org.springframework.test.web.servlet.MockMvc;
 import org.springframework.test.web.servlet.MvcResult;
 import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
 import org.springframework.test.web.servlet.setup.MockMvcBuilders;
 import org.springframework.web.context.WebApplicationContext;

 import java.time.LocalDate;
 import java.util.Arrays;
 import java.util.List;

 import static org.mockito.ArgumentMatchers.any;
 import static org.mockito.ArgumentMatchers.anyLong;
 import static org.mockito.Mockito.when;
 import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
 import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
 import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 **/


/**@ExtendWith(SpringExtension.class)

 @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 @ActiveProfiles("test")
 @TestPropertySource(properties = {
 "spring.datasource.url=jdbc:postgresql://localhost:5432/testdb",
 "spring.datasource.username=postgres",
 "spring.datasource.password=root"
 })
 public class UserControllerTest {
 @Autowired
 private WebApplicationContext context;
 @MockBean
 private UserService userService;
 @MockBean
 private KafkaProducerService kafkaProducerService;
 @MockBean
 private RestaurantServiceClient restaurantServiceClient;
 @MockBean
 private UserControllerContract userControllerContract;

 private MockMvc mockMvc;
 private ObjectMapper objectMapper;

 @BeforeEach
 void setUp() {
 mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
 objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
 when(userControllerContract.delete(anyLong())).thenReturn(new UserDTO(
 1L, "John", "Doe", LocalDate.of(1990, 1, 1), "john.doe@example.com", "password123", 1L, 40.712776, -74.005974
 ));
 }
 @Test
 void shouldSaveUser() throws Exception {
 UserSaveRequest saveRequest = new UserSaveRequest(
 "John", "Doe", LocalDate.of(1990, 1, 1), "password123", "john.doe@example.com", 40.712776, -74.005974, 1L
 );

 String requestAsString = objectMapper.writeValueAsString(saveRequest);

 mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
 .content(requestAsString)
 .contentType(MediaType.APPLICATION_JSON))
 .andExpect(status().isOk());
 }
 private boolean isSuccess(MvcResult mvcResult) throws Exception {
 return mvcResult.getResponse().getStatus() == HttpStatus.OK.value();
 }
 @Test
 void shouldDeleteUser() throws Exception {
 Long userId = 1L;
 when(userControllerContract.delete(userId)).thenReturn(new UserDTO(
 1L, "John", "Doe", LocalDate.of(1990, 1, 1), "john.doe@example.com", "password123", 1L, 40.712776, -74.005974
 ));

 mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/" + userId))
 .andExpect(status().isOk())
 .andExpect(jsonPath("$.messages").value("User deleted successfully."))
 .andDo(print());
 }
 @Test
 void shouldUpdateUser() throws Exception {
 UserUpdateRequest updateRequest = new UserUpdateRequest(
 1L, "Updated Name", "Updated Surname", LocalDate.of(1991, 1, 1), "updatedPassword123", 40.7128, -74.0060, "updated.email@example.com", 2L
 );

 when(userControllerContract.updateUser(any(UserUpdateRequest.class))).thenReturn(new UserDTO(
 1L, "Updated Name", "Updated Surname", LocalDate.of(1991, 1, 1), "updated.email@example.com", "updatedPassword123", 2L, 40.7128, -74.0060
 ));


 String requestJson = objectMapper.writeValueAsString(updateRequest);

 mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/update_user")
 .contentType(MediaType.APPLICATION_JSON)
 .content(requestJson))
 .andExpect(status().isOk())
 .andExpect(jsonPath("$.messages").value("User update successfully"));
 }

 @Test
 void shouldGetRestaurantRecommendations() throws Exception {
 Long userId = 1L;
 List<RestaurantDTO> recommendations = Arrays.asList(
 new RestaurantDTO("Restaurant1","Kukla kebap",74.982,22.123,123,4.0),
 new RestaurantDTO("Restaurant2", "mezzaluna", 74.008,12.213,241,2.0)
 );

 when(userControllerContract.getRestaurantRecommendations(userId)).thenReturn(recommendations);

 mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/" + userId + "/restaurant-recommendations"))
 .andExpect(status().isOk())
 .andExpect(jsonPath("$.messages").value("Restaurant recommendations retrieved successfully."))
 .andExpect(jsonPath("$.data[0].name").value("Kukla kebap"))
 .andExpect(jsonPath("$.data[1].name").value("mezzaluna"));
 }
 }
 **/


