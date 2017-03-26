# AISchedulingProblem
## CS 4320/5314 Homework Assignment #2 \n Search for Room Scheduling \n DUE: Sun, March 26 at 11:59 PM

Objective: To explore the use of different search techniques for solving a complex optimization problem.
Groups: You may optionally work in groups of two (2) students for this project.

The Room Scheduling Problem:
The scenario you will study is based on creating a schedule that assigns courses to classrooms based on some criteria. The goal of your algorithm is to find the best possible schedule as quickly as possible using a variety of search techniques.
More specifically, we have a set of N rooms, a set of M courses that need to be scheduled, and a set of L buildings.
Each building has an associated location, given by (x,y) coordinates.

Each room has the following properties:
1) A building
2) A maximum capacity

Each course has the following properties:
1) An enrollment number
2) A value for being scheduled
3) A list of values for each of 10 available time slots
4) A preferred building

There are 10 possible time slots, and each room can have only one class scheduled in each time slot. In addition, courses can only be scheduled in rooms where the capacity is greater than the enrollment.

For each course, there is a list of values for each time slot. A value of 0 corresponds to infeasible (i.e., the course cannot be held at this time), while any other positive value is a bonus given for scheduling the course in that particular time slot.

Courses also have preferred buildings. Courses scheduled in another building receive a penalty based on the distance between where the course is actually scheduled and the preferred building.

A solution is a mapping from rooms and time slots to courses. That is, each room can be assigned to hold one course in each available time slot. Courses are identified by their indices from 0 to N-1.

The overall value of a schedule is calculated as follows:
* NEGATIVE_INFINITY if the schedule is invalid (e.g., courses assigned multiple times to more than one room or time slot).
* The sum of the values and time slot bonuses for all courses assigned to valid rooms (rooms with a large enough capacity).
* Subtracting the sum of the penalties for scheduling courses away from their preferred building.

You can find the exact definitions of these in the provided code. 

Your Assignment:
We have discussed many different search algorithms for problem solving, including BFS, DFS, iterative deepening, IDS, A*, Hill climbing, simulated annealing, and genetic algorithms. Your assignment is to implement and test two different search methods for solving this scheduling problem.
1) Implement a simulated annealing algorithm for solving this problem, and experiment with different temperature settings.
2) Implement backtracking search for constraint satisfaction for solving this problem, including at least three heuristics for improving the search algorithm.

For both of these algorithms you should test the performance of your methods empirically. For the simulated annealing approach you should show how different temperature setting affect the performance. For the CSP approach, you should show how each of the heuristics improves the performance of your algorithm.

Deliverables:
You will turn in two things:
1) Your Java code for the project, including a jar file and instructions for running your code.
2) A brief (2-4 pages) report describing the search methods you implemented and the results of your performance testing. You must include data from empirical tests that compare the performance of your algorithms (including different temperatures and heuristics) for problems of increasing size.

You will be evaluated based on whether you have working implementations of the algorithms that improve over simple baseline methods, as well as the quality of your testing and evaluation of the performance of your algorithms.
