package dev.rikthipranadhik.bhagkori.controller;

import dev.rikthipranadhik.bhagkori.domain.dto.PaymentDto;
import dev.rikthipranadhik.bhagkori.domain.entity.Payment;
import dev.rikthipranadhik.bhagkori.domain.mapper.PaymentMapper;
import dev.rikthipranadhik.bhagkori.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payment")
@CrossOrigin("*")
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

}
