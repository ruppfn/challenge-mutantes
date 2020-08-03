package ar.com.frupp.challengemutantes.api.service.impl;

import ar.com.frupp.challengemutantes.api.entity.DnaRequestEntity;
import ar.com.frupp.challengemutantes.api.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl {

    @Autowired
    RequestRepository repository;

    public void save(DnaRequestEntity entity){
        repository.save(entity);
    }

    public int countMutantDna(){
        return repository.countMutantDna();
    }

    public int countNonMutantDna(){
        return repository.countNonMutantDna();
    }
}
