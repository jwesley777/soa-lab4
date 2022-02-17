package soa.mapper;

import soa.dto.CoordinatesDTO;
import soa.entity.Coordinates;
import org.springframework.stereotype.Service;
import soa.utils.FieldValidationUtil;

import java.util.ArrayList;

@Service
public class CoordinatesMapper {

    public Coordinates mapCoordinatesDTOToCoordinates(CoordinatesDTO coordinatesDTO) {
        Coordinates coordinates = new Coordinates();
        coordinates.setId(FieldValidationUtil.getIntegerFieldValue(coordinatesDTO.getId()));
        coordinates.setX(FieldValidationUtil.getDoubleFieldValue(coordinatesDTO.getX()));
        coordinates.setY(FieldValidationUtil.getDoubleFieldValue(coordinatesDTO.getY()));
        return coordinates;
    }

    public CoordinatesDTO mapCoordinatesToCoordinatesDTO(Coordinates coordinates) {
        CoordinatesDTO coordinatesDTO = new CoordinatesDTO();
        coordinatesDTO.setId(String.valueOf(coordinates.getId()));
        coordinatesDTO.setX(String.valueOf(coordinates.getX()));
        coordinatesDTO.setY(String.valueOf(coordinates.getY()));
        return coordinatesDTO;
    }

    public ArrayList<CoordinatesDTO> mapCoordinatesListToCoordinatesDTOList(ArrayList<Coordinates> coordinatesList) {
        ArrayList<CoordinatesDTO> coordinatesDTOList = new ArrayList<>();
        for (Coordinates coordinates : coordinatesList) {
            coordinatesDTOList.add(mapCoordinatesToCoordinatesDTO(coordinates));
        }
        return coordinatesDTOList;
    }
}
