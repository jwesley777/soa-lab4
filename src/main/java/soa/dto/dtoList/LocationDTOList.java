package soa.dto.dtoList;

import soa.dto.LocationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class LocationDTOList {
    @XmlElementWrapper(name = "locations")
    @XmlElement(name = "location")
    private ArrayList<LocationDTO> locationsList;
}
