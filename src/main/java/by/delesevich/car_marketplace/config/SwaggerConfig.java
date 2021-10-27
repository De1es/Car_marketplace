package by.delesevich.car_marketplace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  public static final String ADMIN_LOT_TAG = "admin lots controller";
  public static final String ADMIN_USER_TAG = "admin users controller";
  public static final String USER_LOT_TAG = "user lots controller";

  @Bean
  public Docket configure () {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .paths(PathSelectors.regex("/api/.*"))
        .apis(RequestHandlerSelectors.basePackage("by.delesevich.car_marketplace.controller"))
        .build()
        .apiInfo(apiInfo())
        .tags(new Tag(ADMIN_LOT_TAG, "Lot controller only for admin access"))
        .tags(new Tag(ADMIN_USER_TAG, "Users controller only for admin access"))
        .tags(new Tag(USER_LOT_TAG, "Lot controller only for user access"));
  }

  public ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("Demo app - car sales platform").description("It's Task 002" +
        " from tasks list.\nAccess rights:\n     Admin: login Admin - pass admin\n     User: " +
        "login Nick - pass user\nAuthor: Andrei Delesevich delesevich@mail.ru +375 (29) " +
        "795-71-91").build();
  }

}
