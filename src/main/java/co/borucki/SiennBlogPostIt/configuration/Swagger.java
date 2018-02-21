package co.borucki.SiennBlogPostIt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class Swagger {
    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("SiennBlogPostIt")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/api/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Sienn BlogPostIt Api")
                .description("Api allows you to: " +
                        "\n- show all PostIt" +
                        "\n- show the best rated author in each category" +
                        "\n- show postIt header" +
                        "\n- like PostIt" +
                        "\n- create new PostIt")
                .contact("Łukasz Borucki")
                .license("GPL")
                .licenseUrl("http://www.brucki.co/SiennBlogPostIt-licence")
                .version("1.0")
                .build();
    }
}
