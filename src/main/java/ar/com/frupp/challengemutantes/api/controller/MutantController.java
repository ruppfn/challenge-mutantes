package ar.com.frupp.challengemutantes.api.controller;

import ar.com.frupp.challengemutantes.api.entity.DnaRequestEntity;
import ar.com.frupp.challengemutantes.api.exception.NitrogenousBaseNotValidException;
import ar.com.frupp.challengemutantes.api.helper.DnaValidator;
import ar.com.frupp.challengemutantes.api.model.RequestModel;
import ar.com.frupp.challengemutantes.api.service.impl.RequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(
        value = "mutant",
        consumes = "Application/json",
        produces = "Application/json")
public class MutantController {

    @Autowired
    RequestServiceImpl service;

    @PostMapping
    public ResponseEntity<?> validateMutant(@RequestBody RequestModel request) {
        String[] dnaMatrix = request.getDna();

        List<String> validations = DnaValidator.getValidationList(dnaMatrix);

        if (validations.size() > 0) {
            return ResponseEntity.badRequest().build();
        }

        boolean isMutant;
        try {
            isMutant = DnaValidator.isMutant(dnaMatrix);
        } catch (NitrogenousBaseNotValidException e) {
            return ResponseEntity.badRequest().build();
        }

        DnaRequestEntity entity = new DnaRequestEntity(isMutant, dnaMatrix);
        service.save(entity);

        if (isMutant) {
            return ResponseEntity.ok(entity);
        } else {
            return ResponseEntity.status(403).body(entity);
        }
    }

}
