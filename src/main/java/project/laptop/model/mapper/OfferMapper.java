package project.laptop.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;
import project.laptop.model.dto.offerDTO.CreateOrUpdateOfferDto;
import project.laptop.model.dto.offerDTO.OfferDetailsDto;
import project.laptop.model.entity.OfferEntity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Mapper(componentModel = "spring")
public interface OfferMapper {
    @Mapping(target = "imageUrl", source = "imageUrl", qualifiedByName = "mapMultipartFileToString")
    OfferEntity createOrUpdateOfferDtoToOfferEntity(CreateOrUpdateOfferDto addOfferDto);


    @Named("mapMultipartFileToString")
    default String mapMultipartFileToString(MultipartFile file) throws IOException {
        return new String(file.getBytes(), StandardCharsets.UTF_8);
    }

//    CreateOrUpdateOfferDto offerEntityToCreateOrUpdateOfferDtoTo(OfferEntity offerEntity);

    @Mapping(source = "model.name", target = "model")
    @Mapping(source = "model.brand.name", target = "brand")
    @Mapping(source = "seller.firstName", target = "sellerFirstName")
    @Mapping(source = "seller.lastName", target = "sellerLastName")
    OfferDetailsDto offerEntityToOfferDetailDto(OfferEntity offerEntity);
}
