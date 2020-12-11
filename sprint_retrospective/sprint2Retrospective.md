TEAM-15 SPRINT RETROSPECTIVE 2
=====================================
following sections:
- [process measures](#process-measures)
- [quality measures](#quality-measures)
- [general assessment](#assessment)

## PROCESS MEASURES 

### Macro statistics

-  6 stories committed -  6  stories done 
-  23 points committed - 23 points done 
-  70.5 hours planned - 71.5 hours spent (as a team)

**Our Definition of done is:**  

- Unit Tests passing
- Code review completed
- Code present on VCS
- End-to-End tests performed


### Detailed statistics

| Story  | # Tasks | Points | Hours est. | Hours actual|
|--------|---------|--------|------------|-------------|
| #0.1	 |   6     |   -    |     6	     |    6.25     |
| #0.2	 |   1	   |   -    |	  4	 	 |    4        |
| #0.3	 |   3     |   -    |     4	 	 |    3.25     |
| #0.4	 |   9	   |   -    |     9	 	 |    11       |
| #0.5	 |   6	   |   -    |     18 	 |    18       |
| #0.6	 |   1	   |   -    |     2 	 |    2.5      |
| 2   	 |   1     |   8    |     2      |    2        |  
| 7	 	 |   3	   |   2    |	  3  	 |    3.5      |
| 8	 	 |   2	   |   2    |	  2	 	 |    1        |
| 9		 |   3	   |   2    |	  8      |    8        |
| 10	 |   3	   |   5    |	  4.5	 |    4.5      |
| 11 	 |   5 	   |   5    |	  8 	 |    7.5      |
  
- on Average a little more than 1h 30m spent per task
- with a std deviation of 0.9416 
- Total task estimation error ratio: sum of total hours estimation / sum of total hours spent from previous table: 0.986

  
## QUALITY MEASURES 

- Unit Testing:
  - Total hours estimated: 7 h
  - Total hours spent: 6.25 h
  - Nr of automated unit test cases: 11
  - Nr of automated unit tests: 219
  - Coverage: 91.5%
- E2E testing:
  - Total hours estimated: 2 
  - Total hours spent: 2.5 
- Code review 
  - Total hours estimated: 6
  - Total hours spent: 6.25
- Technical Debt management:
  - Hours estimated for remediation by SonarQube for next sprint: 1d
  - debt ratio: 0.3%
  - rating for each quality characteristic reported in SonarQube under "Measures" (namely reliability, security, maintainability ):
	  - namely reliability: A
	  - security: A
	  - maintenability: A
  

## ASSESSMENT

- What caused your errors in estimation (if any)?
	- the major estimation error was made on solving github issues. The team underestimated the time needed to fix the problems, probably this was due to the fact that this was the first time we encountered this type of task.

- What lessons did you learn (both positive and negative) in this sprint?
	- POSITIVE: we learnt to assign tasks in a better way not just dividing them in equals parts but beasing on each one skills
	- NEGATIVE: we learnt how to work on git branches and have a common guideline in order to be more aware of the state of each task   

- Which improvement goals set in the previous retrospective were you able to achieve? 
	- we improved a lot in estimating time. We actually estimated time for the E2E testing.
	- we managed to finish our tasks a little earlier in order to have some time before the deadline to prepare for demo
    - we added some documentation about how to work with branches
   
- Which ones you were not able to achieve? Why?
	- we were not able to keep our scrum meetings short, even if we managed to have many more
   
- Improvement goals for the next sprint and how to achieve them (technical tasks, team coordination, etc.)
	- we should follow the documentation we added about branches in order to work better and cleaner and also to be compliant with other team members
	- we should add automated E2E testing or add dedicated documentation .

- One thing you are proud of as a Team!!
	- we are proud that we always try to work in a professional environment trough the useage of best practices 


