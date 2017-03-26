
public class SearchAlgorithm {
	
	public Schedule simulatedAnnealing(SchedulingProblem problem, long deadline) {

	    // get an empty solution to start from
	    Schedule currSolution = problem.getEmptySchedule();
	    Schedule secondSolution = problem.getEmptySchedule();
	    boolean done = false;
	    
	    //starting temperature and cooling rate of .05
	    double temp = 10000;
	    double coolingRate = 0.05;
	    
	    //while the temperature hasn't cooled fully
	    while(temp > 1){
	    	secondSolution = problem.getEmptySchedule();
	    	for(int i = 0; i < problem.courses.size() -1; i++){
	    		//take two courses (starting from beginning of array)
	    		Course c1 = problem.courses.get(i);
	    		boolean scheduled = false;
	    		
	    		if(!done){
		  	      	for (int j = 0; j < c1.timeSlotValues.length; j++) {
		  	      		if (scheduled) break;
		  	      		if (c1.timeSlotValues[j] > 0) {
		  	      			for (int k = 0; k < problem.rooms.size(); k++) {
		  	      				int ran1 = (int)(Math.random()*problem.rooms.size());
		  	      				int ran2 = (int)(Math.random()*c1.timeSlotValues.length);
		  	      				if (currSolution.schedule[ran1][ran2] < 0) {
		  	      					currSolution.schedule[ran1][ran2] = i;
		  	      					scheduled = true;
		  	      					break;
		  	      				}//end if
		  	      			}//end for
		  	      		}//end if
		  	      	}//end for
		  	      	done = true;
	    		}//end if 
	    		
	  	      	scheduled = false;
	  	      for (int j = 0; j < c1.timeSlotValues.length; j++) {
	  	      		if (scheduled) break;
	  	      		if (c1.timeSlotValues[j] > 0) {
	  	      			for (int k = 0; k < problem.rooms.size(); k++) {
	  	      				int ran1 = (int)(Math.random()*problem.rooms.size());
	  	      				int ran2 = (int)(Math.random()*c1.timeSlotValues.length);
	  	      				if (secondSolution.schedule[ran1][ran2] < 0) {
	  	      					secondSolution.schedule[ran1][ran2] = i;
	  	      					scheduled = true;
	  	      					break;
	  	      				}//end if
	  	      			}//end for
	  	      		}//end if
	  	      	}//end for
	  	      	
	    	}//end for (going through all the courses)
	    	double firstNeighbor = problem.evaluateSchedule(currSolution);
  	      	double secondNeighbor = problem.evaluateSchedule(secondSolution);
  	      	
  	      	if(acceptanceProbability(firstNeighbor, secondNeighbor, temp) > Math.random())
  	      		currSolution = secondSolution;
	    	
	    	temp *= coolingRate;
	    }//end while

	    return currSolution;
	  }//end simulatedAnnealing
	
	  public static double acceptanceProbability(double energy, double newEnergy, double temp){
		  
		  if(newEnergy < energy)
			  return 1.0;
		  return Math.exp((energy - newEnergy) / temp);
		  
	  }//end acceptanceProbability

	  // This is a very naive baseline scheduling strategyΩ/
	  // It should be easily beaten by any reasonable strategy
	  public Schedule naiveBaseline(SchedulingProblem problem, long deadline) {

	    // get an empty solution to start from
	    Schedule solution = problem.getEmptySchedule();

	    for (int i = 0; i < problem.courses.size(); i++) {
	      Course c = problem.courses.get(i);
	      boolean scheduled = false;
	      for (int j = 0; j < c.timeSlotValues.length; j++) {
	        if (scheduled) break;
	        if (c.timeSlotValues[j] > 0) {
	          for (int k = 0; k < problem.rooms.size(); k++) {
	            if (solution.schedule[k][j] < 0) {
	              solution.schedule[k][j] = i;
	              scheduled = true;
	              break;
	            }//end if
	          }//end for
	        }//end if
	      }//end for
	    }//end for

	    return solution;
	  }//end naiveBaseline
}
