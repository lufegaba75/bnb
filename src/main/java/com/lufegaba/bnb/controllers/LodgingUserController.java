package com.lufegaba.bnb.controllers;

import com.lufegaba.bnb.domain.Lodging;
import com.lufegaba.bnb.domain.Role;
import com.lufegaba.bnb.infraestructure.exceptions.NoHirerException;
import com.lufegaba.bnb.infraestructure.exceptions.UnauthorizedResourceException;
import com.lufegaba.bnb.infraestructure.services.LodgingService;
import com.lufegaba.bnb.infraestructure.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping (path = "api/v1/users/{userId}/lodgings")
@AllArgsConstructor
public class LodgingUserController {

    private final UserService userService;
    private final LodgingService lodgingService;

    @PostMapping
    public ResponseEntity<Lodging> createLodging (
            @PathVariable Integer userId, @RequestBody @Valid Lodging lodging
    ) {
        var lodgingCreated = lodgingService.createLodging(lodging);
        lodgingService.assignToUser(userId, lodgingCreated);
        return ResponseEntity.ok(lodgingCreated);
    }

    @GetMapping
    public ResponseEntity<List<Lodging>> showUserLodgings (@PathVariable Integer userId) {
        return ResponseEntity.ok(lodgingService.showUserLodgings(userId));
    }

    @GetMapping ("/{lodgingId}")
    public ResponseEntity<Lodging> showUserLodgingById (@PathVariable Integer lodgingId, @PathVariable Integer userId) {
        if (lodgingService.getLodgingById(lodgingId).getOwner().getId() != userId){
            throw new UnauthorizedResourceException();
        }
        return ResponseEntity.ok(lodgingService.getLodgingById(lodgingId));
    }

    @DeleteMapping ("/{lodgingId}")
    public ResponseEntity<Void> deleteLodgingById (@PathVariable Integer lodgingId, @PathVariable Integer userId) {
        if (lodgingService.getLodgingById(lodgingId).getOwner().getId() != userId) {
            throw new UnauthorizedResourceException();
        }
        lodgingService.deleteLodgingById(lodgingId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping ("/{lodgingId}")
    public ResponseEntity<Lodging> updateLodging (
            @PathVariable Integer lodgingId,
            @PathVariable Integer userId,
            @RequestBody @Valid Lodging lodging
    ){
        if (lodgingService.getLodgingById(lodgingId).getOwner().getId() != userId) {
            throw new UnauthorizedResourceException();
        }
        return ResponseEntity.ok(lodgingService.updateLodging(lodgingId,lodging));
    }
}
