package org.vans4u.fleetwatch.services;

import org.springframework.stereotype.Service;
import org.vans4u.fleetwatch.domain.Van;
import org.vans4u.fleetwatch.repositories.VanRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VanServiceJPA implements VanService {

    private final VanRepository repository;

    public VanServiceJPA(VanRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Van> getVanById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Van> listAllVans() {
        return repository.findAll();
    }

    @Override
    public Van addVan(Van van) {
        return repository.save(van);
    }

    @Override
    public boolean deleteVanById(long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateMileage(long id, int mileage) {
        var optional = repository.findById(id);
        if(optional.isPresent()) {
            var van = optional.get();
            if(mileage < van.getMileage()) {
                throw new BadVanServiceRequestException(String.format(
                    "Cannot set mileage of %d as it's less than current value of %d",
                    mileage, van.getMileage()));
            } else {
                van.setMileage(mileage);
                repository.save(van);
                return true;
            }
        } else {
            return false;
        }
    }
}
