package soa.webservices;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import soa.ApplicationContextProvider;
import soa.dto.dtoList.LocationDTOList;
import soa.entity.Location;
import soa.exceptions.WSFault;
import soa.mapper.CoordinatesMapper;
import soa.mapper.LocationMapper;
import soa.repository.LocationRepository;
import soa.services.CoordinatesService;
import soa.services.LocationService;
import soa.validation.EntityValidator;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;

@Service
@WebService
public class LocationWebService {
    private final LocationMapper locationMapper;
    private final LocationService locationService;

    public LocationWebService(LocationMapper locationMapper,
                              LocationService locationService) {
        this.locationService = locationService;
        this.locationMapper = locationMapper;
    }

    public LocationWebService() {
        this.locationMapper = ApplicationContextProvider.getApplicationContext().getBean(LocationMapper.class);;
        this.locationService = ApplicationContextProvider.getApplicationContext().getBean(LocationService.class);;
    }

    @WebMethod
    public LocationDTOList getLocation(Integer id) throws WSFault {
        try {
            Location location = locationService.getLocationById(id);
            LocationDTOList dto = new LocationDTOList(new ArrayList<>());
            dto.getLocationsList().add(locationMapper.mapLocationToLocationDTO(location));
            return dto;
        } catch (RuntimeException e) {
            throw new WSFault(e.getMessage(), 400);
        }
    }

    @WebMethod
    public LocationDTOList getLocations() throws WSFault {
        try {
            ArrayList<Location> locations = locationService.getLocation();
            LocationDTOList dto = new LocationDTOList(new ArrayList<>());
            dto.setLocationsList(locationMapper.mapLocationListToLocationDTOList(locations));
            return dto;
        } catch (RuntimeException e) {
            throw new WSFault(e.getMessage(), 400);
        }
    }

    @WebMethod
    public void createLocation(LocationDTOList locationDTOList) throws WSFault {
        try {
            Location locationToPersist = locationMapper.mapLocationDTOToLocation(locationDTOList.getLocationsList().get(0));
            locationService.createLocation(locationToPersist);
        } catch (RuntimeException e) {
            throw new WSFault(e.getMessage(), 400);
        }
    }

    @WebMethod
    public void updateLocation(LocationDTOList locationDTOList) throws WSFault {
        try {
            Location locationToUpdate = locationMapper.mapLocationDTOToLocation(locationDTOList.getLocationsList().get(0));
            locationService.updateLocation(locationToUpdate);
        } catch (RuntimeException e) {
            throw new WSFault(e.getMessage(), 400);
        }
    }

}
