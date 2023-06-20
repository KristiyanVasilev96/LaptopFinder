package project.laptop.util;

import org.springframework.stereotype.Component;
import project.laptop.model.entity.*;
import project.laptop.model.entity.enums.CategoryEnum;
import project.laptop.model.entity.enums.ConditionEnum;
import project.laptop.model.entity.enums.OperationSystem;
import project.laptop.model.entity.enums.UserRoleEnum;
import project.laptop.repository.*;

import java.math.BigDecimal;

@Component
public class TestDataUtil {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;

    public TestDataUtil(UserRepository userRepository, UserRoleRepository userRoleRepository, OfferRepository offerRepository, ModelRepository modelRepository, BrandRepository brandRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setUserRole(UserRoleEnum.USER);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(userRole);
        }
    }

    public UserEntity createTestAdmin(String email) {

        initRoles();

        var admin = new UserEntity().

                setEmail(email).
                setFirstName("Kristiyan").
                setLastName("Vasilev").
                setPassword("12345").
                setUserRoles(userRoleRepository.findAll());



        return userRepository.save(admin);
    }

    public UserEntity createTestUser(String email) {

        initRoles();

        var user = new UserEntity().
                setEmail(email).
                setFirstName("User").
                setLastName("Userov")
                .setPassword("12345").

                setUserRoles(userRoleRepository.
                        findAll().stream().
                        filter(r -> r.getUserRole() != UserRoleEnum.ADMIN).
                        toList());

        return userRepository.save(user);
    }

    public OfferEntity createTestOffer(UserEntity seller,
                                       ModelEntity model) {
        var offerEntity = new OfferEntity()
                .setPrice(BigDecimal.TEN)
                .setSeller(seller)
                .setModel(model)
                .setRam(8)
                .setLaptopCondition(ConditionEnum.EXCELLENT)
                .setOperationSystem(OperationSystem.Windows_10)
                .setWeight(BigDecimal.TEN);

        return offerRepository.save(offerEntity);
    }

    public BrandEntity createTestBrand() {
        var brandEntity = new BrandEntity().
                setName("ASUS");

        return brandRepository.save(brandEntity);
    }

    public ModelEntity createTestModel(BrandEntity brandEntity) {
        ModelEntity model = new ModelEntity().
                setName("ROG").
                setBrand(brandEntity).
                setCategory(CategoryEnum.GAMING).
                setImageUrl("http://image.com/image.png");

        return modelRepository.save(model);
    }

    public void cleanUpDatabase() {
        offerRepository.deleteAll();
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
        modelRepository.deleteAll();
        brandRepository.deleteAll();
    }
}