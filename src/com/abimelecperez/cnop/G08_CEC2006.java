package com.abimelecperez.cnop;

import com.abimelecperez.metaheuristics.de.Configurator;
import com.abimelecperez.metaheuristics.de.Problem;

/**
 * G08_CEC2006 class that creates a test CNOP. 
 * This CNOP has as objective function:<br><br>
 * 
 * Minimize: -(sin(2 * pi * x1)^3) * sin(2 * pi * x2) / ((x1^3) * (x1 + x2)) <br>
 * Subject to: x1^2 - x2 + 1 <= 0 <br> 
 *             1 - x1 + (x2 - 4)^2 <= 0 <br>                
 * where: (0,10),(0,10)
 * 
 * <br> This class inherits the mechanisms of the Problem class.
 * 
 * @author <b>Adrian García-López</b><br>
 *         <a href="https://github.com/garcialopez" target="_blank">JMetaBFOP on GitHub</a><br>
 *         <a href="mailto:joseadrian_g97@hotmail.com">joseadrian_g97@hotmail.com</a><br>
 * @version 1.0
 */
public class G08_CEC2006 extends Problem{

    public G08_CEC2006() {
        this.setBestKnownValue(-0.0958250415); 
        this.setNameProblem("G08 CEC2006");
        this.setFunction("-(sin(2 * pi * x1)^3) * sin(2 * pi * x2) / ((x1^3) * (x1 + x2))");  //Función objetivo
        this.setOrderVariables("x1,x2");             //Orden de sus variables        
        this.setObj(Problem.MINIMIZATION);                      // Objetivo -> min o max        
        this.setRankVariable("(0,10),(0,10)"); //Se inserta el rango para cada variable.
        //Se aplica el metodo para detectar las restricciones
        String constraints = "{x1^2 - x2 + 1 <= 0};{1 - x1 + (x2 - 4)^2 <= 0}";
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
        this.configurator.setCr(0.7);/*Crossover factor*/    
        this.configurator.setEvaluations(15000);/*Number of evaluations*/    

        return this.configurator;
    }

}
