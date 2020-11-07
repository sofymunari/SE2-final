# **FRONT END**

## code writing practices
* **React Components** are written in Camel Case, starting with uppercase letter. The name must describe the role of the component. Are group per Semantic Usage.
* **Methods** are written in Camel Case; starting with lowercase letter. The name must describe the function action.
* **Routes** are written with all lowercase letters and should be related with the Component that they lead to.  
* **Documentation** All main classes should be reported below specifying if present: STATE, ROUTES, PROPS and important 
METHODS
* **API functions** Follow the same rules of methods

## main classes:
 * **App.js**: main logic of the front-end
	* STATE: student(null, username of student logged); teacher(null,username);
	* ROUTES:
		1. "/": main page
		2. "/studentlogin": **StudentLogin.js** 
		3. "/teacherLogin": **TeacherLogin.js**
* **StudentLogin.js**: page to login a student
	* PROPS: *LoginStudent()* 
	* STATE: username,password  
*  **TeacherLogin.js**: page to login a teacher
	* PROPS: *LoginTeacher()* 
	* STATE: username,password  
