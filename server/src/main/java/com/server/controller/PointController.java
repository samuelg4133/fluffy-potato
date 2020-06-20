package com.server.controller;

import com.server.model.Item;
import com.server.model.Point;
import com.server.model.PointItems;
import com.server.model.dto.PointDTO;
import com.server.repository.ItemRepository;
import com.server.repository.PointItemsRepository;
import com.server.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/points")
@CrossOrigin("*")
public class PointController {

    @Autowired
    PointRepository pointRepository;

    @Autowired
    PointItemsRepository pointItemsRepository;

    @Autowired
    ItemRepository itemRepository;

    @GetMapping
    public ResponseEntity<List<PointDTO>> index(@RequestParam(required = false) String city,
                                                @RequestParam(required = false) String uf,
                                                @RequestParam(required = false) Long items) {
        List<Point> pointList = pointRepository.getDistinctByCityOrUfOrOrItems(city, uf, items);

        List<PointDTO> pointDTOList = new ArrayList<>();

        for (Point point : pointList) {
            PointDTO pointDTO = new PointDTO(point);

            pointDTO.setItems(itemRepository.findPointItems(point.getId()));
            pointDTOList.add(pointDTO);
        }

        return new ResponseEntity<>(pointDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PointDTO> show(@PathVariable("id") Long id) {
        Point point = pointRepository.getById(id);

        PointDTO pointDTO = new PointDTO(point);

        pointDTO.setItems(itemRepository.findPointItems(id));

        return new ResponseEntity<>(pointDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Point> create(@RequestBody @Valid Point point) {

        point.setImage("fake-image");

        pointRepository.save(point);

        point.getItems().forEach(
                item -> {
                    PointItems pointItems = new PointItems();
                    Item i = itemRepository.getById(item);

                    if (i != null) {
                        pointItems.setItem(i);
                        pointItems.setPoint(point);
                        pointItemsRepository.save(pointItems);
                    } else {
                        return;
                    }
                }
        );

        return new ResponseEntity<>(point, HttpStatus.OK);
    }

}
