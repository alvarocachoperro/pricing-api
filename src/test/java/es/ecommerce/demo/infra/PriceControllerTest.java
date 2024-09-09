package es.ecommerce.demo.infra;

import es.ecommerce.demo.app.service.exception.IErrorMessages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
 class PriceControllerTest {

    public static final String API_URL = "/product-price";
    @Autowired
    private MockMvc mockMvc;

    @Test
     void testGetProductPrice_ReturnsInitialPriceForMorningOnJune14() throws Exception {
        mockMvc.perform(get(API_URL)
                .param("date", "2020-06-14T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.value").value(35.60));
    }

    @Test
     void testGetProductPrice_ReturnsDiscountedPriceForAfternoonOnJune14() throws Exception {
        mockMvc.perform(get(API_URL)
                .param("date", "2020-06-14T16:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.value").value(25.45));
    }

    @Test
     void testGetProductPrice_ReturnsInitialPriceForEveningOnJune14() throws Exception {
        mockMvc.perform(get(API_URL)
                .param("date", "2020-06-14T21:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.value").value(35.50));
    }

    @Test
     void testGetProductPrice_ReturnsNewPriceForMorningOnJune15() throws Exception {
        mockMvc.perform(get(API_URL)
                .param("date", "2020-06-15T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(3))
                .andExpect(jsonPath("$.value").value(30.50));
    }

    @Test
     void testGetProductPrice_ReturnsLatestPriceForNightOnJune16() throws Exception {
        mockMvc.perform(get(API_URL)
                .param("date", "2020-06-16T21:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.value").value(38.95));
    }

    @Test
    void testGetProductPrice_whenParameterTypeMismatched_thenReturnsBadRequest() throws Exception {
        mockMvc.perform(get(API_URL)
                        .param("date", "35455")
                        .param("productId", "2024-06-16T21:00:00")
                        .param("brandId", "2024-06-16T21:00:00"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Error:Parse attempt failed for value [35455]"));
    }


    @Test
    void testGetProductPrice_whenAskedForPriceNotExisting_thenReturnsNotFound() throws Exception {
        mockMvc.perform(get(API_URL)
                        .param("date", "2024-06-16T21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value(IErrorMessages.ERROR_NOTFOUND));
    }

    @Test
    void givenMissingParameter_whenGetProductPrice_thenReturnsBadRequest() throws Exception {
        mockMvc.perform(get(API_URL)
                        .param("date", "2020-06-14T10:00:00")
                        // Missing productId parameter
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Parameter 'productId' is missing"));
    }

}
