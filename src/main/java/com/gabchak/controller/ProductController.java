package com.gabchak.controller;

import com.gabchak.dao.ProductDao;
import com.gabchak.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

    private ProductDao productDao;

    @Autowired
    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @RequestMapping(path = "/{categoryName}/product_{productId}", method = RequestMethod.GET)
    public ModelAndView showProductPage(@PathVariable String categoryName, @PathVariable Long productId, ModelAndView vm) {

        vm.setViewName("product");
        vm.addObject("product", productDao.findById(productId));

        return vm;
    }

    @GetMapping("/admin/products")
    public ModelAndView productAdminList(ModelAndView vm) {
        vm.setViewName("productAdminList");
        vm.addObject("products", productDao.findAll());
        return vm;
    }

    @GetMapping("/admin/products/edit_{id}")
    public ModelAndView editProductPage(@PathVariable Long id, ModelAndView vm) {
        vm.setViewName("product");
        vm.addObject("product", productDao.findById(id));
        return vm;
    }


    @PostMapping("/admin/products/save")
    public ModelAndView saveProduct(Product product, ModelAndView vm) {
        productDao.update(product);
        vm.setViewName("productAdminList");
        vm.addObject("products", productDao.findAll());
        return vm;
    }

    @PostMapping("/admin/products/delete_{id}")
    public ModelAndView deleteProduct(@PathVariable Long id, ModelAndView vm) {
        productDao.deleteById(id);
        vm.setViewName("productAdminList");
        vm.addObject("products", productDao.findAll());
        return vm;
    }

    //TODO: admin: add product page
    //TODO: admin: remove product (POST)
}
