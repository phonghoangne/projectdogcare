package com.app.controller;

import com.app.model.*;
import com.app.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController  {
    private final UserService userService;
    private final ProductService productService;
    private final ProductCategoryService productCategoryService;
    private final InvoiceService invoiceService;
    private final InvoiceLineService invoiceLineService;

    public AdminController(UserService userService, ProductService productService, ProductCategoryService productCategoryService, InvoiceService invoiceService, InvoiceLineService invoiceLineService) {
        this.userService = userService;
        this.productService = productService;
        this.productCategoryService = productCategoryService;
        this.invoiceService = invoiceService;
        this.invoiceLineService = invoiceLineService;
    }

    @GetMapping
    public String getAdmin(Model model ){
        return "crudHome";
    }
    @GetMapping("/account")
    public String getAccount(Model model , @RequestParam(defaultValue = "0",value = "page",required = false) int page){
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page , pageSize);
        Page<User> userPage = userService.findAll(pageable);
        List<User> users = userPage.getContent();
        int totalPages = userPage.getTotalPages();
        model.addAttribute("users", users);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentpage", page);
        model.addAttribute("editUser", new User());


        return "crudHome";
    }
    @GetMapping("/account/edit")
    public String getAdminEditAccount(Model model , @RequestParam(defaultValue = "0",value = "page",required = false) int page,
                                                    @RequestParam("id")Integer integer){
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page , pageSize);
        Page<User> userPage = userService.findAll(pageable);
        List<User> users = userPage.getContent();
        int totalPages = userPage.getTotalPages();
        Optional<User>  editUser = userService.findById(integer);
        model.addAttribute("userList",users);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("currentpage",page);
        model.addAttribute("editUser",editUser);
        return "crudHome";
    }
    @PostMapping("/account/add")
    public String postAdminAccount(Model model , @ModelAttribute("editUser")User userRegister, @RequestParam(defaultValue = "0",value = "page",required = false)int page){
        System.out.println("user:"+ userRegister.toString());
        userService.save(userRegister);
        model.addAttribute("succes","luu thanh cong");
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page , pageSize);
        Page<User> userPage = userService.findAll(pageable);
        List<User> users = userPage.getContent();
        int totalPages = userPage.getTotalPages();
        model.addAttribute("userList",users);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("currentpage",page);
        model.addAttribute("editUser",new User());
        return "crudHome";
    }
    @GetMapping("/account/remove")
    public String removeAdminAccount(Model model ,@RequestParam("userId")Integer userId ,@RequestParam(defaultValue = "0",value = "page",required = false) int page){
        User us = userService.findById(userId).get();
        userService.delete(userId);
        model.addAttribute("succes","xoa thanh cong");
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page , pageSize);
        Page<User> userPage = userService.findAll(pageable);
        List<User> users = userPage.getContent();
        int totalPages = userPage.getTotalPages();
        model.addAttribute("userList",users);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("currentpage",page);
        model.addAttribute("editUser",new User());

        return "crudHome";
    }
    //book
    @GetMapping("/product")
    public String getAdminProduct(Model model , @RequestParam(defaultValue = "0",value = "page",required = false) int page, @RequestParam("id")Integer id
            , MultipartFile file){
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page , pageSize);
        Page<Product> productPage = productService.findAll(pageable);
        List<Product> products = productPage.getContent();
        int totalPages = productPage.getTotalPages();
        List<ProductCategory> categoryList = productCategoryService.findAll();
        Optional<Product> editProduct = productService.findById(id);
        model.addAttribute("productList",products);
        model.addAttribute("CategoryList",categoryList);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("currentpage",page);
        model.addAttribute("editProduct",editProduct.get());
        return "crudHome";
    }
    @PostMapping("/book/save")
    public String save(Model model,@ModelAttribute("editProduct") Product editProduct,@RequestParam(value = "file",required = false) MultipartFile file){

        return "crudHome";
    }
    @GetMapping("/product/delete")
    public String deleteAdminProduct(Model model,@RequestParam(defaultValue = "0",value = "page",required = false) int page, @RequestParam("id")Integer id){
        Product productDelete = productService.findById(id).get();
        productService.delete(productDelete);
        model.addAttribute("succes","xoa thanh cong");
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page , pageSize);
        Page<Product> productPage = productService.findAll(pageable);
        List<Product> products = productPage.getContent();
        int totalPages = productPage.getTotalPages();
        List<ProductCategory> categoryList = productCategoryService.findAll();
        model.addAttribute("productList",products);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("currentpage",page);
        model.addAttribute("editProduct",new Product());
        return "crudHome";
    }
    @GetMapping("/category")
    public String getAdminCategory(Model model){
        List<ProductCategory> categoryList = productCategoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("editCategory",new ProductCategory());
        List<ProductCategory> navCategory = productCategoryService.findAll();
        model.addAttribute("navCategory",navCategory);
        return "crudCategory";
    }
    @GetMapping("/category/edit")
    public String getAdminCategoryEdit(Model model ,@RequestParam("id")Integer id){
        List<ProductCategory> categoryList = productCategoryService.findAll();
       Optional<ProductCategory> editProductCategory = productCategoryService.findById(id);
       model.addAttribute("categoryList",categoryList);
       model.addAttribute("editProductCategory",editProductCategory.get());
       List<ProductCategory> navCategory = productCategoryService.findAll();
       model.addAttribute("navCategory",navCategory);
       return "crudCategory";
    }
    @PostMapping("/category/edit")
    public String postAdminCategoryEdit(Model model ,@ModelAttribute("editProductCategory") ProductCategory editProductCategory){
        List<ProductCategory> categoryList = productCategoryService.findAll();
        productCategoryService.save(editProductCategory);
        model.addAttribute("save thanh cong ", categoryList);
    model.addAttribute("editProductCategory",new ProductCategory());
    List<ProductCategory> navCategory = productCategoryService.findAll();
    model.addAttribute("navCategory",navCategory);
    return "crudCategory";
    }
    @GetMapping("/bill")
    public String getAdminBill(Model model,@RequestParam(defaultValue = "0",value = "page",required = false)int page){
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page , pageSize);
        Page<Invoice> invoicePage  = invoiceService.findAll(pageable);
        List<Invoice> invoiceList = invoicePage.getContent();
        int totalPages = invoicePage.getTotalPages();
        model.addAttribute("invoiceList",invoiceList);
        System.out.println("totalPages"+totalPages);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("currentpage",page);
        model.addAttribute("editInvoice",new Invoice());
        Map<Integer,List<Cart>> mapInvoiceCart = new HashMap<>();
        System.out.println("cart1"+invoiceList.get(0).toString());
        model.addAttribute("cart",mapInvoiceCart);
        List<ProductCategory> navCategory = productCategoryService.findAll();
        model.addAttribute("navCategory",navCategory);
        return "crudBill";
    }



}
