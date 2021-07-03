package jaz.pjwstk.jaz20308nbp.service;

import jaz.pjwstk.jaz20308nbp.model.EntryToDatabase;
import jaz.pjwstk.jaz20308nbp.model.Rate;
import jaz.pjwstk.jaz20308nbp.model.Root;
import jaz.pjwstk.jaz20308nbp.repository.AveragePriceRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
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
//        System.out.println(startDate.getTime()-endDate.getTime());
        Double average = 0D;//TBD
//        Double average = root
//                .getRates().stream()
//                .mapToDouble(Rate::getMid)
//                .sum() / (startDate.getTime()-endDate.getTime());
        EntryToDatabase entryToDatabase = new EntryToDatabase(root.getCurrency(), startDate, endDate, average);
        averagePriceRepository.save(entryToDatabase);
        return entryToDatabase;
    }


}
