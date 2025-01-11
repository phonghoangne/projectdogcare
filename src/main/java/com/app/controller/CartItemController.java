package com.app.controller;

import com.app.DTO.CartItemDto;
import com.app.model.CartItem;
import com.app.service.CartItemService;
import com.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/app/cart")
@RequiredArgsConstructor
public class CartItemController {
    private final CartItemService cartItemService;
    private final ProductService productService;

  @GetMapping("/view")
  public String getCart(Model model, @RequestParam Integer customerId){
      try{
          List<CartItemDto> cartItemDtos = cartItemService.getAllByCustomerIdAndStatus(customerId,"DRA");
          model.addAttribute("listCart",cartItemDtos);
      }catch(Exception e){
          model.addAttribute("error","co loi khi lay gio hang");
          e.printStackTrace();
      }
      return "cartItem";
  }
    @GetMapping("/add")
    public String addCart(RedirectAttributes redirectAttributes, @RequestParam Integer productId, @RequestParam  Integer customerId, Locale locale){
       try{
           CartItem cartItem = cartItemService.addProductToCart(productId,customerId,1,locale);
           redirectAttributes.addAttribute("success","San pham da duoc them vao gio hang");
           BigDecimal totalPrice = cartItem.getTotalPrice();
           System.out.println("Total Price: " + totalPrice);
           redirectAttributes.addAttribute("totalPrice",totalPrice);
       }catch(Exception e){
           redirectAttributes.addAttribute("error","co loi khi lay gio hang");
           e.printStackTrace();
       }
        return "cartItem";
    }

    @GetMapping("/delete")
    public  String deleteCartItem(Model model,@RequestParam Integer cartId,@RequestParam Integer customerId,Locale locale){
       try{
           cartItemService.delete(cartId);
           model.addAttribute("success","San pham da duoc xoa khoi gio hang");
       }catch(Exception e){
           model.addAttribute("error","Co loi xay ra khi xoa san pham ");
           e.printStackTrace();
       }
        return "redirect:/app/cart/view?customerId="+customerId;
    }
    @GetMapping("/update")
    public String updateCart(Model model,
                             @RequestParam Integer cartId,
                             @RequestParam Integer quantity,
                             @RequestParam Integer customerId){
      try{
          if(quantity <= 0 ){
              model.addAttribute("error","so luong phai hon 0 ");
          }else{
              cartItemService.updateCartItem(cartId,quantity);
              model.addAttribute("success","Cap nhat so luong thanh cong");
          }
      }catch (Exception e){
          model.addAttribute("error", "co loi  xay ra khi cap nhat so luong san pham");
          e.printStackTrace();
      }
      return "redirect:/app/cart/view?customerId=" + customerId;
    }
    @GetMapping("/checkout")
    public String checkout(Model model ,@RequestParam Integer customerId){
      try{
          cartItemService.checkoutCart(customerId);
          model.addAttribute("success", "Don hang hoan tat");
      }catch(Exception e){
          model.addAttribute("error","Co loi xay ra khi xac nhan don hang");
          e.printStackTrace();
      }
      return "crudBill";
    }
}