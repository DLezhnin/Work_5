package inno.lab5;

import com.fasterxml.jackson.databind.ObjectMapper;
import inno.lab5.model.Product;
import inno.lab5.model.Register;
import inno.lab5.web.model.response.RegisterResponse;
import inno.lab5.web.model.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public abstract class AbstractTestController {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected Product createProduct(Long id, Register register){

        Product product = new Product(
                id,
                2L,
                2L,
                "Type",
                "number",
                1,
                null,
                null,
                null,
                6,
                BigDecimal.valueOf(10.01),
                BigDecimal.valueOf(100.01),
                BigDecimal.valueOf(1000.01),
                "RegisterType",
                "InterestRateType",
                BigDecimal.valueOf(9),
                "ReasonClose",
                "State",
                new ArrayList<>()
        );
        if(register != null){
            register.setProduct(product);
            product.addProductRegister(register);
        }
        return product;
    }

    protected Register createProductRegister(Long id, Product product){
            Register register = new Register(
                    id,
                    product,
                    "Type",
                    2L,
                    "CurrencyCode",
                    "State",
                    "AccountNumber"
            );
        return register;
    }

    protected ProductResponse createProductResponse(Long id, RegisterResponse registerResponse){
        ProductResponse productResponse = new ProductResponse(
                id,
                2L,
                2L,
                "Type",
                "number",
                1,
                null,
                null,
                null,
                6,
                BigDecimal.valueOf(10.01),
                BigDecimal.valueOf(100.01),
                BigDecimal.valueOf(1000.01),
                "RegisterType",
                "InterestRateType",
                BigDecimal.valueOf(9),
                "ReasonClose",
                "State",
                new ArrayList<>()
        );
        if(registerResponse != null){
            productResponse.getProductRegisters().add(registerResponse);
        }
        return productResponse;
    }

    protected RegisterResponse createProductRegisterResponse(Long id) {
        return new RegisterResponse(
                id,
                1L,
                "Type",
                2L,
                "RUR",
                "State",
                "40815810000000000001"
        );
    }
}