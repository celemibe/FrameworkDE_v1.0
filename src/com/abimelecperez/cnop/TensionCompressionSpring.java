package com.abimelecperez.cnop;

import com.abimelecperez.metaheuristics.de.Configurator;
import com.abimelecperez.metaheuristics.de.Problem;

/**
 * TensionCompressionSpring class that creates a test CNOP. 
 * This CNOP has as objective function:<br><br>
 * 
 * Minimize: (N+2)*D*d^2 <br>
 * Subject to: 1-(D^3*N)/(71785*d^4) <= 0 <br>
 * ((4*(D^2)-d*D)/(12566*(D*(d^3)-(d^4)))) + (1/(5108*(d^2)))-1 <= 0 <br>
 * 1-(140.45*d/((D^2)*N)) <= 0 <br>
 * ((D+d)/1.5)-1 <= 0 <br> 
 * where: (0.05, 2),(0.25, 1.3),(2, 15)
 * 
 * <br> This class inherits the mechanisms of the Problem class.
 * 
 * @author <b>Adrian García-López</b><br>
 *         <a href="https://github.com/garcialopez" target="_blank">JMetaBFOP on GitHub</a><br>
 *         <a href="mailto:joseadrian_g97@hotmail.com">joseadrian_g97@hotmail.com</a><br>
 * @version 1.0
 */
public class TensionCompressionSpring extends Problem {
    
    public TensionCompressionSpring() {
        this.setBestKnownValue(0.012665);                   //Mejor valor conocido
        this.setNameProblem("Tension/compression spring"); //Nombre del problema        
        this.setFunction("(N+2)*D*d^2");                   //Función objetivo
        this.setOrderVariables("d,D,N");                   //Orden de sus variables        
        this.setObj(Problem.MINIMIZATION);                                // Objetivo -> min o max        
        this.setRankVariable("(0.05, 2),(0.25, 1.3),(2, 15)"); //Se inserta el rango para cada variable.        
        //Se aplica el metodo para detectar las restricciones
        String constraints = "{1-(D^3*N)/(71785*d^4) <= 0};"
                           + "{((4*(D^2)-d*D)/(12566*(D*(d^3)-(d^4)))) + (1/(5108*(d^2)))-1 <= 0};"
                           + "{1-(140.45*d/((D^2)*N)) <= 0};"
                           + "{((D+d)/1.5)-1 <= 0}";
        //Se aplica el metodo para detectar las restricciones
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
        this.configurator.setEvaluations(15000);/*Number of evaluations*/      

        return this.configurator;
    }

}
