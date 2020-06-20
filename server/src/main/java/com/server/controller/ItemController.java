package com.server.controller;

import com.server.model.Item;
import com.server.model.dto.ItemDTO;
import com.server.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("items")
@CrossOrigin("http://localhost:3000")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping
    public ResponseEntity<List<ItemDTO>> indexItems() {
        List<Item> items = itemRepository.findAll();

        List<ItemDTO> list = new ArrayList<>();

        for (Item item : items
        ) {
            ItemDTO itemDTO = new ItemDTO(item);
            list.add(itemDTO);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
