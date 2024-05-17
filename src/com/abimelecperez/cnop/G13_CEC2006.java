package com.abimelecperez.cnop;

import com.abimelecperez.metaheuristics.de.Configurator;
import com.abimelecperez.metaheuristics.de.Problem;

/**
 * G13_CEC2006 class that creates a test CNOP. 
 * This CNOP has as objective function:<br><br>
 * 
 * Minimize: exp(x1 * x2 * x3 * x4 * x5) <br>
 * Subject to: x1^2 + x2^2 + x3^2 + x4^2 + x5^2 - 10 = 0 <br> 
 *             x2 * x3 - 5 * x4 * x5 = 0 <br>  
 *              x1^3 + x2^3 + 1 = 0 <br>  
 * where: (-2.3,2.3);(-2.3,2.3);ran{3:5,-3.2:3.2}
 * 
 * <br> This class inherits the mechanisms of the Problem class.
 * 
 * @author <b>Adrian García-López</b><br>
 *         <a href="https://github.com/garcialopez" target="_blank">JMetaBFOP on GitHub</a><br>
 *         <a href="mailto:joseadrian_g97@hotmail.com">joseadrian_g97@hotmail.com</a><br>
 * @version 1.0
 */
public class G13_CEC2006 extends Problem{

    public G13_CEC2006() {
        this.setBestKnownValue(0.0539415140); 
        this.setNameProblem("G13 CEC2006");
        this.setFunction("exp(x1 * x2 * x3 * x4 * x5)");                   //Función objetivo
        this.setOrderVariables("x1,x2,x3,x4,x5");             //Orden de sus variables        
        this.setObj(Problem.MINIMIZATION);                      // Objetivo -> min o max        
        this.setRankVariable("(-2.3,2.3);(-2.3,2.3);ran{3:5,-3.2:3.2}"); //Se inserta el rango para cada variable.
        //Se aplica el metodo para detectar las restricciones
        String constraints = "{x1^2 + x2^2 + x3^2 + x4^2 + x5^2 - 10 = 0};{x2 * x3 - 5 * x4 * x5 = 0};{x1^3 + x2^3 + 1 = 0}";
        this.detectConstraints(constraints);
    }
    
    @Override
    public Configurator getRecommendedSetting() {
        this.configurator = new Configurator();
        /**
         * Inicia la calibración de parámetros del algoritmo...
         */
        this.configurator.setSb(14);/*Number of individuals for start population*/                                         
        this.configurator.setMutationFactor(0.6);/*mutation factor*/     
        this.configurator.setCr(0.9);/*Crossover factor*/    
        this.configurator.setEvaluations(20000);/*Number of evaluations*/   

        return this.configurator;
    }

}
