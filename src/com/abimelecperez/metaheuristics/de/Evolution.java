package com.abimelecperez.metaheuristics.de;

/**
 *
 * <b>Evolution</b> provides an interface to define the differential evolution process: 
 * crossover. If
 * you implement the Evolution object it can be used while building the
 * <b>DE</b> object. <br>
 *
 * This interface can be used to include other versions of the DE algorithm.
 *
 * @author <b>Abimelec Perez-Flores</b><br>
 * <a href="https://github.com/celemibe" target="_blank">JMetaDE on GitHub</a><br>
 * <a href="mailto:abimelecpf@hotmail.com">abimelecpf@hotmail.com</a><br>
 * @version 1.0
 */
public interface Evolution {

    /**
     *In the crossing method, the process of crossing and mutation of DE is 
     * carried out.
     *
     * @param configurator object of the class Configurator
     * @param problem object of the class Problem
     * @param function object of the class Function
     */
    public abstract void crossover(Configurator configurator, Problem problem, Function function);

//    /**
//     * The mutation method is a DE process, which is applied in the middle cycle
//     * of the crossover process.
//     */
//    public abstract void mutation(Tsmbfoa r1, Tsmbfoa r2, Tsmbfoa r3, Tsmbfoa f);

}
