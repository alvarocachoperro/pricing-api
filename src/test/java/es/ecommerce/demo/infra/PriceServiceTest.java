package es.ecommerce.demo.infra;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import es.ecommerce.demo.app.repository.PriceRepository;
import es.ecommerce.demo.app.service.exception.PriceNotFoundException;
import es.ecommerce.demo.app.service.impl.PriceServiceImpl;
import es.ecommerce.demo.domain.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

 class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceServiceImpl priceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testGetPrice_Test1() {
        // Arrange
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        int productId = 35455;
        int brandId = 1;

        Price mockPrice = createMockPrice(1, applicationDate.minusDays(1), applicationDate.plusDays(200), 1, productId, 0, new BigDecimal("35.50"));
        when(priceRepository.findByDateProductAndBrand(applicationDate, productId, brandId)).thenReturn(Arrays.asList(mockPrice));

        // Act
        Price result = null;
        try {
            result = priceService.getPrice(applicationDate, (long)productId, (long)brandId);
        } catch (PriceNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Assert
        assertNotNull(result);
        assertEquals(35455, result.getProductId());
        assertEquals(1, result.getBrandId());
        assertEquals(1, result.getPriceList());
        assertEquals(new BigDecimal("35.50"), result.getValue());
    }

    @Test
     void testGetPrice_Test2() {
        // Arrange
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        int productId = 35455;
        int brandId = 1;

        Price mockPrice = createMockPrice(1, applicationDate.minusHours(1), applicationDate.plusHours(2), 2, productId, 1, new BigDecimal("25.45"));
        when(priceRepository.findByDateProductAndBrand(applicationDate, productId, brandId)).thenReturn(Arrays.asList(mockPrice));

        // Act
       Price result = null;
       try {
          result = priceService.getPrice(applicationDate, (long)productId, (long)brandId);
       } catch (PriceNotFoundException e) {
          throw new RuntimeException(e);
       }

        // Assert
        assertNotNull(result);
        assertEquals(35455, result.getProductId());
        assertEquals(1, result.getBrandId());
        assertEquals(2, result.getPriceList());
        assertEquals(new BigDecimal("25.45"), result.getValue());
    }

    @Test
     void testGetPrice_Test3() {
        // Arrange
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        int productId = 35455;
        int brandId = 1;

        Price mockPrice = createMockPrice(1, applicationDate.minusHours(10), applicationDate.plusHours(10), 1, productId, 0, new BigDecimal("35.50"));
        when(priceRepository.findByDateProductAndBrand(applicationDate, productId, brandId)).thenReturn(Arrays.asList(mockPrice));

        // Act
       Price result = null;
       try {
          result = priceService.getPrice(applicationDate, (long)productId, (long)brandId);
       } catch (PriceNotFoundException e) {
          throw new RuntimeException(e);
       }

        // Assert
        assertNotNull(result);
        assertEquals(35455, result.getProductId());
        assertEquals(1, result.getBrandId());
        assertEquals(1, result.getPriceList());
        assertEquals(new BigDecimal("35.50"), result.getValue());
    }

    @Test
     void testGetPrice_Test4() {
        // Arrange
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        int productId = 35455;
        int brandId = 1;

        Price mockPrice = createMockPrice(1, applicationDate.minusHours(5), applicationDate.plusHours(5), 3, productId, 1, new BigDecimal("30.50"));
        when(priceRepository.findByDateProductAndBrand(applicationDate, productId, brandId)).thenReturn(Arrays.asList(mockPrice));

        // Act
       Price result = null;
       try {
          result = priceService.getPrice(applicationDate, (long)productId, (long)brandId);
       } catch (PriceNotFoundException e) {
          throw new RuntimeException(e);
       }

        // Assert
        assertNotNull(result);
        assertEquals(35455, result.getProductId());
        assertEquals(1, result.getBrandId());
        assertEquals(3, result.getPriceList());
        assertEquals(new BigDecimal("30.50"), result.getValue());
    }

    @Test
     void testgetValue_Test5() {
        // Arrange
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        int productId = 35455;
        int brandId = 1;

        Price mockPrice = createMockPrice(1, applicationDate.minusDays(1), applicationDate.plusDays(1), 4, productId, 1, new BigDecimal("38.95"));
        when(priceRepository.findByDateProductAndBrand(applicationDate, productId, brandId)).thenReturn(Arrays.asList(mockPrice));

        // Act
       Price result = null;
       try {
          result = priceService.getPrice(applicationDate, (long)productId, (long)brandId);
       } catch (PriceNotFoundException e) {
          throw new RuntimeException(e);
       }

        // Assert
        assertNotNull(result);
        assertEquals(35455, result.getProductId());
        assertEquals(1, result.getBrandId());
        assertEquals(4, result.getPriceList());
        assertEquals(new BigDecimal("38.95"), result.getValue());
    }

    @Test
    void testgetValue_Test6() {
       // Arrange
       LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
       int productId = 35455;
       int brandId = 1;

       // Act
       PriceNotFoundException e = assertThrows(PriceNotFoundException.class, () -> priceService.getPrice(applicationDate, (long)productId, (long)brandId));

       // Assert
       assertEquals("error.notFound", e.getMessage());
    }

    private Price createMockPrice(int brandId, LocalDateTime startDate, LocalDateTime endDate, int priceList, int productId, int priority, BigDecimal price) {
        Price mockPrice = new Price();
        mockPrice.setBrandId((long)brandId);
        mockPrice.setProductId((long)productId);
        mockPrice.setStartDate(startDate);
        mockPrice.setEndDate(endDate);
        mockPrice.setPriceList(priceList);
        mockPrice.setPriority(priority);
        mockPrice.setValue(price);
        mockPrice.setCurrency("EUR");
        return mockPrice;
    }
}
