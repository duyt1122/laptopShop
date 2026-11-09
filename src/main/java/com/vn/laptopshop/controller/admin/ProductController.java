package com.vn.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.vn.laptopshop.domain.Product;
import com.vn.laptopshop.service.ProductService;
import com.vn.laptopshop.service.UploadService;

import jakarta.validation.Valid;

@Controller 
public class ProductController {

    private final ProductService productService;
    private final UploadService uploadService;
    
    public ProductController(ProductService productService, UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }

     @GetMapping ("/admin/product")
    private String getProduct(Model model){
      List<Product> products = productService.getAllProducts();
      model.addAttribute("products", products);
        return "admin/product/show";
    }

    @GetMapping ("/admin/product/create")
    private String getProductCreate(Model model){
        model.addAttribute("newProduct",new Product());
        return "admin/product/create";
    }
    @PostMapping ("/admin/product/create")
    private String productCreate(Model model, @ModelAttribute ("newProduct")  @Valid Product product, BindingResult productBindingResult, @RequestParam("imageFile") MultipartFile file){
        String productImage = this.uploadService.handleSaveUploadFile(file,"product");

        if(productBindingResult.hasErrors()){
           
            return "admin/product/create";
        }
        product.setImage(productImage);
        this.productService.saveProduct(product);
        return "redirect:/admin/product";
    }
    @GetMapping ("/admin/product/delete/{id}")
    public String getProductDelete(Model model, @PathVariable("id") Long id){
        model.addAttribute("newProduct", new Product());
        model.addAttribute("id", id);
        return "admin/product/delete";
    }
    @PostMapping ("/admin/product/delete")
    public String productDelete( @ModelAttribute ("newProduct") Product product){
        this.productService.deleteProduct(product.getId());
        return "redirect:/admin/product";
    }
    @GetMapping ("/admin/product/update/{id}")
    public String getProductUpdate(Model model, @PathVariable("id") Long id){
        Product product = this.productService.getProductById(id);
        model.addAttribute("newProduct", product);
        return "admin/product/update";
    }
    @PostMapping ("/admin/product/update")
    public String productUpdate(Model model, @ModelAttribute ("newProduct")  @Valid Product product, BindingResult productBindingResult, @RequestParam("imageFile") MultipartFile file){
        
        if(productBindingResult.hasErrors()){
            return "admin/product/update";
        }
        Product existingProduct = this.productService.getProductById(product.getId());
        if(existingProduct != null){
            if(!file.isEmpty()){
                String productImage = this.uploadService.handleSaveUploadFile(file,"product");
                existingProduct.setImage(productImage);
            }
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setFactory(product.getFactory());
            existingProduct.setDetailDesc(product.getDetailDesc());
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setShortDesc(product.getShortDesc());
            existingProduct.setTarget(product.getTarget());

            this.productService.saveProduct(existingProduct);
        }
        
        
        return "redirect:/admin/product";
    }
    @GetMapping ("/admin/product/{id}")
    public String getProductDetail(Model model, @PathVariable("id") Long id){
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        return "admin/product/detail";
    }
}
