package dev.rikthipranadhik.bhagkori.controller;

import dev.rikthipranadhik.bhagkori.domain.dto.PaymentDto;
import dev.rikthipranadhik.bhagkori.domain.entity.Payment;
import dev.rikthipranadhik.bhagkori.domain.mapper.PaymentMapper;
import dev.rikthipranadhik.bhagkori.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/payment")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentMapper  paymentMapper;


    @PostMapping("/create")
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto) {
        Long roomId = paymentDto.roomId();
        Long payerId = paymentDto.payerId();
        Long payeeId = paymentDto.payeeId();
        return ResponseEntity.ok(paymentMapper.toDto(paymentService.createPayment(roomId, payerId, payeeId, paymentMapper.fromDto(paymentDto))));
    }

    @GetMapping("/get/payer/{userId}")
    public ResponseEntity<List<PaymentDto>> getPaymentsByPayer(@PathVariable Long userId) {
        return ResponseEntity.ok(paymentService.getPaymentsByPayerId(userId)
                .stream()
                .map(paymentMapper :: toDto)
                .toList()
        );
    }

    @GetMapping("/get/room/{roomId}")
    public ResponseEntity<List<PaymentDto>> getPaymentsByRoom(@PathVariable Long roomId) {
        return ResponseEntity.ok(paymentService.getPaymentsByRoomId(roomId)
                .stream()
                .map(paymentMapper :: toDto)
                .toList()
        );
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable Long id){
        paymentService.deletePayment(id);
        return ResponseEntity.ok().body("Payment deleted successfully");
    }
}
