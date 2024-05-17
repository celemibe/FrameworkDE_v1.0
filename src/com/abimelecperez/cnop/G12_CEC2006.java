package com.abimelecperez.cnop;

import com.abimelecperez.metaheuristics.de.Configurator;
import com.abimelecperez.metaheuristics.de.Problem;

/**
 * G12_CEC2006 class that creates a test CNOP. 
 * This CNOP has as objective function:<br><br>
 * 
 * Minimize: -(100 - (x1 - 5)^2 - (x2 - 5)^2 - (x3 - 5)^2)/100 <br>
 * Subject to: val{g1,p,q,r,1:9} <br>  
 * where: ran{1:3,0:10}
 * 
 * <br> This class inherits the mechanisms of the Problem class.
 * 
 * @author <b>Adrian García-López</b><br>
 *         <a href="https://github.com/garcialopez" target="_blank">JMetaBFOP on GitHub</a><br>
 *         <a href="mailto:joseadrian_g97@hotmail.com">joseadrian_g97@hotmail.com</a><br>
 * @version 1.0
 */
public class G12_CEC2006 extends Problem{

    public G12_CEC2006() {
        this.setBestKnownValue(-1.0000000000); 
        this.setNameProblem("G12 CEC2006");
        this.setFunction("-(100 - (x1 - 5)^2 - (x2 - 5)^2 - (x3 - 5)^2)/100"); //Función objetivo
        this.setOrderVariables("x1,x2,x3");             //Orden de sus variables        
        this.setObj(Problem.MINIMIZATION);                      // Objetivo -> min o max        
        this.setRankVariable("ran{1:3,0:10}"); //Se inserta el rango para cada variable.
        //Se aplica el metodo para detectar las restricciones        
        this.detectConstraints("{(x1-p)^2 + (x2 - q)^2 + (x3 - r)^2 - 0.0625 <= 0}");
        this.setConstraintsValuate("val{g1,p,q,r,1:9}");
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
