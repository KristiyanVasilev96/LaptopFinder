package project.laptop.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import project.laptop.model.dto.offerDTO.OfferDetailsDto;
import project.laptop.repository.OfferRepository;
import project.laptop.service.OfferService;

import java.util.List;

@Component
public class SchedulerUpdatePrice {



    private final OfferService offerService;

    public SchedulerUpdatePrice(OfferService offerService) {

        this.offerService = offerService;
    }

    @Scheduled(cron = "0 30 15 * * ?")//15;30 once a day
    public void updatePrice(){
       offerService.updatePrices();
    }


}
