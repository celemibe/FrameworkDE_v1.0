package com.abimelecperez.cnop;

import com.abimelecperez.metaheuristics.de.Configurator;
import com.abimelecperez.metaheuristics.de.Problem;

/**
 * G05_CEC2006 class that creates a test CNOP. 
 * This CNOP has as objective function:<br><br>
 * 
 * Minimize: 3 * x1 +  0.000001 * x1^3 + 2 * x2 + (0.000002 / 3) * x2^3 <br>
 * Subject to: -x4 + x3 - 0.55 <= 0 <br>
 * -x3 + x4 - 0.55 <= 0 <br>
 * 1000 * sin(-x3 - 0.25) + 1000 * sin(-x4 - 0.25) + 894.8 - x1 = 0 <br>
 * 1000 * sin(x3 - 0.25) + 1000 * sin(x3 - x4 - 0.25) + 894.8 - x2 = 0 <br>
 * 1000 * sin(x4 - 0.25) + 1000 * sin(x4 - x3 - 0.25) + 1294.8 = 0 <br>  
 * where: (0,1200),(0,1200),(-0.55,0.55),(-0.55,0.55)
 * 
 * <br> This class inherits the mechanisms of the Problem class.
 * 
 * @author <b>Adrian García-López</b><br>
 *         <a href="https://github.com/garcialopez" target="_blank">JMetaBFOP on GitHub</a><br>
 *         <a href="mailto:joseadrian_g97@hotmail.com">joseadrian_g97@hotmail.com</a><br>
 * @version 1.0
 */
public class G05_CEC2006 extends Problem{

    public G05_CEC2006() {
        this.setBestKnownValue(5126.4967140071); 
        this.setNameProblem("G05 CEC2006");
        this.setFunction("3 * x1 +  0.000001 * x1^3 + 2 * x2 + (0.000002 / 3) * x2^3");                   //Función objetivo
        this.setOrderVariables("x1,x2,x3,x4");             //Orden de sus variables        
        this.setObj(Problem.MINIMIZATION);                      // Objetivo -> min o max        
        this.setRankVariable("(0,1200),(0,1200),(-0.55,0.55),(-0.55,0.55)"); //Se inserta el rango para cada variable.
        //Se aplica el metodo para detectar las restricciones
        String constraints = "{-x4 + x3 - 0.55 <= 0};{-x3 + x4 - 0.55 <= 0};"
                + "{1000 * sin(-x3 - 0.25) + 1000 * sin(-x4 - 0.25) + 894.8 - x1 = 0};"
                + "{1000 * sin(x3 - 0.25) + 1000 * sin(x3 - x4 - 0.25) + 894.8 - x2 = 0};"
                + "{1000 * sin(x4 - 0.25) + 1000 * sin(x4 - x3 - 0.25) + 1294.8 = 0}";
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
