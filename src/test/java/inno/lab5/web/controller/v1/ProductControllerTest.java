package inno.lab5.web.controller.v1;

import inno.lab5.AbstractTestController;
import inno.lab5.StringTestUtils;
import inno.lab5.exception.EntityNotFoundException;
import inno.lab5.mapper.ProductMapper;
import inno.lab5.model.Product;
import inno.lab5.model.Register;
import inno.lab5.service.ProductService;
import inno.lab5.web.model.response.RegisterResponse;
import inno.lab5.web.model.response.ProductResponse;
import inno.lab5.web.model.request.ProductRequest;
import net.bytebuddy.utility.RandomString;
import net.javacrumbs.jsonunit.JsonAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest extends AbstractTestController {

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductMapper productMapper;

    @Test
    public void whenFindAll_thenReturnAllProducts() throws Exception{
        List<Product> products = new ArrayList<>();
        products.add(createProduct(1L,null));
        Register register = createProductRegister(1L,null);
        products.add(createProduct(2L, register));

        List<ProductResponse> productResponses = new ArrayList<>();
        productResponses.add(createProductResponse(1L,null));
        RegisterResponse registerResponse = createProductRegisterResponse(1L);

        productResponses.add((createProductResponse(2L, registerResponse)));
        ProductListResponse productListResponse = new ProductListResponse(productResponses);

        Mockito.when(productService.findAll()).thenReturn(products);
        Mockito.when(productMapper.productListToProductResponseList(products)).thenReturn(productListResponse);

        String actualResponse = mockMvc.perform(get("/v1/product"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String expectedResponse = StringTestUtils.readStringFromResource("response/find_all_products_response.json");

        Mockito.verify(productService, Mockito.times(1)).findAll();
        Mockito.verify(productMapper, Mockito.times(1)).productListToProductResponseList(products);

        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);
    }

    @Test
    public void whenGetProductById_thenReturnProductById() throws Exception{
        Product product = createProduct(1L,null);
        ProductResponse productResponse =createProductResponse(1L,null);

        Mockito.when(productService.findById(1L)).thenReturn(product);
        Mockito.when(productMapper.productToResponse(product)).thenReturn(productResponse);

        String actualResponse = mockMvc.perform(get("/v1/product/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String expectedResponse = StringTestUtils.readStringFromResource("response/find_product_by_id_response.json");

        Mockito.verify(productService, Mockito.times(1)).findById(1L);
        Mockito.verify(productMapper, Mockito.times(1)).productToResponse(product);

        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);
    }

    @Test
    public void whenCreateProduct_thenReturnNewProduct() throws Exception{
        Product product = new Product();
        product.setProductCodeId(2L);
        product.setClient(2L);
        product.setType("Type");
        product.setNumber("number");
        product.setPriority(1);
        product.setDays(6);
        product.setPenaltyRate(BigDecimal.valueOf(10.01));
        product.setNso(BigDecimal.valueOf(100.01));
        product.setThresholdAmount(BigDecimal.valueOf(1000.01));
        product.setRequisiteType("RegisterType");
        product.setInterestRateType("InterestRateType");
        product.setTaxRate(BigDecimal.valueOf(9));
        product.setReasonClose("ReasonClose");
        product.setState("State");

        Product createproduct = createProduct(1L,null);
        ProductResponse productResponse =createProductResponse(1L,null);
        ProductRequest request = new ProductRequest(2L,2L,"Type","number",1,null,null,null,6,BigDecimal.valueOf(10.01),
                BigDecimal.valueOf(100.01),BigDecimal.valueOf(1000.01),"RegisterType","InterestRateType",BigDecimal.valueOf(9),
                "ReasonClose","State");

        Mockito.when(productService.save(product)).thenReturn(createproduct);
        Mockito.when(productMapper.requestToProduct(request)).thenReturn(product);
        Mockito.when(productMapper.productToResponse(createproduct)).thenReturn(productResponse);

        String actualResponse = mockMvc.perform(post("/v1/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String expectedResponse = StringTestUtils.readStringFromResource("response/create_product_response.json");

        Mockito.verify(productService, Mockito.times(1)).save(product);
        Mockito.verify(productMapper, Mockito.times(1)).requestToProduct(request);
        Mockito.verify(productMapper, Mockito.times(1)).productToResponse(createproduct);

        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);
    }

    @Test
    public void whenUpdateProduct_thenReturnUpdatedProduct() throws Exception {
        ProductRequest request = new ProductRequest(2L, 2L, "Type", "new_number", 1, null, null, null, 6, BigDecimal.valueOf(10.01),
                BigDecimal.valueOf(100.01), BigDecimal.valueOf(1000.01), "RegisterType", "InterestRateType", BigDecimal.valueOf(9),
                "ReasonClose", "State");

        Product updatedProduct = new Product(1L, 2L, 2L, "Type", "new_number", 1, null, null, null, 6, BigDecimal.valueOf(10.01),
                BigDecimal.valueOf(100.01), BigDecimal.valueOf(1000.01), "RegisterType", "InterestRateType", BigDecimal.valueOf(9),
                "ReasonClose", "State", new ArrayList<>());
        ProductResponse productResponse = new ProductResponse(1L, 2L, 2L, "Type", "new_number", 1, null, null, null, 6, BigDecimal.valueOf(10.01),
                BigDecimal.valueOf(100.01), BigDecimal.valueOf(1000.01), "RegisterType", "InterestRateType", BigDecimal.valueOf(9),
                "ReasonClose", "State", new ArrayList<>());

        Mockito.when(productService.update(updatedProduct)).thenReturn(updatedProduct);
        Mockito.when(productMapper.requestToProduct(1L, request)).thenReturn(updatedProduct);
        Mockito.when(productMapper.productToResponse(updatedProduct)).thenReturn(productResponse);

        String actualResponse = mockMvc.perform(put("/v1/product/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String expectedResponse = StringTestUtils.readStringFromResource("response/update_product_response.json");

        Mockito.verify(productService, Mockito.times(1)).update(updatedProduct);
        Mockito.verify(productMapper, Mockito.times(1)).requestToProduct(1L,request);
        Mockito.verify(productMapper, Mockito.times(1)).productToResponse(updatedProduct);

        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);
    }

    @Test
    public void whenDeleteProductById_thenReturnStatusNoContent() throws Exception {
        mockMvc.perform(delete("/v1/product/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(productService, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void whenFindByIdNotExistedProduct_thenReturnError() throws Exception {
        Mockito.when(productService.findById(5L)).thenThrow(new EntityNotFoundException("Клиент с id 5 не найден!"));

        var response = mockMvc.perform(get("/v1/product/5"))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse();

        response.setCharacterEncoding("UTF-8");

        String actualResponse = response.getContentAsString();
        String expectedResponse = StringTestUtils.readStringFromResource("response/product_by_id_not_found_response.json");

        Mockito.verify(productService, Mockito.times(1)).findById(5L);

        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);
    }

    @Test
    public void whenCreatedProductWithEmptyNumber_thenReturnError() throws Exception{


        var response = mockMvc.perform(post("/v1/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new ProductRequest(2L, 2L, "Type", null, 1, null, null, null, 6, BigDecimal.valueOf(10.01),
                                BigDecimal.valueOf(100.01), BigDecimal.valueOf(1000.01), "RegisterType", "InterestRateType", BigDecimal.valueOf(9),
                                "ReasonClose", "State"))))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse();

        response.setCharacterEncoding("UTF-8");

        String actualResponse = response.getContentAsString();
        String expectedResponse = StringTestUtils.readStringFromResource("response/empty_product_number_respones.json");

        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);

    }

    @ParameterizedTest
    @MethodSource("invalidSizeNumber")
    public void whenCreateProductWithInvalidSizeNumber_thenReturnError(String number) throws Exception{
        var response = mockMvc.perform(post("/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new ProductRequest(2L, 2L, "Type", number, 1, null, null, null, 6, BigDecimal.valueOf(10.01),
                                BigDecimal.valueOf(100.01), BigDecimal.valueOf(1000.01), "RegisterType", "InterestRateType", BigDecimal.valueOf(9),
                                "ReasonClose", "State"))))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse();
        response.setCharacterEncoding("UTF-8");

        String actualResponse = response.getContentAsString();
        String expectedResponse = StringTestUtils.readStringFromResource("response/product_number_size_exception_response.json");

        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);

    }


    private static Stream<Arguments> invalidSizeNumber(){
        return Stream.of(
                Arguments.of(RandomString.make(2)),
                Arguments.of(RandomString.make(51))
        );
    }
}
