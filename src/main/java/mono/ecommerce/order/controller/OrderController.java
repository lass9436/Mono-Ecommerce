package mono.ecommerce.order.controller;

import mono.ecommerce.order.service.OrderService;
import mono.ecommerce.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDto> findByUserId(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return orderService.findByUserId(userDetails.getId());
    }
}
