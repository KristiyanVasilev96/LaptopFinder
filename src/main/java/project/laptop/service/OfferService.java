package project.laptop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.laptop.exceptions.ObjectNotFoundException;
import project.laptop.model.dto.offerDTO.CreateOrUpdateOfferDto;
import project.laptop.model.dto.offerDTO.EditOfferDto;
import project.laptop.model.dto.offerDTO.OfferDetailsDto;
import project.laptop.model.dto.offerDTO.SearchOfferDto;
import project.laptop.model.entity.ModelEntity;
import project.laptop.model.entity.OfferEntity;
import project.laptop.model.entity.UserEntity;
import project.laptop.model.entity.enums.UserRoleEnum;
import project.laptop.model.mapper.OfferMapper;
import project.laptop.repository.ModelRepository;
import project.laptop.repository.OfferRepository;
import project.laptop.repository.OfferSpecifications;
import project.laptop.repository.UserRepository;
import project.laptop.scheduling.SchedulerUpdatePrice;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;

    private final ImageCloudService imageCloudService;
    private Logger LOGGER= LoggerFactory.getLogger(SchedulerUpdatePrice.class);

    public OfferService(OfferRepository offerRepository, OfferMapper offerMapper, UserRepository userRepository, ModelRepository modelRepository, ImageCloudService imageCloudService) {
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.imageCloudService = imageCloudService;
    }

    public void addOffer(CreateOrUpdateOfferDto addOfferDto, UserDetails userDetails,MultipartFile imageFile) {
        String pictureUrl = imageCloudService.saveImage(imageFile);
        OfferEntity newOffer = offerMapper.createOrUpdateOfferDtoToOfferEntity(addOfferDto);

        UserEntity seller = userRepository.findByEmail(userDetails.getUsername()).orElse(null);

        ModelEntity model = modelRepository.findById(addOfferDto.getModelId()).orElse(null);

        newOffer.setModel(model);
        newOffer.setSeller(seller);
        newOffer.setImageUrl(pictureUrl);

        offerRepository.save(newOffer);
    }

    public Page<OfferDetailsDto> getAllOffers(Pageable pageable) {
        return offerRepository.findAll(pageable)
                .map(offerMapper::offerEntityToOfferDetailDto);
    }

//    Optional<CreateOrUpdateOfferDto> getOfferEditDetails(Long offerId){
//        return offerRepository.findById(offerId).map(offerMapper::offerEntityToCreateOrUpdateOfferDtoTo);
//    }

    public Optional<OfferDetailsDto> findOfferById(Long id) {
        return offerRepository.findById(id).map(offerMapper::offerEntityToOfferDetailDto);
    }

    public void deleteOfferById(Long id) {
        offerRepository.deleteById(id);
    }

    public boolean isOwner(String userName, Long id) {
        boolean isOwner = offerRepository.findById(id)
                .filter(o -> o.getSeller().getEmail().equals(userName)).isPresent();

        if (isOwner) {
            return true;
        }

        return userRepository
                .findByEmail(userName)
                .filter(this::isAdmin)
                .isPresent();
        //check if owner or admin
    }

    private boolean isAdmin(UserEntity user) {
        return user.getUserRoles()
                .stream().anyMatch(r -> r.getUserRole() == UserRoleEnum.ADMIN);
    }

    public List<OfferDetailsDto> searchOffer(SearchOfferDto searchOfferDto) {
        return this.offerRepository.findAll(new OfferSpecifications(searchOfferDto))
                .stream().map(offerMapper::offerEntityToOfferDetailDto)
                .toList();
    }


    public void edit(Long id, EditOfferDto editOfferDto) {
        OfferEntity editOffer = offerRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("I messed up"));
        editOffer.setPrice(editOfferDto.getPrice())
                .setRam(editOfferDto.getRam())
                .setWeight(editOfferDto.getWeight())
                .setImageUrl(editOfferDto.getImageUrl())
                .setOperationSystem(editOfferDto.getOperationSystem())
                .setLaptopCondition(editOfferDto.getLaptopCondition())
                .setDescription(editOfferDto.getDescription());


        offerRepository.save(editOffer);

    }



    public OfferEntity findById(Long offerId) {
        return offerRepository.findById(offerId).orElse(null);
    }


    public void updatePrices() {
        List<OfferEntity> offers = offerRepository.findAll();
        for (OfferEntity offer : offers) {
            BigDecimal oldPrice = offer.getPrice();
            BigDecimal newPrice = oldPrice.multiply(BigDecimal.valueOf(0.95));//reduce price by 5%
            offer.setPrice(newPrice);
            offerRepository.save(offer);
            if (offer.getId() == 1) {
                LOGGER.info("Updated price of offer with id 1: {}", newPrice);
            }
        }

    }
}
