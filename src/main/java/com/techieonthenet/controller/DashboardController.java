package com.techieonthenet.controller;

import com.techieonthenet.dto.Pie;
import com.techieonthenet.entity.CartItem;
import com.techieonthenet.entity.Order;
import com.techieonthenet.entity.Role;
import com.techieonthenet.entity.User;
import com.techieonthenet.entity.common.OrderStatus;
import com.techieonthenet.service.OrderService;
import com.techieonthenet.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.format.TextStyle;
import java.util.*;

/**
 * The type Dashboard controller.
 */
@Controller
public class DashboardController {

    private static Logger logger = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    private OrderService orderService;

    /**
     * Dash page string.
     *
     * @param principal the principal
     * @param model     the model
     * @param session   the session
     * @return the string
     */
    @GetMapping("/dashboard")
    public String dashPage(Principal principal, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Map<String, Integer> chartData = new HashMap<>();
        int totalItemsTillDate = 0;
        int totalItemsLastMonth = 0;


        BigDecimal totalExpenseLastMonth = BigDecimal.valueOf(0);
        BigDecimal totalExpenseTillDate = BigDecimal.valueOf(0);
        BigDecimal totalExpensePreviousLastMonth = BigDecimal.valueOf(0);
        BigDecimal totalExpensePreviousToPreviousLastMonth = BigDecimal.valueOf(0);
        Map<String, Integer> categoryMap = new HashMap<>();
        List<CartItem> cartItemList = new ArrayList<>();
        List<Order> orderList = new ArrayList<>();
        for (Role role : user.getRoles()) {
            if (role.getName().equals("GROUP_ADMIN")) {
                orderList = orderService.findByGroup(user.getGroup());
                break;
            } else if (role.getName().equals("ADMIN") || role.getName().equals("SUPER_ADMIN")) {
                orderService.findAll().forEach(orderList::add);
                break;
            } else {
                orderList = orderService.findByUser(user);
                break;
            }
        }

        //logic for line graph
        for (Order order : orderList) {
            if (order.getOrderStatus().equals(OrderStatus.APPROVED_PENDING_SHIPMENT)
                    || order.getOrderStatus().equals(OrderStatus.DELIVERED)
                    || order.getOrderStatus().equals(OrderStatus.SHIPPED)) {
                totalItemsTillDate += order.getCartItemList().stream().mapToInt(CartItem::getQty).sum();
                totalExpenseTillDate = totalExpenseTillDate.add(order.getOrderTotal());

                if (order.getOrderDate().compareTo(DateUtils.getLastMonthStartDate().minusMonths(2)) >= 0 &&
                        order.getOrderDate().compareTo(DateUtils.getLastMonthEndDate().minusMonths(2)) <= 0) {

                    totalExpensePreviousToPreviousLastMonth = totalExpensePreviousToPreviousLastMonth.add(order.getOrderTotal());
                    chartData.put(DateUtils.getLastMonthEndDate().minusMonths(2).getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH), totalExpensePreviousToPreviousLastMonth.intValue());
                    cartItemList.addAll(order.getCartItemList());
                }
                if (order.getOrderDate().compareTo(DateUtils.getLastMonthStartDate().minusMonths(1)) >= 0 &&
                        order.getOrderDate().compareTo(DateUtils.getLastMonthEndDate().minusMonths(1)) <= 0) {

                    totalExpensePreviousLastMonth = totalExpensePreviousLastMonth.add(order.getOrderTotal());
                    chartData.put(DateUtils.getLastMonthEndDate().minusMonths(1).getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH), totalExpensePreviousLastMonth.intValue());
                    cartItemList.addAll(order.getCartItemList());
                }
                if (order.getOrderDate().compareTo(DateUtils.getLastMonthStartDate()) >= 0 &&
                        order.getOrderDate().compareTo(DateUtils.getLastMonthEndDate()) <= 0) {
                    totalItemsLastMonth += order.getCartItemList().stream().mapToInt(CartItem::getQty).sum();
                    totalExpenseLastMonth = totalExpenseLastMonth.add(order.getOrderTotal());
                    chartData.put(DateUtils.getLastMonthEndDate().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH), totalExpenseLastMonth.intValue());
                    cartItemList.addAll(order.getCartItemList());
                }
            }
        }
        model.addAttribute("totalItemsLastMonth", totalItemsLastMonth);
        model.addAttribute("totalOrdersTillDate", totalItemsTillDate);
        model.addAttribute("totalExpenseTillDate", totalExpenseTillDate);
        model.addAttribute("totalExpenseLastMonth", totalExpenseLastMonth.intValue());
        model.addAttribute("chartData", chartData);

        //pie graph logic
        for (CartItem cartItem : cartItemList) {
            if (categoryMap.containsKey(cartItem.getProduct().getCategory().getName())) {
                categoryMap.replace(cartItem.getProduct().getCategory().getName(), categoryMap.get(cartItem.getProduct().getCategory().getName()) + cartItem.getSubTotal().intValue());
            } else {
                categoryMap.put(cartItem.getProduct().getCategory().getName(), cartItem.getSubTotal().intValue());
            }
        }
        List<Pie> listMapPie = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : categoryMap.entrySet()) {
            listMapPie.add(new Pie(entry.getKey(), entry.getValue()));
        }
        model.addAttribute("pieJsonData", listMapPie);
        return "dashboard";
    }


}
