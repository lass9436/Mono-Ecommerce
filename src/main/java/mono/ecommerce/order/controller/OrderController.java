package mono.ecommerce.order.controller;

import mono.ecommerce.order.service.OrderService;
import mono.ecommerce.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{orderId}")
    public OrderDto findById(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("orderId") Long orderId) {
        return orderService.findById(userDetails.getId(), orderId);
    }

    @PatchMapping("/{orderId}")
    public OrderDto updateOrderStatus(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("orderId") Long orderId, @RequestBody OrderUpdate orderUpdate) {
        return orderService.updateOrderStatus(userDetails.getId(), orderId, orderUpdate);
    }

    @PostMapping
    public OrderDto registerOrder(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody OrderRegister orderRegister) {
        return orderService.registerOrder(userDetails.getId(), orderRegister);
    }
}
