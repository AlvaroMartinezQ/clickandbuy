API internal services: 

Endpoint: user data  
	* Receives: user data as String and an email as a String.  
	* Performs: email send to the requested user with his data. Runs it on a different system thread.  
	* /legacy/user/info  
	
WS endpoints:  
	* Connects all users to a same chat.  
	* /socket  
