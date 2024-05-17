package com.abimelecperez.metaheuristics.de;

/**
 * <b>Configurator</b> class creates a new configuration of the TS-MBFOA 
 * own parameters. <br> With its methods, the following are adjusted.
 * <ol>
 * <li> Sb is the number of bacteria </li>
 * <li> Nc is the number of chemotaxis cycles </li>
 * <li>&beta; is the scaling factor </li>
 * <li> Sr is the number of bacteria to reproduce</li>
 * <li> Repcycle is the reproduction frequency </li>
 * <li> GMAX is the number of generations</li>
 * </ol>
 * 
 * This class inherits the mechanisms of the Population class.
 * 
 * @author <b>Adrian García-López</b><br>
 *         <a href="https://github.com/garcialopez" target="_blank">JMetaBFOP on GitHub</a><br>
 *         <a href="mailto:joseadrian_g97@hotmail.com">joseadrian_g97@hotmail.com</a><br>
 * @version 1.0
 */


public class Configurator extends Population{

    private int nc = 0;
    private double cr = 0.9;
    private int evaluations = 30000;  
    private double mutationFactor = 0.6; 
    private String description;        //Information presented 
    
    /**
     * Constructor method to create a new instance of the Configurator class.
     * <br>
     * Does not receive parameters.
     */
    public Configurator() {        
    }   

    /**
     * Method returns the stored value for the Nc parameter of the TS-MBFOA.
     * @return value of parameter Nc
     */
    public double getNc() {
        return nc;
    }

    /**
     * Method that assigns value to the Nc parameter of the TS-MBFOA.<br>
     * the number of chemotaxis cycles [1,Sb]
     * @param nc the nc to set
     */
    public void setNc(int nc) {
        this.nc = nc;
    }

        /**
     * Method returns the stored value for the Cr parameter of the DE.
     * @return value of parameter Cr
     */
    public double getCr() {
        return cr;
    }

    /**
     * Method that assigns value to the Cr parameter of the DE.<br>
     * @param cr the cr to set
     */
    public void setCr(double cr) {
        this.cr = cr;
    }
    /**
     * Method returns the stored value for the mutation parameter of the DE.
     * @return value of parameter mutation
     */
    public double getMutationFactor() {
        return mutationFactor;
    }

    /**
     * Method that assigns value to the mutation parameter of the DE.<br>
     * the mutation factor [0,1]
     * @param mutationFactor the mutation factor  to set
     */
    public void setMutationFactor(double mutationFactor) {
        this.mutationFactor = mutationFactor;
    }


    /**
     * Method returns the stored value for the GMAX parameter of the DE.
     * @return value of parameter evaluations (GMAX)
     */
    public int getEvaluations() {
        return evaluations;
    }

    /**
     * Method that assigns value to the GMAX parameter of the DE.<br>
     *
     * @param evaluations the evaluations (GMAX) to set
     */
    public void setEvaluations(int evaluations) {
        this.evaluations = evaluations;        
    }       
    
    /**
     * Method that returns a description of the population of each run.    
     * 
     * @return value {@code String}
     * 
     * <p>The invocation is as follows:</p> <br>
     * <pre>{@code object.getDescription();}</pre>
     * 
     */
    @Override
    public String getDescription(){
        this.description = "";
        String asterisks = "";
        for (int i = 0; i < 60; i++) 
            asterisks += "*";                
        
        this.description += "\n" + asterisks + "\n"
                + "\t\t  Information presented\n"
                + asterisks + "\n"
                + "\t\tColumn 1: Number of population\n"
                + "\t\tColumn 2 to " + ((this.getBacteriumMatix()[0].length - 2) / 2 + 1)
                + ": Variable values\n"
                + "\t\tColumn " + ((this.getBacteriumMatix()[0].length - 2) / 2 + 2)
                + ": Objective function evaluated\n"
                + "\t\tColumn " + ((this.getBacteriumMatix()[0].length - 2) / 2 + 3)
                + ": Constraints violation sum\n"
                + asterisks + "\n"
                + super.getDescription()+"\n"
                + asterisks + "\n";
                
        return this.description;
    }
            
}
