package ru.example.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.example.userservice.dto.PurchaseDto;
import ru.example.userservice.dto.request.CreatePurchaseRequest;
import ru.example.userservice.service.PurchaseService;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    public PurchaseDto create(@RequestBody CreatePurchaseRequest createPurchaseRequest,
                              @PathVariable("userId") Long userId) {
        return purchaseService.create(createPurchaseRequest, userId);
    }

    @GetMapping
    public List<PurchaseDto> getByUser(@PathVariable("userId") Long userId,
                                       @RequestParam(value = "page", defaultValue = "0") int pageNumber,
                                       @RequestParam(value = "size", defaultValue = "10") int pageSize) {
        return purchaseService.getByUser(userId, pageNumber, pageSize);
    }
}
