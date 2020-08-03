package ar.com.frupp.challengemutantes.api.model;

public class StatsModel {

    private int countMutantDna;
    private int countNonMutantDna;
    private float ratio;

    public StatsModel(int countMutantDna, int countNonMutantDna) throws ArithmeticException{
        if(countNonMutantDna == 0){throw new ArithmeticException();}

        this.countMutantDna = countMutantDna;
        this.countNonMutantDna = countNonMutantDna;

        this.ratio = countMutantDna/countNonMutantDna;
    }
}
