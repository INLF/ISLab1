package com.eldar.ISlab1.controller;

import com.eldar.ISlab1.domain.dto.RequestBookCreatureDTO;
import com.eldar.ISlab1.domain.dto.ResponseBookCreatureDTO;
import com.eldar.ISlab1.domain.dto.request.BookCreatureRequest;
import com.eldar.ISlab1.domain.dto.request.MagicCityRequest;
import com.eldar.ISlab1.domain.dto.response.BookCreatureResponse;
import com.eldar.ISlab1.domain.dto.response.MagicCityResponse;
import com.eldar.ISlab1.domain.model.BookCreatureEntity;
import com.eldar.ISlab1.service.BookCreatureService;
import com.eldar.ISlab1.service.impl.BookCreatureServiceImpl;
import com.eldar.ISlab1.service.impl.CoordinatesServiceImpl;
import com.eldar.ISlab1.service.impl.MagicCityServiceImpl;
import com.eldar.ISlab1.service.impl.RingServiceImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.model.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creature")
@RequiredArgsConstructor
public class BookCreatureController {
    private final BookCreatureService bookCreatureService;


    @PostMapping("create")
    public ResponseEntity<BookCreatureResponse> createBookCreature(@RequestBody @Valid BookCreatureRequest request) {
        BookCreatureResponse response = bookCreatureService.createBookCreature(request);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
       return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBookCreature(@PathVariable @PositiveOrZero long id) {
        boolean isDeleted = bookCreatureService.deleteBookCreature(id);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<BookCreatureResponse> getBookCreature(@PathVariable @PositiveOrZero long id) {
        BookCreatureResponse response = bookCreatureService.getBookCreatureEntityById(id);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("update")
    public ResponseEntity<BookCreatureResponse> updateBookCreature(@RequestBody BookCreatureRequest request) {
        BookCreatureResponse response = bookCreatureService.updateBookCreature(request);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("filtered")
    public ResponseEntity<Page<BookCreatureResponse>> getFilteredBookCreatures(
            @RequestBody BookCreatureRequest request ,
            Pageable pageable
    ) {
        return ResponseEntity.ok(bookCreatureService.getFilteredBook(request, pageable));
    }

    @DeleteMapping("/remove-by-attack-level/{lvl}")
    public ResponseEntity<String> removeOneByAttackLevel(@PathVariable @PositiveOrZero Float lvl) {
        bookCreatureService.removeOneByAttackLevel(lvl);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/count-by-ring-id/{id}")
    public ResponseEntity<String> removeOneByAttackLevel(@PathVariable @PositiveOrZero Long id) {
        int count = bookCreatureService.countByRing(id);
        return ResponseEntity.ok().body(Integer.toString(count));
    }

    @GetMapping("/unique-attack-levels")
    public List<Float> removeOneByAttackLevel() {
        return bookCreatureService.getUniqueAttackLevels();
    }

    @PutMapping("/remove-hobbit-rings")
    public ResponseEntity<String> removeHobbitRings() {
        bookCreatureService.removeRingsFromHobbits();
        return ResponseEntity.ok().build();
    }


}
