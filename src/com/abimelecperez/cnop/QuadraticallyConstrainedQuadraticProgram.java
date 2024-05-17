package com.abimelecperez.cnop;

import com.abimelecperez.metaheuristics.de.Configurator;
import com.abimelecperez.metaheuristics.de.Problem;

/**
 * QuadraticallyConstrainedQuadraticProgram class that creates a test CNOP. 
 * This CNOP has as objective function:<br><br>
 * 
 * Minimize: x1^4 - 14 * x1^2 + 24 * x1 - x2^2 <br>
 * Subject to: -x1 + x2 - 8 <= 0 <= 0 <br>
 * x2 - x1^2 - 2 * x1 + 2 <= 0 <br>
 * where: (-8,10),(0,10)
 * 
 * <br> This class inherits the mechanisms of the Problem class.
 * 
 * @author <b>Adrian García-López</b><br>
 *         <a href="https://github.com/garcialopez" target="_blank">JMetaBFOP on GitHub</a><br>
 *         <a href="mailto:joseadrian_g97@hotmail.com">joseadrian_g97@hotmail.com</a><br>
 * @version 1.0
 */
public class QuadraticallyConstrainedQuadraticProgram extends Problem{

    public QuadraticallyConstrainedQuadraticProgram() {
        this.setBestKnownValue(-118.7048); 
        this.setNameProblem("Quadratically constrained quadratic program");
        this.setFunction("x1^4 - 14 * x1^2 + 24 * x1 - x2^2");                   //Función objetivo
        this.setOrderVariables("x1,x2");             //Orden de sus variables        
        this.setObj(Problem.MINIMIZATION);                      // Objetivo -> min o max        
        this.setRankVariable("(-8,10),(0,10)"); //Se inserta el rango para cada variable.
        //Se aplica el metodo para detectar las restricciones        
        this.detectConstraints("{-x1 + x2 - 8 <= 0};{x2 - x1^2 - 2 * x1 + 2 <= 0}");
    }
    
    @Override
    public Configurator getRecommendedSetting() {
        this.configurator = new Configurator();
        /**
         * Inicia la calibración de parámetros del algoritmo...
         */
        this.configurator.setSb(14);                                         
        this.configurator.setMutationFactor(0.6);/*mutation factor*/     
        this.configurator.setCr(0.7);    
        this.configurator.setEvaluations(20000); 
        return this.configurator;
    }

}
