package ar.com.frupp.challengemutantes.api.controller;

import ar.com.frupp.challengemutantes.api.exception.NitrogenousBaseNotValidException;
import ar.com.frupp.challengemutantes.api.helper.DnaValidator;
import ar.com.frupp.challengemutantes.api.model.RequestModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("mutant")
public class MutantController {

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

        if (isMutant) {
            return ResponseEntity.ok().build();
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

}
