package ar.com.frupp.challengemutantes.api.repository;

import ar.com.frupp.challengemutantes.api.entity.DnaRequestEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends CrudRepository<DnaRequestEntity, Integer> {

    @Query(
            value = "COUNT(1) FROM DNA_REQUEST R WHERE R.IS_MUTANT = 1",
            nativeQuery = true
    )
    int countMutantDna();

    @Query(
            value = "COUNT(1) FROM DNA_REQUEST R WHERE R.IS_MUTANT = 0",
            nativeQuery = true
    )
    int countNonMutantDna();
}
