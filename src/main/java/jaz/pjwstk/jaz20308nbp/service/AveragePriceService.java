package jaz.pjwstk.jaz20308nbp.service;

import jaz.pjwstk.jaz20308nbp.model.EntryToDatabase;
import jaz.pjwstk.jaz20308nbp.model.Rate;
import jaz.pjwstk.jaz20308nbp.model.Root;
import jaz.pjwstk.jaz20308nbp.repository.AveragePriceRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Service
public class AveragePriceService {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final RestTemplate restTemplate;
    private final AveragePriceRepository averagePriceRepository;

    public AveragePriceService(RestTemplate restTemplate, AveragePriceRepository averagePriceRepository) {
        this.restTemplate = restTemplate;
        this.averagePriceRepository = averagePriceRepository;
    }

    public EntryToDatabase getPriceFromDays(String currency, String startDate, String endDate) {
        Root root = restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/rates/a/" + currency + "/" + startDate + "/" + endDate, Root.class);
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        long difference = Duration.between(start.atStartOfDay(), end.atStartOfDay()).toDays();
        Double average = root
                .getRates().stream()
                .mapToDouble(Rate::getMid)
                .sum() / (difference);
        EntryToDatabase entryToDatabase = new EntryToDatabase(root.getCurrency(), startDate, endDate, average);
        averagePriceRepository.save(entryToDatabase);
        return entryToDatabase;
    }


}
