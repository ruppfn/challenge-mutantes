package ar.com.frupp.challengemutantes.api.controller;

import ar.com.frupp.challengemutantes.api.model.StatsModel;
import ar.com.frupp.challengemutantes.api.service.impl.RequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    RequestServiceImpl service;

    @GetMapping
    public ResponseEntity<?> getStats(){
        int mutantCount = service.countMutantDna();
        int nonMutantCount = service.countNonMutantDna();

        if(nonMutantCount == 0){
            return ResponseEntity.noContent().build();
        }

        StatsModel model = new StatsModel(mutantCount, nonMutantCount);

        return ResponseEntity.ok(model);
    }
}
