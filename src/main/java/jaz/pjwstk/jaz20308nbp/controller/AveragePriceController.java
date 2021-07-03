package jaz.pjwstk.jaz20308nbp.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "get currency price form NBP API",
            response = Root.class,
            notes = "This method get average price from any start and end date, and insert data to database. If not found give 404. ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "404 NotFound"),
            @ApiResponse(code = 400, message = "400 Bad Request")
    })
    @GetMapping("/{currency}")
    public ResponseEntity<EntryToDatabase> getPriceFromDays(
            @ApiParam(name = "currency",
                    value = "Currency code(usd)",
                    required = true,
                    type = "String")
            @PathVariable String currency,
            @ApiParam(name = "startDate",
                    value = "date (2000-01-01",
                    required = true,
                    type = "String")
            @RequestParam String startDate,
            @ApiParam(name = "endDate",
                    value = "date (2000-01-01",
                    required = true,
                    type = "String")
            @RequestParam String endDate
            ){
        return ResponseEntity.ok(averagePriceService.getPriceFromDays(currency,startDate,endDate));
    }

}
