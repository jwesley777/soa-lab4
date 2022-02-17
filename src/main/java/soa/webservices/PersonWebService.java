package soa.webservices;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import soa.ApplicationContextProvider;
import soa.dto.PersonDTO;
import soa.dto.dtoList.PersonDTOList;
import soa.entity.Person;
import soa.exceptions.WSFault;
import soa.mapper.LocationMapper;
import soa.mapper.PersonMapper;
import soa.services.LocationService;
import soa.services.PersonService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;

@Service
@WebService
public class PersonWebService {
    private final PersonService personService;
    private final PersonMapper personMapper;

    public PersonWebService(PersonService personService,
                            PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    public PersonWebService() {
        this.personService = ApplicationContextProvider.getApplicationContext().getBean(PersonService.class);;
        this.personMapper = ApplicationContextProvider.getApplicationContext().getBean(PersonMapper.class);;
    }

    @WebMethod
    public PersonDTOList getPerson(@PathVariable(name = "id") Integer id) throws WSFault {
        try {
            Person person = personService.getPersonById(id);
            PersonDTOList dto = new PersonDTOList(new ArrayList<>());
            ArrayList<PersonDTO> dtoList = dto.getPersonsList();
            dtoList.add(personMapper.mapPersonToPersonDTO(person));
            return dto;
        } catch (RuntimeException e) {
            throw new WSFault(e.getMessage(), 400);
        }
    }

    @WebMethod
    public PersonDTOList getPersons() throws WSFault {
        try {
            ArrayList<Person> personList = personService.getPerson();
            PersonDTOList dto = new PersonDTOList(new ArrayList<>());
            dto.setPersonsList(personMapper.mapPersonListToPersonDTOList(personList));
            return dto;
        }  catch (RuntimeException e) {
            throw new WSFault(e.getMessage(), 400);
        }
    }


    @WebMethod
    public void createPerson(PersonDTOList personDTOList) throws WSFault {
        try {
            Person personToPersist = personMapper.mapPersonDTOToPerson(personDTOList.getPersonsList().get(0));
            personService.createPerson(personToPersist);
        } catch (RuntimeException e) {
            throw new WSFault(e.getMessage(), 400);
        }
    }

    @WebMethod
    public void updatePerson(PersonDTOList personDTOList) throws WSFault {
        try {
            Person personToUpdate = personMapper.mapPersonDTOToPerson(personDTOList.getPersonsList().get(0));
            personService.updatePerson(personToUpdate);
        } catch (RuntimeException e) {
            throw new WSFault(e.getMessage(), 400);
        }
    }


}
