package ar.com.frupp.challengemutantes.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DNA_REQUEST")
@Getter @Setter
public class DnaRequestEntity {

    @Id
    private int id;

    private boolean isMutant;

    private String dna;

    public DnaRequestEntity(boolean isMutant, String[] dnaMatrix){
        String totalDna = "";

        for (String dna : dnaMatrix){
            if(totalDna != ""){ totalDna += ",";}
            totalDna += dna;
        }

        this.isMutant = isMutant;
        this.dna = totalDna;
    }

    public String[] getDnaAsArray(){
        return this.dna.split(",");
    }
}
