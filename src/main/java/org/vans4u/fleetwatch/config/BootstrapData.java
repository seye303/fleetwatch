package org.vans4u.fleetwatch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.vans4u.fleetwatch.domain.Van;
import org.vans4u.fleetwatch.domain.VanType;
import org.vans4u.fleetwatch.repositories.VanRepository;

import static org.vans4u.fleetwatch.domain.VanManufacturer.FORD;
import static org.vans4u.fleetwatch.domain.VanManufacturer.MERCEDES;

@Configuration
@Profile("dev")
public class BootstrapData {

    private static final Logger logger = LoggerFactory.getLogger(BootstrapData.class);

    @Bean
    CommandLineRunner initDatabase(VanRepository repo) {
        return args -> {
            logger.debug("loading {}", repo.save(
                new Van(FORD, VanType.MEDIUM, "LJ73 OPQ", 23123)));
            logger.debug("loading {}", repo.save(
                new Van(FORD, VanType.MEDIUM, "RM73 LTM", 120210)));
            logger.debug("loading {}", repo.save(
                new Van(MERCEDES, VanType.MEDIUM, "PR72 ONO", 50000)));
            logger.debug("loading {}", repo.save(
                new Van(MERCEDES, VanType.MEDIUM, "SC72 EMS", 40000)));
            logger.debug("loading {}", repo.save(
                new Van(MERCEDES, VanType.MEDIUM, "OJ70 RTX", 30000)));
        };
    }

}
