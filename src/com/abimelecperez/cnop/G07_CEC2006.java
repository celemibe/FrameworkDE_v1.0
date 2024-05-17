package com.abimelecperez.cnop;

import com.abimelecperez.metaheuristics.de.Configurator;
import com.abimelecperez.metaheuristics.de.Problem;

/**
 * G07_CEC2006 class that creates a test CNOP. 
 * This CNOP has as objective function:<br><br>
 * 
 * Minimize: x1^2 + x2^2 + x1 * x2 - 14 * x1 - 16 * x2 + (x3 - 10)^2 + 4 * (x4 - 5)^2 + (x5 - 3)^2 + 2 * (x6 - 1)^2 + 5 * x7^2 + 7 * (x8 - 11)^2 + 2 * (x9 - 10)^2 + (x10 - 7)^2 + 45 <br>
 * Subject to: -105 + 4 * x1 + 5 * x2 - 3 * x7 + 9 * x8 <= 0 <br>
 * 10 * x1 - 8 * x2 - 17 * x7 + 2 * x8 <= 0 <br>
 * -8 * x1 + 2 * x2 + 5 * x9 - 2 * 10 - 12 <= 0 <br>
 * 3 * (x1 - 2)^2 + 4 * (x2 - 3)^2 + 2 * x3^2 -7 * x4 - 120 <= 0 <br>
 * 5 * x1^2 + 8 * x2 + (x3 - 6)^2 - 2 * x4 - 40 <= 0 <br>
 * x1^2 + 2 * (x2 - 2)^2 - 2 * x1 * x2 + 14 * x5 - 6 * x6 <= 0 <br>
 * 0.5 * (x1 - 8)^2 + 2.0 * (x2 - 4)^2 + (3.0 * x5^2) - x6 - 30.0 <= 0 <br>
 * -3 * x1 + 6 * x2 + 12 * (x9 - 8)^2 - 7 * x10 <= 0 <br>  
 * where: ran{1:10,-10:10}
 * 
 * <br> This class inherits the mechanisms of the Problem class.
 * 
 * @author <b>Adrian García-López</b><br>
 *         <a href="https://github.com/garcialopez" target="_blank">JMetaBFOP on GitHub</a><br>
 *         <a href="mailto:joseadrian_g97@hotmail.com">joseadrian_g97@hotmail.com</a><br>
 * @version 1.0
 */
public class G07_CEC2006 extends Problem{

    public G07_CEC2006() {
        this.setBestKnownValue(24.3062090681); 
        this.setNameProblem("G07 CEC2006");
        this.setFunction("x1^2 + x2^2 + x1 * x2 - 14 * x1 - 16 * x2 + (x3 - 10)^2 + 4 * (x4 - 5)^2 + (x5 - 3)^2 + 2 * (x6 - 1)^2 + 5 * x7^2 + 7 * (x8 - 11)^2 + 2 * (x9 - 10)^2 + (x10 - 7)^2 + 45");                   //Función objetivo
        this.setOrderVariables("iter{x,1,10}");             //Orden de sus variables        
        this.setObj(Problem.MINIMIZATION);                      // Objetivo -> min o max        
        this.setRankVariable("ran{1:10,-10:10}"); //Se inserta el rango para cada variable.
        //Se aplica el metodo para detectar las restricciones
        String constraints = "{-105 + 4 * x1 + 5 * x2 - 3 * x7 + 9 * x8 <= 0};"
                + "{10 * x1 - 8 * x2 - 17 * x7 + 2 * x8 <= 0};"
                + "{-8 * x1 + 2 * x2 + 5 * x9 - 2 * 10 - 12 <= 0};"
                + "{3 * (x1 - 2)^2 + 4 * (x2 - 3)^2 + 2 * x3^2 -7 * x4 - 120 <= 0};"
                + "{5 * x1^2 + 8 * x2 + (x3 - 6)^2 - 2 * x4 - 40 <= 0};"
                + "{x1^2 + 2 * (x2 - 2)^2 - 2 * x1 * x2 + 14 * x5 - 6 * x6 <= 0};"
                + "{0.5 * (x1 - 8)^2 + 2.0 * (x2 - 4)^2 + (3.0 * x5^2) - x6 - 30.0 <= 0};"
                + "{-3 * x1 + 6 * x2 + 12 * (x9 - 8)^2 - 7 * x10 <= 0}";
        this.detectConstraints(constraints);
    }
    
    @Override
    public Configurator getRecommendedSetting() {
        this.configurator = new Configurator();
        /**
         * Inicia la calibración de parámetros del algoritmo...
         */
        this.configurator.setSb(10);/*Number of individuals for start population*/                                         
       this.configurator.setMutationFactor(0.6);/*mutation factor*/     
        this.configurator.setCr(0.7);/*Crossover factor*/    
        this.configurator.setEvaluations(15000);/*Number of evaluations*/    

        return this.configurator;
    }

}
