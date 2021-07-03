package jaz.pjwstk.jaz20308nbp.controller;

import jaz.pjwstk.jaz20308nbp.model.EntryToDatabase;
import jaz.pjwstk.jaz20308nbp.service.AveragePriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/averagePrice")
public class AveragePriceController {
    private final AveragePriceService averagePriceService;

    public AveragePriceController(AveragePriceService averagePriceService) {
        this.averagePriceService = averagePriceService;
    }
    @GetMapping("/{currency}")
    public ResponseEntity<EntryToDatabase> getPriceFromDays(
            @PathVariable String currency,
            @RequestParam String startDate,
            @RequestParam String endDate
            ){
        return ResponseEntity.ok(averagePriceService.getPriceFromDays(currency,startDate,endDate));
    }

}
