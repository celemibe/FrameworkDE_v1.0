package com.abimelecperez.metaheuristics.de;

import com.abimelecperez.statistics.Statistics;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * <b>RunDE</b> class is responsible for initiating the DE processes by
 * fulfilling its generational cycles of the algorithm and applying the entire
 * search process.
 *
 * @author <b>Adrian García-López</b><br>
 * <a href="https://github.com/garcialopez" target="_blank">JMetaBFOP on
 * GitHub</a><br>
 * <a href="mailto:joseadrian_g97@hotmail.com">joseadrian_g97@hotmail.com</a><br>
 *
 * @author <b>Abimelec Perez-Flores</b><br>
 * <a href="https://github.com/celemibe" target="_blank">JMetaDE on
 * GitHub</a><br>
 * <a href="mailto:abimelecpf@hotmail.com">abimelecpf@hotmail.com</a><br>
 * @version 1.0
 */
public class RunDE {

    private final Function function;
    private final Mechanism mechanism;
    private final DE de;
    private final Statistics st;
    private double[] bestValue;
    private double[][] bestValues;
    private double[][] aux;
    private double[][] aux1;
    private long timeAux;

    public RunDE() {
        this.function = new Function();
        this.mechanism = new Mechanism();
        this.de = new DE();
        this.st = new Statistics();
    }

    /**
     * This method has two purposes: to parse the objective function with the
     * help of the mXparser and to assign the constraints contained in the CNOP.
     *
     * @param problem object of the class Problem
     */
    private void adjustFunction(Problem problem) {
        // Parse to evaluate the objective function
        // The order of variables is established (x1, x2, x3, ..., xn).
        this.function.setFuncion(problem.getFunction(), problem.getOrderVariables());

        if (problem.getArguments() != null) {
            this.function.setArgs(problem.getArguments());
        }

        if (problem.getConstraintsInequality() != null || problem.getConstraintsEquality() != null) {
            this.function.setConstraints(problem.getConstraintsInequality(), problem.getConstraintsEquality());
        }
    }

