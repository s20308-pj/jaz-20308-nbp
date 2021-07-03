package jaz.pjwstk.jaz20308nbp.repository;

import jaz.pjwstk.jaz20308nbp.model.EntryToDatabase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AveragePriceRepository extends JpaRepository<EntryToDatabase, Long> {
}
