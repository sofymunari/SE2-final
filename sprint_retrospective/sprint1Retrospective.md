TEAM-15 SPRINT RETROSPECTIVE 1
=====================================
following sections:
- [process measures](#process-measures)
- [quality measures](#quality-measures)
- [general assessment](#assessment)

## PROCESS MEASURES 

### Macro statistics

-  6 stories committed -  5  stories done 
-  21 points committed - 18 points done 
-  66 hours planned - 68.25 hours spent (as a team)

**Our Definition of done is:**  

- Unit Tests passing
- Code review completed
- Code present on VCS
- End-to-End tests performed


### Detailed statistics

| Story  | # Tasks | Points | Hours est. | Hours actual |
|--------|---------|--------|------------|-------------|
| #0.0   |   9     |   -    |   12.5     |   16.25     |
| #0.1	 |   6	   |   -    |	17	 |   17	       |
| #0.2	 |   2	   |   -    |   2	 |   3         |
| 1    	 |   6     |   5    |   6.5      |   8         |  
| 2	 |   6	   |   3    |	10.5	 |   8.5       |
| 3	 |   4	   |   3    |	6	 |   6	       |
| 4	 |   2	   |   2    |	1.5      |   1.5       |
| 5	 |   5	   |   5    |	7	 |   6.5       |
| 6	 |   2	   |   3    |	3	 |   1.5       |
  
- on Average a little more than 1h 30m spent per task
- with a std deviation of 0.99 

- Total task estimation error ratio: sum of total hours estimation / sum of total hours spent from previous table: 0.967

  
## QUALITY MEASURES 

- Unit Testing:
  - Total hours estimated: 8 h
  - Total hours spent: 9 h
  - Nr of automated unit test cases: 11
  - Nr of automated unit tests: 165
  - Coverage: 85.5%
- E2E testing:
  - Total hours estimated: -- (this testing was included in developing tasks)
  - Total hours spent: -- (not tracked specifically)
- Code review 
  - Total hours estimated: 2
  - Total hours spent: 2
- Technical Debt management:
  - Hours estimated for remediation by SonarQube for next sprint: 2d
  - debt ratio: 1.2%
  - rating for each quality characteristic reported in SonarQube under "Measures" (namely reliability, security, maintainability ):
	  - namely reliability: C
	  - security: A
	  - maintenability: A
  

## ASSESSMENT

- What caused your errors in estimation (if any)?
	- the major estimation error was made on setting up the enviroment. The team underestimated the time needed to discuss and decide all the base features ( like DB skema and general project skema), that are fundamental for a good set up of the enviroment.

- What lessons did you learn (both positive and negative) in this sprint?
	- POSITIVE: we learnt that assigning roles is a good way to code better and faster since each one of us can improve faster and specify on a topic
	- NEGATIVE: we learnt not to divide tasks equally because we have different background knowledges and we don't code all at same speed. We must respect the ability and knowledge of everybody through better assignment of tasks

- Which improvement goals set in the previous retrospective were you able to achieve? 
	- we improved a lot in estimating time. Past retrospective we had a estimation error ratio of 0.39 against 0.97 of this time. In particolar we divided stories in tasks much better than last time having a std deviation much lower and an avarage time per task quite good.
	- we estimated time for testing and we did much more testing than last sprint, reaching a way higher coverage.  
  
- Which ones you were not able to achieve? Why?
	- we still haven't completly nailed the testing estimation, since we didn't consider the end 2 end testing as a separate task, so we were not able to track the related time spent on this sprint.
	- we can still improve on documentation of code and on coordination between team members to optimize coding parallelization . 

- Improvement goals for the next sprint and how to achieve them (technical tasks, team coordination, etc.)
	- we should work more on coordinate better through more frequent stand up meetings,  keeping them short (as they should be). Along with this we should also have longer meetings between fewer team members. To sum up, we need each member to work in strict contact with the other member(s) working on related tasks, but at the same time always have a nice overview of the global ongoing work.
	- we should always focus on clean work and remember to estimate time for all types of testing .

- One thing you are proud of as a Team!!
	- we are proud that we really improved a lot on time estimation ( and in general in sprint planning) and we manage to produce much more code and also much cleaner than last time. 