    /**
     * This method is responsible for implementing the algorithm. An instance of
     * the RunDE class and invoking the run method, triggers the execution of
     * the DE processes.
     *
     * @param problem object of the class Problem
     * @param configurator object of the class Configurator
     * @param debug set to true if you want the execution status to appear on
     * the console and false if you do not want it to be activated.
     * @param debugResults is set to true if you want the results to appear in
     * the console and false if you do not want it to be activated.
     */
    public void run(Problem problem, Configurator configurator, boolean debug, boolean debugResults) {

        if (problem.getExecutions() > 0 && problem.getExecutions() <= 30) {
            System.out.println("Starting...");
            //Debugging is done in parts if the debug is enabled
            if (debug) {
                System.out.println("Preparing CNOP: " + problem.getNameProblem()
                        + "\for  " + (problem.getExecutions()) + " executions."
                        + "\n"
                        + "\t\t  Parameter calibration\n"
                        + "\t\tPopulation:\t\t" + configurator.getSb() + "\n"
                        + "\t\tMutation factor (F):\t\t" + configurator.getMutationFactor() + "\n"
                        + "\t\tCrossover factor (CR):\t\t" + configurator.getCr() + "\n"
                        + "\t\tEvaluations:\t\t" + configurator.getEvaluations() + "\n");
            }

            //in case of greater than 1 independent execution
            int medianExecution = 1;
            if (problem.getExecutions() > 1) {
                medianExecution = (int) problem.getExecutions() / 2;
            }
            problem.iniConvergenceMedia();
            problem.iniConvergenceBestValueAUX();
            double bestValueAux = Double.POSITIVE_INFINITY;

            //Containers are prepared for the new results
            problem.newResults();
            problem.newStatistic();
            problem.newConverter();
            int[] sccp = new int[problem.getExecutions()];

            //The experion is adjusted
            this.adjustFunction(problem);
            // The position of the OF is calculated
            int position = problem.getNumberVariableOF();

            /**
             * Start DE...
             */
            //Debugging is done in parts if the debug is enabled
            if (debug) {
                System.out.println("JMetaDE starts.");
            }
            this.timeAux = System.nanoTime();

            //The percentage of progress of the algorithm is included
            int percentageIncrease = (int) (100 / problem.getExecutions());
            problem.setAdvance(1);
            double count = 0;
            double[] exec = new double[configurator.getSb()];
            //Number of executions
            for (int i = 0; i < problem.getExecutions(); i++) {
                //variables to calculate performance
                count ++;
                int bp = 0;
                int sperformace = 0;

                //Debugging is done in parts if the debug is enabled
                if (debug) {
                    System.out.println("Start execution " + (i + 1));
                    System.out.println("Generating population...");
                }

                // The population is initialized
                configurator.startPopulation(problem);
                //The objective function, the constraints are evaluated and assigned to the population matrix
                configurator.setBacteriumMatix(this.mechanism.evaluationFyC(configurator, problem, this.function));
                // The population is ordered
                configurator.sortPopulation(problem.getNumberAssignedVariable());
                /*Imprime la matriz inicial*/
                if (debug) {
                    System.out.println("Matriz inicial.");
                    for (double[] Result : configurator.getBacteriumMatix()) {
                        System.out.println(Arrays.toString(Result));
                    }
                }
                // Counters are reset
                this.de.setGeneration(0);
                this.de.setCount(1);

                //The position of the OF is calculated
                int positionOF = configurator.getBacteriumMatix()[0].length - 2;

                //Debugging is done in parts if the debug is enabled
                if (debug) {
                    System.out.println("Initiates process for generations.");
                }
                // Start the process
                while (this.de.getInstruction(configurator)) {
                    //increase in generation
                    this.de.increaseGeneration(1);
                    //Debugging is done in parts if the debug is enabled
                    if (debug) {
                        System.out.println("\n> Start of generation " + this.de.getGeneration()
                                + " of execution " + (i + 1)
                                + "\n> Starts the crossover process...");
                    }
                    //crossover process
                    this.de.crossover(configurator, problem, function);
                    //increase counter
                    this.de.increaseCount(1);
                    /**
                     * Se usa para imprimir la matriz de cada generación
                     */
                    if (debug) {
                        System.out.println("Matriz de la generacion: " + this.de.getGeneration());
                        for (double[] Result : configurator.getBacteriumMatix()) {
                            System.out.println(Arrays.toString(Result));
                        }
                    }
                    if ((i + 1) == medianExecution) {
                        //System.out.println("THIS IS: " + this.de.getCount());
                        problem.setConvergenceMedia(
                                this.de.getCount(),
                                this.st.bestRow(
                                        configurator.getBacteriumMatix(),
                                        positionOF
                                )[positionOF]
                        );
                    }
                    int x = configurator.getBacteriumMatix()[0].length - 1;

                    if (configurator.getBacteriumMatix()[0][x] == 0) {

                        sperformace = st.successPerformance(configurator.getBacteriumMatix(), problem.getBestKnownValue());

                        if ((bp == 0) && (sperformace == 1)) {
                            sccp[i] = this.de.getCount();
                            bp = 1;
                        }

                    }

                    problem.setConvergenceBestValueAUX(
                            this.de.getCount(),
                            this.st.bestRow(
                                    configurator.getBacteriumMatix(),
                                    positionOF
                            )[positionOF]
                    );

                }//End DE                                                

                if (debugResults) {
                    System.out.println("\nTerminates DE with "
                            + this.de.getGeneration() + " generations.");
                }

                /**
                 * Se crea la matriz que guarda las poblaciones de cada
                 * ejecucion
                 */
                for (int b = 0; b < configurator.getSb(); b++) {
                    exec[b] = count;
                }
                if (i == 0) {
                    aux1 = new double[configurator.getSb()][problem.getNumberVariableOF() + 2];

                    //busqueda por filas
                    for (int c = 0; c < configurator.getSb(); c++) {
                        //busqueda por columnas
                        for (int d = 0; d < problem.getNumberVariableOF(); d++) {
                            this.aux1[c][d] = configurator.getBacteriumMatix()[c][d];
                        }
                    }
                    int count_aux = problem.getNumberVariableOF() * 2;

                    for (int c = 0; c < configurator.getSb(); c++) {
                        int pos = problem.getNumberVariableOF();
                        for (int d = count_aux; d < (count_aux + 2); d++) {
                            this.aux1[c][pos] = configurator.getBacteriumMatix()[c][d];
                            pos++;
                        }
                    }
                    this.aux1=problem.agregarColumna(this.aux1, exec);

                    problem.setMatix(this.aux1);
                } else {
                    aux1 = new double[configurator.getSb()][problem.getNumberVariableOF() + 2];
                    //busqueda por filas
                    for (int c = 0; c < configurator.getSb(); c++) {
                        //busqueda por columnas
                        for (int d = 0; d < problem.getNumberVariableOF(); d++) {
                            this.aux1[c][d] = configurator.getBacteriumMatix()[c][d];
                        }
                    }
                    int count_aux = problem.getNumberVariableOF() * 2;

                    for (int c = 0; c < configurator.getSb(); c++) {
                        int pos = problem.getNumberVariableOF();
                        for (int d = count_aux; d < (count_aux + 2); d++) {
                            this.aux1[c][pos] = configurator.getBacteriumMatix()[c][d];
                            pos++;
                        }
                    }
                    this.aux1=problem.agregarColumna(this.aux1, exec);
                    for (int a = 0; a < configurator.getSb(); a++) {
                        this.bestValues = problem.agregarFilas(problem.getMatix(), this.aux1[a]);
                        problem.setMatix(this.bestValues);
                    }
                }
                //Termina el guardado de la matriz
                if (problem.getExecutions() > 1) {
                    switch (problem.getObj()) {
                        case Problem.MINIMIZATION:
                            this.bestValue = this.st.bestRow(configurator.getBacteriumMatix(),
                                    configurator.getBacteriumMatix()[0].length - 2);
                            break;
                        case Problem.MAXIMIZATION:
                            this.bestValue = this.st.worstRow(configurator.getBacteriumMatix(),
                                    configurator.getBacteriumMatix()[0].length - 2);
                            break;
                        default:
                            System.err.println("Results cannot be saved, if min or max.");
                    }
                    problem.addBestResults(this.bestValue);

                    if (debugResults) {
                        System.out.println("Saving the best value found: " + this.bestValue[positionOF]);
                    }

                    //The convergence of the best solution is saved if the iteration is greater than 1                                           
                    if (this.bestValue[positionOF] < bestValueAux && this.bestValue[positionOF + 1] == 0) {
                        //System.out.println("between");
                        problem.setConvergenceBestValue();
                        problem.iniConvergenceBestValueAUX();
                        bestValueAux = this.bestValue[positionOF];
                    }

                }
////                System.out.println("Individuos finales ordenados");
////                if (debug) {
////                    
////                     configurator.sortPopulation(problem.getNumberAssignedVariable());
////                   
////                     for (double[] Result : configurator.getBacteriumMatix()) {
////                        System.out.println(Arrays.toString(Result));
////                    }
////                }
                //the percentage of progress is calculated
                if (problem.getAdvance() + percentageIncrease - 1 >= 100) {
                    problem.setAdvance(99);
                } else {
                    problem.setAdvance(problem.getAdvance() + percentageIncrease - 1);
                }

                if (debugResults) {
                    System.out.println("Information generated by execution "
                            + (i + 1)
                            + ":\n"
                            + configurator.getDescription());
                    System.out.println("<<< Completion of run " + (i + 1) + " with "
                            + this.de.getGeneration() + " generations >>>\n");
                }
            } // Finish the executions 
            if (debug) {
                System.out.println("Matriz para guardar en excel: ");
                for (double[] Result : problem.getMatix()) {
                    System.out.println(Arrays.toString(Result));
                }
            }
            //The time is added in seconds
            problem.setTimeSeconds(TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - timeAux));

            if (debugResults) {
                System.out.println("The established executions end.");
            }
            /*Sector de pruebas*/
//            int count_aux = problem.getNumberVariableOF() * 2;
//            System.out.println("cantidad de columnas" + String.valueOf(problem.getOrderVariables()));
            //Termina sector de pruebas
            if (problem.getExecutions() == 1) {
                for (double[] bacteriumMatix : configurator.getBacteriumMatix()) {
                    problem.addBestResults(bacteriumMatix);
                }

                if (debug) {
                    System.out.println("As there is 1 independent execution, the results are"
                            + "\nevaluated with the only population generated.");
                }
            }

            if (debugResults) {
                System.out.println("Best results found for each generation in each run "
                        + "of the DE:");
                for (double[] bestResult : problem.getBestResults()) {
                    System.out.println(Arrays.toString(bestResult));
                }
            }

            //Best value depending on the max or min objectives
            problem.addStatistic(
                    (problem.getObj().equals(Problem.MINIMIZATION))
                    ? this.st.best(problem.getBestResults(), position)
                    : this.st.worst(problem.getBestResults(), position)
            );

            //Media
            problem.addStatistic(this.st.mean(problem.getBestResults(), position));

            //Median
            problem.addStatistic(this.st.median(problem.getBestResults(), position));

            //Standard deviation
            problem.addStatistic(this.st.standardDeviation(problem.getBestResults(), position));

            //Worst
            problem.addStatistic(
                    (problem.getObj().equals(Problem.MINIMIZATION))
                    ? this.st.worst(problem.getBestResults(), position)
                    : this.st.best(problem.getBestResults(), position)
            );

            //Feasibility rate
            problem.addStatistic(this.st.feasibleRate(problem.getBestResults(), position + 1));
            /*Success rate*/
            problem.addStatistic(this.st.successRate(problem.getBestResults(), position, problem.getBestKnownValue()));

            int pd = 0;
            double pscp = 0;

            for (int i = 0; i < sccp.length; i++) {
                if (sccp[i] > 0) {
                    pd++;
                    pscp = pscp + sccp[i];
                }

            }

            if (pscp > 0 && pd > 0) {
                pscp = pscp / pd;
            } else {
                pscp = 0;
            }

            if (pscp > 0) {
                problem.addStatistic(Math.floor(pscp * problem.getExecutions()) / pd);
            } else {
                problem.addStatistic(0);
            }

            if (debugResults) {
                System.out.println("\nCalculating statistics...");
                for (int i = 0; i < problem.getStatistic().length; i++) {
                    System.out.println(problem.getStatisticsName()[i] + ": " + problem.getStatistic()[i]);
                }
            }
            if (debugResults) {
                System.out.println("Time: " + problem.getTimeSeconds());
            }

            if (problem.getAdvance() < 100) {
                problem.setAdvance(100);
            }

            System.out.println("\nJMetaDE completed.");

        } else {
            System.err.println("The number of executions must be between [1 and 30].");
        }

    }

//    /**
//     * 
//     * @param obj 
//     * @param newLength 
//     */
//    private void cleanMatrix(double[][] obj, int newLength){
//        aux = new double[obj.length][newLength + 2];        
//        for (int i = 0; i < obj.length; i++) {            
//            for (int j = 0; j < obj[i].length; j++) {                
//                aux[i][j] = obj[i][j];                        
//                if (i == newLength) {
//                    i = obj[i].length;
//                }
//                
//            }
//            
//        }
//    }
}
