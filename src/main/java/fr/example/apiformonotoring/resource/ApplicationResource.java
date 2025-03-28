package fr.example.apiformonotoring.resource;

import fr.example.apiformonotoring.model.ApplicationDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/application")
@Slf4j
public class ApplicationResource {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.application.version}")
    private String applicationVersion;


    @GetMapping("/details")
    public ApplicationDetails getApplicationDetails() {

        log.info("Application : {} version : {} is UP", applicationName, applicationVersion);

        return ApplicationDetails.builder()
                .name(applicationName)
                .version(applicationVersion)
                .build();
    }
}