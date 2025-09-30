package com.ecommerce.order.services;

import com.ecommerce.order.dto.CartItemRequest;
import com.ecommerce.order.models.CartItem;
import com.ecommerce.order.repository.CartItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServices {

    private final CartItemRepository cartItemRepository;

    public boolean addToCart(String userId, CartItemRequest request) {
        // check if product exist
//        Optional<Product> productOpt = productRepository.findById(request.getProductId());
//        if(productOpt.isEmpty())
//            return false;
//
//        // if stock exsist
//        Product product = productOpt.get();
//        if (product.getStockQuantity() < request.getQuantity())
//            return false;
//
//        // check if user exist by this id
//        Optional <User> userOpt = userRepository.findById(Long.valueOf(userId));
//        if(userOpt.isEmpty())
//            return false;

        //User user = userOpt.get();

        CartItem existingCartItem = cartItemRepository.findByUserIdAndProductId(userId, request.getProductId());
        if (existingCartItem != null) {
            // update quantity
            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
            existingCartItem.setPrice(BigDecimal.valueOf(1000.00));
            cartItemRepository.save(existingCartItem);
        }else {
            // create new cart item
            CartItem cartItem = new CartItem();
            cartItem.setUserId(userId);
            cartItem.setProductId(request.getProductId());
            cartItem.setQuantity(request.getQuantity());
            cartItem.setPrice(BigDecimal.valueOf(1000.00));
            cartItemRepository.save(cartItem);
        }
        return true;
    }

    public boolean deleteItemFromCart(String userId, String productId) {
        CartItem cartItem =  cartItemRepository.findByUserIdAndProductId(userId, productId);
        if (cartItem != null){
            cartItemRepository.delete(cartItem);
            return true;
        }
        return false;
    }

    public List<CartItem> getCart(String userId) {
        return cartItemRepository.findByUserId(userId);
    }

    public void clearCart(String userId) {
        cartItemRepository.deleteByUserId(userId);
    }
}
