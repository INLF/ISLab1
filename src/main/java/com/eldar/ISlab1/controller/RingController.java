package com.eldar.ISlab1.controller;


import com.eldar.ISlab1.domain.dto.request.RingRequest;
import com.eldar.ISlab1.domain.dto.response.RingResponse;
import com.eldar.ISlab1.service.impl.RingServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/ring")
@RequiredArgsConstructor
public class RingController {
    private final RingServiceImpl ringServiceImpl;

    @PostMapping("create")
    public ResponseEntity<RingResponse> createRing(@RequestBody @Valid RingRequest request) {

        RingResponse response = ringServiceImpl.createRing(request);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRing(@PathVariable("id") @PositiveOrZero int ringId) {
        if (ringServiceImpl.deleteRing(ringId)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<RingResponse> getRing(@PathVariable("id") @PositiveOrZero int ringId) {
        RingResponse response = ringServiceImpl.getRingById(ringId);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("update")
    public ResponseEntity<RingResponse> updateRing(@RequestBody @Valid RingRequest request) {
        if (request.id == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        RingResponse response = ringServiceImpl.updateRing(request);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("filtered")
    public ResponseEntity<Page<RingResponse>> getFilteredRings(
            @RequestBody RingRequest request ,
            Pageable pageable
    ) {
        return ResponseEntity.ok(ringServiceImpl.getFilteredRings(request, pageable));
    }

}
