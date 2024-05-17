package com.abimelecperez.metaheuristics.de;

import java.util.Arrays;

/**
 * <b>DE</b> class allows the creation of an object to initiate the
 * search process for CNOP optimization. This class is implemented by
 * Evolution.
 *
 * @author <b>Abimelec Perez-Flores</b><br>
 * <a href="https://github.com/celemibe" target="_blank">JMetaDE on GitHub</a><br>
 * <a href="mailto:abimelecpf@hotmail.com">abimelecpf@hotmail.com</a><br>
 * @version 1.0
 */
public class DE implements Evolution {

    private final Mechanism mechanism;
    private final NumberRandom numberRandom;
    private Configurator newBacter;
    private int generation, count;

    /**
     * Empty constructor method that initializes the instances of the objects
     * used inside the class.
     */
    public DE() {
        this.numberRandom = new NumberRandom();
        this.mechanism = new Mechanism();
        this.generation = 0;
        this.count = 0;
    }

    @Override
    public void crossover(Configurator bacterium, Problem problem, Function function) {
        this.newBacter = new Configurator();
        int numberVariables = problem.getNumberAssignedVariable();
        int jrand = this.numberRandom.getRandomRankUnif(0, (bacterium.getBacteriumMatix().length - 1)); //Numero aleatorio de variable
        double[][] newBacterium = new double[1][numberVariables * 2 + 2];
        double[] stem = new double[numberVariables * 2 + 2];       //trial
        boolean flag;                        //1:true, 0:false
        int[] ind; //individual

        for (int b = 0; b < bacterium.getBacteriumMatix().length; b++) { //for b
            flag = true;
            ind = randomPosition(this.newBacter.getSb(), b);
            for (int k = 0; k < numberVariables; k++) { //for k 
                double random = this.numberRandom.getRandomUnif(); //This is a random number
                if (random <= this.newBacter.getCr() || k == jrand) {
                    //the three individuals are mutated
                    stem[k] = mutation(bacterium.getBacteriumMatix()[ind[0]][k], bacterium.getBacteriumMatix()[ind[1]][k], bacterium.getBacteriumMatix()[ind[2]][k], bacterium.getMutationFactor());
                    // It is validated that the new bacteria is within the ranges of the variables
                    if (stem[k] < problem.getRankVariable()[k][0]) {
                        //System.out.println("revaso menor");
                        stem[k] = (problem.getRankVariable()[k][0] * 2.0 - newBacterium[0][k]);
                    }
                    if (stem[k] > problem.getRankVariable()[k][1]) {
                        //System.out.println("revaso mayor");
                        stem[k] = (problem.getRankVariable()[k][1] * 2.0 - newBacterium[0][k]);
                    }
                    if (stem[k] < problem.getRankVariable()[k][0] || newBacterium[0][k] > problem.getRankVariable()[k][1]) {
                        stem[k] = this.numberRandom.getRandomRankUnif(problem.getRankVariable()[k][0], problem.getRankVariable()[k][1]);
                        //System.out.println("revaso ambos");
                    }
                } else { // they do not mutate
                    stem[k] = bacterium.getBacteriumMatix()[b][k];
                }
            }// for k

            newBacterium[0] = stem;
            newBacter.setBacteriumMatix(newBacterium);
            newBacterium = this.mechanism.evaluationFyC(newBacter, problem, function);

            /*After evaluation of the objective function of the new bacteria,
            it is compared with the bacteria in process using feasibility rules.*/
            if (newBacterium[0][numberVariables * 2 + 1] == 0 && bacterium.getBacteriumMatix()[b][numberVariables * 2 + 1] == 0) {
                if (newBacterium[0][numberVariables * 2] < bacterium.getBacteriumMatix()[b][numberVariables * 2]) {
                    flag = false;
                    bacterium.getBacteriumMatix()[b] = Arrays.copyOfRange(newBacterium[0], 0, newBacterium[0].length);
                } else {
                    flag = true;
                }
            } else if (newBacterium[0][numberVariables * 2 + 1] > 0 && bacterium.getBacteriumMatix()[b][numberVariables * 2 + 1] > 0) {
                if (newBacterium[0][numberVariables * 2 + 1] < bacterium.getBacteriumMatix()[b][numberVariables * 2 + 1]) {
                    flag = false;
                    bacterium.getBacteriumMatix()[b] = Arrays.copyOfRange(newBacterium[0], 0, newBacterium[0].length);
                } else {
                    flag = true;
                }
            } else if (newBacterium[0][numberVariables * 2 + 1] == 0 && bacterium.getBacteriumMatix()[b][numberVariables * 2 + 1] > 0) {
                flag = false;
                bacterium.getBacteriumMatix()[b] = Arrays.copyOfRange(newBacterium[0], 0, newBacterium[0].length);
            } else {
                flag = true;
            }

            bacterium.sortPopulation(problem.getNumberAssignedVariable());
        }//for b
    }

    
    public int[] randomPosition(int sb, int different) {
        sb = sb - 1;
        int[] position = new int[3];
        position[0] = this.numberRandom.getRandomRankUnif(0, sb);
        position[1] = this.numberRandom.getRandomRankUnif(0, sb);
        position[2] = this.numberRandom.getRandomRankUnif(0, sb);

        //Se evalua que sean diferentes
        while (position[0] == different) {
            position[0] = this.numberRandom.getRandomRankUnif(0, sb);
        }

        while ((position[1] == different) || (position[1] == position[0])) {
            position[1] = this.numberRandom.getRandomRankUnif(0, sb);
        }

        while ((position[2] == different) || (position[2] == position[1]) || (position[2] == position[0])) {
            position[2] = this.numberRandom.getRandomRankUnif(0, sb);
        }
        return position;
    }

    /**
     *This method returns the mutated trial. 
     * @param r1 individual
     * @param r2 individual
     * @param r3 individual
     * @param F Matation factor
     * @return trial
     */
    public double mutation(double r1, double r2, double r3, double F) {
        return r1 + (F * (r2 - r3));
    }

    /**
     * This method returns the generational number.
     *
     * @return the generation
     */
    public int getGeneration() {
        return generation;
    }

    /**
     * This method increases the number of DE generations.
     *
     * @param value generational number
     */
    protected void increaseGeneration(int value) {
        this.setGeneration(this.generation + value);
    }

    /**
     * Return to DE stop condition.
     *
     * @param configurator object of the class Configurator
     * @return true or false if (counter * sb + sb ) <= evaluations
     */
    protected boolean getInstruction(Configurator configurator) {
        return (((this.getCount() * configurator.getSb()) + configurator.getSb()) <= configurator.getEvaluations());
    }

    /**
     * This method increments an auxiliary counter for the generations.
     *
     * @param value the count to set
     */
    protected void increaseCount(int value) {
        this.setCount(this.getCount() + value);
    }

    /**
     * Method that assigns value to the generation.
     *
     * @param generation the generation to set
     */
    protected void setGeneration(int generation) {
        this.generation = generation;
    }

    /**
     * Method that assigns value to the count.
     *
     * @param count the count to set
     */
    protected void setCount(int count) {
        this.count = count;
    }

    /**
     * Method that returns the value count.
     *
     * @return the count
     */
    protected int getCount() {
        return count;
    }


}
