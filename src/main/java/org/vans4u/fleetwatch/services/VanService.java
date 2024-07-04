package org.vans4u.fleetwatch.services;

import org.vans4u.fleetwatch.domain.Van;

import java.util.List;
import java.util.Optional;

public interface VanService {

    Optional<Van> getVanById(long id);

    List<Van> listAllVans();

    Van addVan(Van van);

    boolean deleteVanById(long id);

    boolean updateMileage(long id, int mileage);

}
