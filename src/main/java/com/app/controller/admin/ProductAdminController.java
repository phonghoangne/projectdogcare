package com.app.controller.admin;

import com.app.model.Product;
import com.app.service.ProductCategoryService;
import com.app.service.ProductService;
import com.app.utils.SaveFileUntil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/product-admin")
@RequiredArgsConstructor
public class ProductAdminController {
    private final ProductService productService;
    private final ProductCategoryService productCategoryService;
    @RequestMapping("/list")
    public String listUser(Model model) {

        model.addAttribute("products", productService.getAllProductDto());
        model.addAttribute("productSave", new Product());
        model.addAttribute("productCategory", productCategoryService.findAll());
        return "admin/product";
    }

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute("productSave") Product productSave,
                       @RequestParam(value = "imageProduct",required = false)  MultipartFile file,
                       @RequestParam(value = "date" ) Date date,
                       @RequestParam(value = "dateLocal")LocalDateTime localDateTime,
                       @RequestParam(value = "test_category")Integer categoryId){
        try {
        // mau , khuon
            //
            DateTimeFormatter localDateTi= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-dd-MM");
            System.out.println("date: "+simpleDateFormat.format(date));
            System.out.println("dateLocal"+localDateTime.format(localDateTi));
            Path pathUpload = Path.of("src/uploads");
            if(!file.isEmpty()){
                SaveFileUntil.save(file,pathUpload);
                productSave.setImage(file.getOriginalFilename());
            }
            productService.save(productSave);
            model.addAttribute("messageSave","Save success");
            model.addAttribute("productSave",new Product());
        }catch (Exception e ){
            e.printStackTrace();
        }

        return "admin/product";
    }

    @GetMapping("/viewAPI")
    public String viewAPI()
    {
        return "admin/ProductApi";
    }



}
