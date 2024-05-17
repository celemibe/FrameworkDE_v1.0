package com.abimelecperez.cnop;

import com.abimelecperez.metaheuristics.de.Configurator;
import com.abimelecperez.metaheuristics.de.Problem;

/**
 * G06_CEC2006 class that creates a test CNOP. 
 * This CNOP has as objective function:<br><br>
 * 
 * Minimize: (x1 - 10)^3 + (x2 - 20)^3 <br>
 * Subject to: -(x1 - 5)^2 - (x2 - 5)^2 + 100 <= 0 <br> 
 *             (x1 - 6)^2 + (x2 - 5)^2 - 82.81 <= 0 <br>  
 * where: (13.0,100.0),(0,100.0)
 * 
 * <br> This class inherits the mechanisms of the Problem class.
 * 
 * @author <b>Adrian García-López</b><br>
 *         <a href="https://github.com/garcialopez" target="_blank">JMetaBFOP on GitHub</a><br>
 *         <a href="mailto:joseadrian_g97@hotmail.com">joseadrian_g97@hotmail.com</a><br>
 * @version 1.0
 */
public class G06_CEC2006 extends Problem{

    public G06_CEC2006() {
        this.setBestKnownValue(-6961.8138755802); 
        this.setNameProblem("G06 CEC2006");
        this.setFunction("(x1 - 10)^3 + (x2 - 20)^3");                   //Función objetivo
        this.setOrderVariables("x1,x2");             //Orden de sus variables        
        this.setObj(Problem.MINIMIZATION);                      // Objetivo -> min o max        
        this.setRankVariable("(13.0,100.0),(0,100.0)"); //Se inserta el rango para cada variable.
        //Se aplica el metodo para detectar las restricciones
        String constraints = "{-(x1 - 5)^2 - (x2 - 5)^2 + 100 <= 0};{(x1 - 6)^2 + (x2 - 5)^2 - 82.81 <= 0}";
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
        this.configurator.setCr(0.7);/*Crossover factor*/    
        this.configurator.setEvaluations(20000);/*Number of evaluations*/   

        return this.configurator;
    }

}
