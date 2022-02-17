package soa.mapper;

import soa.dto.LocationDTO;
import soa.entity.Location;
import org.springframework.stereotype.Service;
import soa.utils.FieldValidationUtil;

import java.util.ArrayList;

@Service
public class LocationMapper {
    public Location mapLocationDTOToLocation(LocationDTO locationDTO) {
        Location location = new Location();
        location.setId(FieldValidationUtil.getIntegerFieldValue(locationDTO.getId()));
        location.setX(FieldValidationUtil.getLongFieldValue(locationDTO.getX()));
        location.setY(FieldValidationUtil.getDoubleFieldValue(locationDTO.getY()));
        location.setName(FieldValidationUtil.getStringValue(locationDTO.getName()));
        return location;
    }

    public LocationDTO mapLocationToLocationDTO(Location location) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(String.valueOf(location.getId()));
        locationDTO.setName(location.getName());
        locationDTO.setX(String.valueOf(location.getX()));
        locationDTO.setY(String.valueOf(location.getY()));
        return locationDTO;
    }

    public ArrayList<LocationDTO> mapLocationListToLocationDTOList(ArrayList<Location> locationList) {
        ArrayList<LocationDTO> locationDTOArrayList = new ArrayList<>();
        for (Location location : locationList) {
            locationDTOArrayList.add(mapLocationToLocationDTO(location));
        }
        return locationDTOArrayList;
    }
}
