package ar.com.frupp.challengemutantes.api.helper;

import ar.com.frupp.challengemutantes.api.exception.NitrogenousBaseNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class DnaValidator {

    private static boolean rowIsMutant(String dnaSequence) {
        int cantidadRepetidos = 0;
        char letraActual = dnaSequence.charAt(0);

        for (int i = 1; i < dnaSequence.length(); i++) {

            if (cantidadRepetidos > 3) {
                break;
            }

            if (dnaSequence.charAt(i) == letraActual) {
                cantidadRepetidos++;
            } else {
                letraActual = dnaSequence.charAt(i);
                cantidadRepetidos = 1;
            }
        }

        return cantidadRepetidos >= 4 ? true : false;
    }

    private static boolean columnIsMutant(String[] dnaMatrix, int dnaSequenceNumber, int charNumber) {
        int cantidadRepetidos = 0;
        char letraActual = dnaMatrix[dnaSequenceNumber].charAt(charNumber);

        for (int i = 0; i + dnaSequenceNumber < dnaMatrix.length; i++) {
            if (dnaMatrix[dnaSequenceNumber + i].charAt(charNumber) == letraActual) {
                cantidadRepetidos++;
            } else {
                break;
            }
        }

        return cantidadRepetidos >= 4 ? true : false;
    }

    private static boolean diagonalIsMutant(String[] dnaMatrix, int dnaSequenceNumber, int charNumber) {

        char letraActual = dnaMatrix[dnaSequenceNumber].charAt(charNumber);
        int cantidadRepetidos = 0;

        for (int i = 0; i + dnaSequenceNumber < dnaMatrix.length && i + charNumber < dnaMatrix[dnaSequenceNumber].length(); i++) {
            if (dnaMatrix[dnaSequenceNumber + i].charAt(charNumber + i) == letraActual) {
                cantidadRepetidos++;
            } else {
                break;
            }
        }

        return cantidadRepetidos >= 4 ? true : false;
    }

    private static boolean inverseDiagonalIsMutant(String[] dna, int i, int j) {
        char letraActual = dna[i].charAt(j);
        int cantidadRepetidos = 0;

        for (int k = 0; i + k < dna.length && j - k >= 0; k++) {
            if (dna[i + k].charAt(j - k) == letraActual) {
                cantidadRepetidos++;
            } else {
                break;
            }
        }

        return cantidadRepetidos >= 4 ? true : false;
    }

    public static boolean isMutant(String[] dnaMatrix) throws NitrogenousBaseNotValidException {
        boolean esMutante = false;

        // Iteracion sobre strings
        for (int i = 0; i < dnaMatrix.length; i++) {

            //Si no esta compuesto solo de ATCG no es valido
            Pattern pattern = Pattern.compile("[ATCG]+", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(dnaMatrix[i]);
            boolean isValid = matcher.matches();

            if(!isValid){
                throw new NitrogenousBaseNotValidException(dnaMatrix[i]);
            }

            if (rowIsMutant(dnaMatrix[i])) {
                esMutante = true;
                break;
            }

            // Iteracion sobre chars
            for (int j = 0; j < dnaMatrix[i].length(); j++) {
                if (columnIsMutant(dnaMatrix, i, j) || inverseDiagonalIsMutant(dnaMatrix, i, j) || diagonalIsMutant(dnaMatrix, i, j)) {
                    esMutante = true;
                    break;
                }
            }
        }
            return esMutante;
    }

    public static List<String> getValidationList(String[] dnaMatrix){
        List<String> validations = new ArrayList<>();

        if(dnaMatrix == null){
            validations.add("Dna can't be null or empty");
            return validations;
        }

        // valido que sea NxN
        for (String dna: dnaMatrix) {
            if(dna.length() != dnaMatrix.length){
                validations.add("The matrix must be NxN");
                break;
            }
        }

        return validations;
    }
}
