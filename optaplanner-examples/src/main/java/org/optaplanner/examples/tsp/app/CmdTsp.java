package org.optaplanner.examples.tsp.app;

import java.io.File;

import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.examples.tsp.domain.TravelingSalesmanTour;
import org.optaplanner.examples.tsp.domain.Visit;
import org.optaplanner.examples.tsp.persistence.TspImporter;

public class CmdTsp {
	  public static final String SOLVER_CONFIG
      = "org/optaplanner/examples/tsp/solver/tspSolverConfig.xml";


	public static void main(String[] args) {
		
		 // Build the Solver
        SolverFactory solverFactory = SolverFactory.createFromXmlResource(
        		SOLVER_CONFIG);
        Solver solver = solverFactory.buildSolver();

        TravelingSalesmanTour solution=(TravelingSalesmanTour) new TspImporter().readSolution(new File("/Users/peter/Downloads/optaplanner-distribution-6.2.0.Final/examples/sources/data/tsp/import/mppt-no-domicile.tsp"));
//        // Load a problem with 400 computers and 1200 processes
//        TravelingSalesmanTour unsolvedCloudBalance = new TravelingSalesmanTour().getProblemFacts();
//
//        // Solve the problem
        solver.solve(solution);
        TravelingSalesmanTour best=(TravelingSalesmanTour) solver.getBestSolution();
        
//        CloudBalance solvedCloudBalance = (CloudBalance) solver.getBestSolution();

        // Display the result
        
        System.out.println("\n" + best.getScore() );
        for(Visit v:best.getVisitList()){
        	System.out.println(v.getLocation().getName() +" -->" + v.getPreviousStandstill().getLocation().getName());
        }
//                + toDisplayString(solvedCloudBalance));

	}

}
