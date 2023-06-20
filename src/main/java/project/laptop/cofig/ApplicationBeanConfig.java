package project.laptop.cofig;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;


@Configuration

public class ApplicationBeanConfig {


    @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();
    }

}
