package com.app.controller.Admin;

import com.app.model.*;
import com.app.repository.UserRepository;
import com.app.service.*;
import com.app.utils.SaveFileUntil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final ProductService productService;
    private final ProductCategoryService productCategoryService;
    private final InvoiceService invoiceService;
    private final InvoiceLineService invoiceLineService;
    private final UserRepository userRepository;

    public AdminController(UserService userService, ProductService productService, ProductCategoryService productCategoryService, InvoiceService invoiceService, InvoiceLineService invoiceLineService, UserRepository userRepository) {
        this.userService = userService;
        this.productService = productService;
        this.productCategoryService = productCategoryService;
        this.invoiceService = invoiceService;
        this.invoiceLineService = invoiceLineService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getAdmin(Model model) {
        return "crudHometest";
    }

    @GetMapping("/account")
    public String getAccount(Model model, @RequestParam(defaultValue = "0", value = "page", required = false) int page) {
        model.addAttribute("userList", userService.findAll() );
        model.addAttribute("editUser", new User());
        return "crudAccount";
    }

    @GetMapping("/account/edit")
    public String getAdminEditAccount(Model model, @RequestParam(defaultValue = "0", value = "page", required = false) int page,
                                      @RequestParam("id") Integer integer) {
        List<User> users = userService.findAll();
        Optional<User> editUser = userService.findById(integer);
        model.addAttribute("userList", users);
        model.addAttribute("editUser", editUser.get());
        return "crudAccount";
    }

    @PostMapping("/account/add")
    public String postAdminAccount(Model model, @ModelAttribute("editUser") User userRegister, @RequestParam(defaultValue = "0", value = "page", required = false) int page) {
        System.out.println("user:" + userRegister.toString());
        userService.save(userRegister);
        model.addAttribute("success", "luu thanh cong");
      List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        model.addAttribute("editUser", new User());
        return "crudAccount";
    }

    @GetMapping("/account/remove")
    public String removeAdminAccount(Model model, @RequestParam("userId") Integer Id, @RequestParam(defaultValue = "0", value = "page", required = false) int page) {
        User user = userService.findById(Id).get();
        userService.delete(Id);
        model.addAttribute("success", "xoa thanh cong");
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList", userList);
        model.addAttribute("editUser", new User());
        return "crudAccount";
    }

    //book
    @GetMapping("/product/edit")
    public String editAdminProduct(Model model, @RequestParam(defaultValue = "0", value = "page", required = false) int page, @RequestParam("id") Integer id, MultipartFile file) {
        Optional<Product> editProduct = productService.findById(id);
        model.addAttribute("productList", productService.findAll());
        model.addAttribute("editProduct", editProduct.get());
        model.addAttribute("category", productCategoryService.findAll());
        return "curdProduct";
    }

    @PostMapping("/product/save")
    public String save(Model model, @ModelAttribute("editProduct") Product editProduct, @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            Path pathUpload = Path.of("src/uploads");
            if (!file.isEmpty()) {
                SaveFileUntil.save(file, pathUpload);
                editProduct.setImage(file.getOriginalFilename());
            }
            productService.save(editProduct);
            model.addAttribute("messageSave", "Save success");
            model.addAttribute("editProduct", new Product());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "curdProduct";
    }

    @GetMapping("/product/delete")
    public String deleteAdminProduct(Model model, @RequestParam(defaultValue = "0", value = "page", required = false) int page, @RequestParam("id") Integer id) {
        Product productDelete = productService.findById(id).get();
        productService.delete(productDelete);
        model.addAttribute("success", "xoa thanh cong");
        model.addAttribute("productList", productService.findAll());
        model.addAttribute("category", productCategoryService.findAll());
        model.addAttribute("editProduct", new Product());
        return "curdProduct";
    }

    @GetMapping("/category")
    public String getAdminCategory(Model model) {
        List<ProductCategory> categoryList = productCategoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("editCategory", new ProductCategory());
        List<ProductCategory> navCategory = productCategoryService.findAll();
        model.addAttribute("navCategory", navCategory);
        return "crudCategory";
    }

    @GetMapping("/category/edit")
    public String getAdminCategoryEdit(Model model, @RequestParam("id") Integer id) {
        List<ProductCategory> categoryList = productCategoryService.findAll();
        Optional<ProductCategory> editProductCategory = productCategoryService.findById(id);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("editProductCategory", editProductCategory.get());
        List<ProductCategory> navCategory = productCategoryService.findAll();
        model.addAttribute("navCategory", navCategory);
        return "crudCategory";
    }

    @PostMapping("/category/edit")
    public String postAdminCategoryEdit(Model model, @ModelAttribute("editProductCategory") ProductCategory editProductCategory) {
        List<ProductCategory> categoryList = productCategoryService.findAll();
        productCategoryService.save(editProductCategory);
        model.addAttribute("save thanh cong ", categoryList);
        model.addAttribute("editProductCategory", new ProductCategory());
        List<ProductCategory> navCategory = productCategoryService.findAll();
        model.addAttribute("navCategory", navCategory);
        return "crudCategory";
    }

    @GetMapping("/bill")
    public String getAdminBill(Model model, @RequestParam(defaultValue = "0", value = "page", required = false) int page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Invoice> invoicePage = invoiceService.findAll(pageable);
        List<Invoice> invoiceList = invoicePage.getContent();
        int totalPages = invoicePage.getTotalPages();
        model.addAttribute("invoiceList", invoiceList);
        System.out.println("totalPages" + totalPages);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentpage", page);
        model.addAttribute("editInvoice", new Invoice());
        Map<Integer, List<Cart>> mapInvoiceCart = new HashMap<>();
        System.out.println("cart1" + invoiceList.get(0).toString());
        model.addAttribute("cart", mapInvoiceCart);
        List<ProductCategory> navCategory = productCategoryService.findAll();
        model.addAttribute("navCategory", navCategory);
        return "crudBill";
    }


    @GetMapping("/product/list")
    public String getAllProductList(Model model, @RequestParam(defaultValue = "0", value = "page", required = false) int page) {
        model.addAttribute("editProduct", new Product());
        model.addAttribute("category", productCategoryService.findAll());
        model.addAttribute("productList", productService.findAll());
        return "curdProduct";
    }
}
