package com.eldar.ISlab1.controller;



import com.eldar.ISlab1.domain.dto.request.MagicCityRequest;
import com.eldar.ISlab1.domain.dto.request.RingRequest;
import com.eldar.ISlab1.domain.dto.response.MagicCityResponse;
import com.eldar.ISlab1.domain.dto.response.RingResponse;
import com.eldar.ISlab1.domain.mappers.MagicCityMapper;
import com.eldar.ISlab1.domain.model.MagicCityEntity;
import com.eldar.ISlab1.service.MagicCityService;
import com.eldar.ISlab1.service.impl.MagicCityServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/city")
@RequiredArgsConstructor
public class MagicCityController {
    private final MagicCityService magicCityService;


    @PostMapping("create")
    public ResponseEntity<MagicCityResponse> createCity(@RequestBody @Valid MagicCityRequest request) {
        MagicCityResponse response = magicCityService.createCity(request);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCity(@PathVariable("id") @PositiveOrZero long id) {
        if (magicCityService.softDeleteCity(id)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<MagicCityResponse> getCity(@PathVariable @PositiveOrZero long id) {
        MagicCityResponse response = magicCityService.getCityEntityById(id);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


        return ResponseEntity.ok(response);
    }

    @PostMapping("update")
    public ResponseEntity<MagicCityResponse> updateCity(@RequestBody @Valid MagicCityRequest request) {
        MagicCityResponse response = magicCityService.updateCity(request);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("filtered")
    public ResponseEntity<Page<MagicCityResponse>> getFilteredCities(
            @RequestBody MagicCityRequest request ,
            Pageable pageable
    ) {
        return ResponseEntity.ok(magicCityService.getFilteredCities(request, pageable));
    }

    @DeleteMapping("/destroy-elf-cities")
    public ResponseEntity<String> destroyElfCities() {
        magicCityService.destroyElfCities();
        return ResponseEntity.ok().build();
    }

}
