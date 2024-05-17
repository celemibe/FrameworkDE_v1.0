package com.abimelecperez.cnop;

import com.abimelecperez.metaheuristics.de.Configurator;
import com.abimelecperez.metaheuristics.de.Problem;

/**
 * G24_CEC2006 class that creates a test CNOP. 
 * This CNOP has as objective function:<br><br>
 * 
 * Minimize: -x1 - x2 <br>
 * Subject to: -2 * x1^4 + 8 * x1^3 - 8 * x1^2 + x2 - 2 <= 0 <br>
 * -4 * x1^4 + 32 * x1^3 - 88 * x1^2 + 96 * x1 + x2 - 36 <= 0 <br>
 * where: (0,3),(0,4)
 * 
 * <br> This class inherits the mechanisms of the Problem class.
 * 
 * @author <b>Adrian García-López</b><br>
 *         <a href="https://github.com/garcialopez" target="_blank">JMetaBFOP on GitHub</a><br>
 *         <a href="mailto:joseadrian_g97@hotmail.com">joseadrian_g97@hotmail.com</a><br>
 * @version 1.0
 */
public class G24_CEC2006 extends Problem{

    public G24_CEC2006() {
        this.setBestKnownValue(-5.5080132716); 
        this.setNameProblem("G24 CEC2006");
        this.setFunction("-x1 - x2");                   //Función objetivo
        this.setOrderVariables("x1,x2");             //Orden de sus variables        
        this.setObj(Problem.MINIMIZATION);                      // Objetivo -> min o max        
        this.setRankVariable("(0,3),(0,4)"); //Se inserta el rango para cada variable.
        //Se aplica el metodo para detectar las restricciones        
        this.detectConstraints("{-2 * x1^4 + 8 * x1^3 - 8 * x1^2 + x2 - 2 <= 0};"
                           + "{-4 * x1^4 + 32 * x1^3 - 88 * x1^2 + 96 * x1 + x2 - 36 <= 0}");
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
