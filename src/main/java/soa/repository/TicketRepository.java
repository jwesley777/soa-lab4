package soa.repository;

import soa.entity.Ticket;
import soa.enums.TicketType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Integer>, TicketRepositoryCustom{
    Optional<Ticket> findById(Integer id);

    void deleteTicketById(Integer id);

    Long countAllByPerson_IdLessThan(Integer id);

    ArrayList<Ticket> findAllByDiscountLessThan(Long discount);

    @Query("SELECT DISTINCT t.type FROM Ticket t")
    ArrayList<TicketType> getDistinctTypes();


}
