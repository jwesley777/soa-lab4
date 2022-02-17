package soa.webservices;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import soa.ApplicationContextProvider;
import soa.dto.CoordinatesDTO;
import soa.dto.dtoList.CoordinatesDTOList;
import soa.entity.Coordinates;
import soa.exceptions.WSFault;
import soa.mapper.CoordinatesMapper;
import soa.mapper.TicketMapper;
import soa.services.CoordinatesService;
import soa.services.TicketService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;

@Service
@WebService
public class CoordinatesWebService {
    private final CoordinatesService coordinatesService;
    private final CoordinatesMapper coordinatesMapper;

    public CoordinatesWebService() {
        this.coordinatesService = ApplicationContextProvider.getApplicationContext().getBean(CoordinatesService.class);;
        this.coordinatesMapper = ApplicationContextProvider.getApplicationContext().getBean(CoordinatesMapper.class);;
    }
    public CoordinatesWebService(CoordinatesMapper coordinatesMapper,
                                 CoordinatesService coordinatesService) {
        this.coordinatesService = coordinatesService;
        this.coordinatesMapper = coordinatesMapper;
    }

    @WebMethod
    public CoordinatesDTOList getCoordinates() throws WSFault {
        try {
            ArrayList<Coordinates> coordinates = coordinatesService.getCoordinates();
            CoordinatesDTOList dto = new CoordinatesDTOList(new ArrayList<>());
            dto.setCoordinatesList(coordinatesMapper.mapCoordinatesListToCoordinatesDTOList(coordinates));
            return dto;
        } catch (RuntimeException e) {
            throw new WSFault(e.getMessage(), 400);
        }
    }

    @WebMethod
    public CoordinatesDTOList getCoordinate(Integer id) throws WSFault {
        try {
            Coordinates coordinates = coordinatesService.getCoordinatesById(id);
            CoordinatesDTOList dto = new CoordinatesDTOList(new ArrayList<>());
            ArrayList<CoordinatesDTO> dtoList = new ArrayList<>();
            dtoList.add(coordinatesMapper.mapCoordinatesToCoordinatesDTO(coordinates));
            dto.setCoordinatesList(dtoList);
            return dto;
        } catch (RuntimeException e) {
            throw new WSFault(e.getMessage(), 400);
        }
    }


    @WebMethod
    public void createCoordinate(CoordinatesDTOList coordinatesDTOList) throws WSFault {
        try {
            Coordinates coordinatesToPersist = coordinatesMapper.mapCoordinatesDTOToCoordinates(coordinatesDTOList.getCoordinatesList().get(0));
            coordinatesService.createCoordinates(coordinatesToPersist);
        } catch (RuntimeException e) {
            throw new WSFault(e.getMessage(), 400);
        }
    }

    @WebMethod
    public void updateCoordinate(CoordinatesDTOList coordinatesDTOList) throws WSFault {
        try {
            Coordinates coordinatesToUpdate = coordinatesMapper.mapCoordinatesDTOToCoordinates(coordinatesDTOList.getCoordinatesList().get(0));
            coordinatesService.updateCoordinates(coordinatesToUpdate);
        } catch (RuntimeException e) {
            throw new WSFault(e.getMessage(), 400);
        }
    }
}
