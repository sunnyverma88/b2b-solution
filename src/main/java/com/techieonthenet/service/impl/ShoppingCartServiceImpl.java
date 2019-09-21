package com.techieonthenet.service.impl;

import com.techieonthenet.entity.CartItem;
import com.techieonthenet.entity.ShoppingCart;
import com.techieonthenet.repository.ShoppingCartRepository;
import com.techieonthenet.service.CartItemService;
import com.techieonthenet.service.HsnCodeGstMappingService;
import com.techieonthenet.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private static Logger logger = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private HsnCodeGstMappingService hsnCodeGstMappingService;

    @Override
    public Iterable<ShoppingCart> findAll() {
        return shoppingCartRepository.findAll();
    }

    @Override
    public ShoppingCart save(ShoppingCart cart) {
        return shoppingCartRepository.save(cart);
    }

    @Override
    public ShoppingCart findById(Long id) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(id);
        return shoppingCart.orElse(null);
    }

    @Override
    public ShoppingCart findByUserId(Long userId) {
        return shoppingCartRepository.findByUserId(userId);
    }

    @Override
    public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {
        BigDecimal cartTotal = new BigDecimal(0);
        BigDecimal gst = new BigDecimal(0);
        int totalItems = 0;
        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
        for (CartItem cartItem : cartItemList) {
            cartTotal = cartTotal.add(cartItem.getSubTotal());
            gst = gst.add(new BigDecimal(hsnCodeGstMappingService.findByHsnCode(
                    cartItem.getProduct().getHsnCode()).getGstPercentage()).
                    multiply(cartItem.getSubTotal()));
            logger.info("Cart Item {} Quantity -  {}", cartItem.getProduct().getName(), cartItem.getQty());
            totalItems += cartItem.getQty();
            logger.info("Total Quantity - {} ", totalItems);
        }
        shoppingCart.setGst(gst.divide(new BigDecimal(100)).round(MathContext.DECIMAL32));
        shoppingCart.setCartTotal(cartTotal);
        shoppingCart.setGrandTotal(cartTotal.add(shoppingCart.getGst()));
        shoppingCart.setTotalItems(totalItems);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

}
