package com.nextecommerce.commerce.apis;

import com.nextecommerce.commerce.dtos.requests.OrderRequestDTO;
import com.nextecommerce.commerce.dtos.responses.OrderResponseDTO;
import com.nextecommerce.commerce.services.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {

        return new ResponseEntity<>(orderService.getAllOrders(),HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public  ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {

        return new ResponseEntity<>(orderService.getOrderById(id),HttpStatus.OK);

    }

    @PostMapping
    public  ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO request) {

        return new ResponseEntity<>(orderService.createOrder(request),HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public  ResponseEntity<OrderResponseDTO> updateOrder(@PathVariable Long id, @Valid @RequestBody OrderRequestDTO request) {

        return new ResponseEntity<>(orderService.updateOrder(id,request),HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable Long id) {

        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
