package inno.lab5.service;

import inno.lab5.model.Product;

import java.util.List;

public interface ProductService {
    Product save(Product productid);
    Product findById(Long id);
}
