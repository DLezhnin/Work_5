package inno.lab5.web.controller;

import inno.lab5.mapper.ProductMapper;
import inno.lab5.model.Product;
import inno.lab5.service.ValidateService;
import inno.lab5.service.impl.DatabaseProductService;
import inno.lab5.web.model.response.ProductResponse;
import inno.lab5.web.model.request.ProductRequest;
import jakarta.validation.Valid;
import jakarta.xml.bind.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final DatabaseProductService databaseProductService;
    @Autowired
    private final ProductMapper productMapper;


//    @GetMapping("/{id}")
//    public ResponseEntity<ProductResponse> findById(@PathVariable Long id){
//        return ResponseEntity.ok(
//                productMapper.productToResponse(databaseProductService.findById(id))
//        );
//    }

    @PostMapping
    public  ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductRequest request) throws ValidationException {
        Product newProduct = databaseProductService.save(productMapper.requestToProduct(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                        .body(productMapper.productToResponse(newProduct));
    }




}
