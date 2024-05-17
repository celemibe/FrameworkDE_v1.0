package com.abimelecperez.metaheuristics.de;

import com.abimelecperez.cnop.*;

/**
 * This is main class.
 * 
 * @author <b>Abimelec Perez-Flores</b><br>
 * <a href="https://github.com/celemibe" target="_blank">JMetaDE on GitHub</a><br>
 * <a href="mailto:abimelecpf@hotmail.com">abimelecpf@hotmail.com</a><br>
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        /**
         * Start setup...
         */

        Problem problem = new TensionCompressionSpring();
        Configurator configurator = new Configurator();
        /* The initial population is established.*/
        configurator.setSb(14);
        /* The value of the crossover factor is established.*/
        configurator.setCr(0.9);
        /* The value of the mutation factor is established.*/
        configurator.setMutationFactor(0.6);
        /* The value of the number evaluations is established.*/
        configurator.setEvaluations(15000);
        /*The number of executions is established*/
        problem.setExecutions(3);
        /**
         * End setup...
         */

        RunDE stst = new RunDE();
        stst.run(problem, configurator, false, false);

//        System.out.println(Arrays.toString(problem.getConvergenceBestValue()[0]));
//        System.out.println(Arrays.toString(problem.getConvergenceBestValue()[1]));
        System.out.println("\nSalida de convergencia");
        for (int i = 0; i < problem.getConvergenceMedia()[0].length; i++) {
            System.out.println(problem.getConvergenceMedia()[1][i] + ",");
        }

    }
}
