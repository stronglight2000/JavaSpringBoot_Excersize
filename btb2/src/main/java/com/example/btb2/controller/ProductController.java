package com.example.btb2.controller;

import com.example.btb2.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
@Controller
public class ProductController {

    private List<Product> products = new ArrayList<>(List.of(
            new Product("1","SamSung Galaxy S25 Ultra","Đẹp,mỏng",25000000,"SamSung"),
            new Product("2","Túi xách Dior","Kiểu dáng thời trang, thanh lịch",30000000,"Dior"),
            new Product("3","Bông tẩy trang Cotton pads","Mềm, mịn",30000,"Cotton pad"),
            new Product("4","Bàn phím cơ edra","Gọn gàng, dễ mang theo",500000,"Edra"),
            new Product("5","Nước tẩy trang Garnier","Làm sạch hiệu quả, dễ dàng",150000,"Garniner")

    ));

    @ResponseBody
    @GetMapping("/products")
public List<Product> getProducts(){return products;}
//1. Lấy thông tin chi tiết của một sản phẩm
//    API: GET /products/{id}
//    Mô tả: Trả về chi tiết của sản phẩm dựa trên id được cung cấp.
    @ResponseBody
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable String id){
        for (Product product:products) {
            if(product.getId().equals(id)) return product;
        }
        return null;
    }
//
//2. Lấy sản phẩm với tên bắt đầu bằng prefix nào đó
//    API: GET /products/name-starts/{prefix}
//    Mô tả: Trả về danh sách sản phẩm có tên bắt đầu bằng nào đó.
     @ResponseBody
    @GetMapping("/products/name-starts/{prefix}")
    public List<Product> getProductsByNamePrefix(@PathVariable String prefix) {
    List<Product> result = new ArrayList<>();
    for (Product product : products) {
        if (product.getName().toLowerCase().startsWith(prefix.toLowerCase())) {
            result.add(product);
        }
    }
    return result;
}
//3. Lọc sản phẩm theo khoảng giá
//    API: GET /products/price/{min}/{max}
//    Mô tả: Trả về danh sách sản phẩm có giá nằm trong khoảng từ min đến max.
    @ResponseBody
    @GetMapping("/products/price/{min}/{max}")
    public List<Product> getProductsByPrice(@PathVariable int min,@PathVariable int max) {
    List<Product> result = new ArrayList<>();
    for (Product product : products) {
        if (product.getPrice() >= min && product.getPrice() <= max) {
            result.add(product);
        }
    }
    return result;
}
//4. Lấy sản phẩm theo thương hiệu
//    API: GET /products/brand/{brand}
//    Mô tả: Trả về danh sách sản phẩm thuộc thương hiệu được chỉ định.
    @ResponseBody
    @GetMapping("/products/brand/{brand}")
    public List<Product> getProductsByBrand(@PathVariable String brand) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getBrand().equalsIgnoreCase(brand)) {
                result.add(product);
            }
        }
        return result;
    }
//            5. Lấy sản phẩm có giá cao nhất
//    API: GET /products/brand/{brand}/max-price
//    Mô tả: Trả về sản phẩm có giá cao nhất theo brand được chỉ định.
    @ResponseBody
    @GetMapping("/products/brand/{brand}/max-price")
    public Product getMaxPriceProductByBrand( @PathVariable String brand) {
    Product maxProduct = null;
    for (Product product : products) {
        if (product.getBrand().equalsIgnoreCase(brand)) {
            if (maxProduct == null || product.getPrice() > maxProduct.getPrice()) {
                maxProduct = product;
            }
        }
    }
    return maxProduct;
}
}
