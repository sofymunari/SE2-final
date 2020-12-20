TEAM-15 SPRINT RETROSPECTIVE 3
=====================================
following sections:
- [process measures](#process-measures)
- [quality measures](#quality-measures)
- [general assessment](#assessment)

## PROCESS MEASURES 

### Macro statistics

-  7 stories committed -  7 stories done 
-  28 points committed - 28 points done 
-  72 hours planned - 71 hours spent (as a team)

**Our Definition of done is:**  

- Unit Tests passing
- Code review completed
- Code present on VCS
- End-to-End tests performed


### Detailed statistics

| Story  | # Tasks | Points | Hours est. | Hours actual|
|--------|---------|--------|------------|-------------|
| #0.1	 |   6     |   -    |     18	     |    18     |
| #0.2	 |   3	   |   -    |	  5	 	 |    5        |
| #0.3	 |   6     |   -    |     9	 	 |    6.5     |
| #0.4	 |   3	   |   -    |     6	 	 |    3.45       |
| #0.5	 |   2	   |   -    |     3.5 	 |    3.5       |
| 12   	 |   6     |   8    |     8.5      |    9.5        |  
| 13		 |   2	   |   2    |	  3.5      |    3.5        |
| 14	 |   2	   |   1    |	  1.5	 |    2      |
| 15 	 |   1 	   |   1    |	  1 	 |    0.5      |
| 16 	 |   4 	   |   8    |	  7.5 	 |    8.75      |
| 17	 	 |   3	   |   5    |	  4.5  	 |    5      |
| 18	 	 |   3	   |   3    |	  4	 	 |    5        |

- on average 1.7 hours spent per task
- with a std deviation of ___
- Total task estimation error ratio: sum of total hours estimation / sum of total hours spent from previous table: 1.014

  
## QUALITY MEASURES 

- Unit Testing:
  - Total hours estimated: 6.5
  - Total hours spent: 6.5
  - Nr of automated unit test cases: 15
  - Nr of automated unit tests: 267
  - Coverage: 89.9%
- E2E testing:
  - Total hours estimated: 3.5 
  - Total hours spent: 3.5 
- Code review 
  - Total hours estimated: 9
  - Total hours spent: 6.5
- Technical Debt management:
  - Hours estimated for remediation by SonarQube for next sprint: 2d
  - debt ratio: 0.9%
  - rating for each quality characteristic reported in SonarQube under "Measures" (namely reliability, security, maintainability ):
	  - namely reliability: A
	  - security: A
	  - maintenability: A
  

## ASSESSMENT

- What caused your errors in estimation (if any)?
	- We didn't estimate well the time to decrese the code smells because we based our estimations on the previous sprints but this time we had less code smells to be fixed.

- What lessons did you learn (both positive and negative) in this sprint?
	- POSITIVE: we learnt that having more scrum meetings is better to improve our efficency and to be more aware about team work.
	- NEGATIVE: we learnt that automating the E2E testing this late is more time consuming then doing it from the beginning.  

- Which improvement goals set in the previous retrospective were you able to achieve? 
	- we followed the documentation that we added about branches and this made our work more organized.
	- we started to automate the E2E testing.
   
- Which ones you were not able to achieve? Why?
	- we were not able to complete the automated E2E tests.

- Improvement goals for the next sprint and how to achieve them (technical tasks, team coordination, etc.)
	- we aim to complete the automated E2E testing.
	- we should finish our tasks a bit earlier like in the previous sprints in order to perform the tests some days before the demo.

- One thing you are proud of as a Team!!
	- we are proud that we improved in communication between team members that are working on related tasks and we increased the cohesion of the team.


