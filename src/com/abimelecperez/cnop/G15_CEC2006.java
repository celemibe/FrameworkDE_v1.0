package com.abimelecperez.cnop;

import com.abimelecperez.metaheuristics.de.Configurator;
import com.abimelecperez.metaheuristics.de.Problem;

/**
 * G15_CEC2006 class that creates a test CNOP. 
 * This CNOP has as objective function:<br><br>
 * 
 * Minimize: 1000 - x1^2 - 2 * x2^2 -x3^2 - x1 * x2 - x1 * x3 <br>
 * Subject to: x1^2 + x2^2 + x3^2 - 25 = 0 <br> 
 *             8 * x1 + 14 * x2 + 7 * x3 - 56 = 0<br>   
 * where: ran{1:3,0:10}
 * 
 * <br> This class inherits the mechanisms of the Problem class.
 * 
 * @author <b>Adrian García-López</b><br>
 *         <a href="https://github.com/garcialopez" target="_blank">JMetaBFOP on GitHub</a><br>
 *         <a href="mailto:joseadrian_g97@hotmail.com">joseadrian_g97@hotmail.com</a><br>
 * @version 1.0
 */
public class G15_CEC2006 extends Problem{

    public G15_CEC2006() {
        this.setBestKnownValue(961.7150222899); 
        this.setNameProblem("G15 CEC2006");
        this.setFunction("1000 - x1^2 - 2 * x2^2 -x3^2 - x1 * x2 - x1 * x3");                   //Función objetivo
        this.setOrderVariables("x1,x2,x3");             //Orden de sus variables        
        this.setObj(Problem.MINIMIZATION);                      // Objetivo -> min o max        
        this.setRankVariable("ran{1:3,0:10}"); //Se inserta el rango para cada variable.
        //Se aplica el metodo para detectar las restricciones
        String constraints = "{x1^2 + x2^2 + x3^2 - 25 = 0};{8 * x1 + 14 * x2 + 7 * x3 - 56 = 0}";
        this.detectConstraints(constraints);
    }
    
    @Override
    public Configurator getRecommendedSetting() {
        this.configurator = new Configurator();
        /**
         * Inicia la calibración de parámetros del algoritmo...
         */
        this.configurator.setSb(10);/*Number of individuals for start population*/                                         
        this.configurator.setMutationFactor(0.5);/*mutation factor*/     
        this.configurator.setCr(0.6);/*Crossover factor*/    
        this.configurator.setEvaluations(15000);/*Number of evaluations*/   

        return this.configurator;
    }

}
