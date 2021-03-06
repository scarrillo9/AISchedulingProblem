
public class SearchAlgorithm {

	public Schedule simulatedAnnealing(SchedulingProblem problem, long deadline) {

		// get an empty solution to start from
		Schedule currSolution = problem.getEmptySchedule();
		currSolution = startingSched(problem);
		Schedule secondSolution = problem.getEmptySchedule();

		//starting temperature and cooling rate of .05
		double temp = 10000;
		double coolingRate = 0.05;

		//while the temperature hasn't cooled fully
		while(temp > 1){
			//always start the new second solution
			secondSolution = problem.getEmptySchedule();

			//for ever course it'll go through the for loop
			for(int i = 0; i < problem.courses.size() -1; i++){
				//take the course to be scheduled
				Course c1 = problem.courses.get(i);
				boolean scheduled = false;

				//start scheduling the course into the 
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
			double currEnergy = problem.evaluateSchedule(currSolution);
			double secondEnergy = problem.evaluateSchedule(secondSolution);

			if(acceptanceProbability(currEnergy, secondEnergy, temp) > Math.random())
				currSolution = secondSolution;

			temp *= coolingRate;
		}//end while

		return currSolution;
	}//end simulatedAnnealing

	public Schedule backtracking(SchedulingProblem problem, long deadline){
		Schedule solution = problem.getEmptySchedule();
		
		for (int i = 0; i < problem.courses.size(); i++) {
			Course c = problem.courses.get(i);
			boolean scheduled = false;
			
			for (int j = 0; j < c.timeSlotValues.length; j++) {
				if (scheduled) break;
				if (c.timeSlotValues[j] > 0) {
					double x = c.preferredLocation.xCoor;
					double y = c.preferredLocation.yCoor;
					
					for (int k = 0; k < problem.rooms.size(); k++) {
						if (preferredBCPS(problem, x, y, k)) {
							solution.schedule[k][j] = i;
							scheduled = true;
							break;
						}//end if
					}//end for
					if(!scheduled){
						for (int k = 0; k < problem.rooms.size(); k++) {
							if (solution.schedule[k][j] < 0) {
								solution.schedule[k][j] = i;
								scheduled = true;
								break;
							}//end if
						}//end for
					}//end if hasn't been scheduled
				}//end if
			}//end for
			
		}//end for

		return solution;
	}//end backtracking
	
	public static boolean preferredBCPS(SchedulingProblem problem, double x, double y, int k){
		if(x == problem.rooms.get(k).b.xCoor && y == problem.rooms.get(k).b.yCoor)
			return true;
		return false;
	}//end preferredBCPS

	//method to initialize the first schedule randomly
	public static Schedule startingSched(SchedulingProblem problem){
		Schedule currSolution = problem.getEmptySchedule();

		for(int i = 0; i < problem.courses.size() -1; i++){
			//take the course to be scheduled
			Course c1 = problem.courses.get(i);
			boolean scheduled = false;

			for (int j = 0; j < c1.timeSlotValues.length; j++) {
				if (scheduled) break;
				if (c1.timeSlotValues[j] > 0) {
					for (int k = 0; k < problem.rooms.size(); k++) {
						if (currSolution.schedule[k][j] < 0) {
							currSolution.schedule[k][j] = i;
							scheduled = true;
							break;
						}//end if
					}//end for
				}//end if
			}//end for

		}//end for (going through all the courses)
		return currSolution;
	}//end startingSched

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
}//end SearchAlgorithm
