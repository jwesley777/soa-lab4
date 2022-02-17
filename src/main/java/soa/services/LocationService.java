package soa.services;

import soa.entity.Location;
import org.springframework.stereotype.Service;
import soa.repository.LocationRepository;
import soa.validation.EntityValidator;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final EntityValidator entityValidator;

    public LocationService(LocationRepository locationRepository, EntityValidator entityValidator) {
        this.locationRepository = locationRepository;
        this.entityValidator = entityValidator;
    }

    public Location getLocationById(Integer id) {
        return locationRepository.findById(id).orElseThrow(NoResultException::new);
    }

    public ArrayList<Location> getLocation() {
        return locationRepository.findAll();
    }

    @Transactional
    public void updateLocation(Location locationToUpdate) {
        locationRepository.findById(locationToUpdate.getId())
                .orElseThrow(() -> new NoResultException(" Location with id " + locationToUpdate.getId() + " does not exist"));
        entityValidator.validateLocation(locationToUpdate);
        locationRepository.save(locationToUpdate);
    }

    @Transactional
    public void createLocation(Location locationToPersist) {
        entityValidator.validateLocation(locationToPersist);
        locationRepository.save(locationToPersist);
    }
}