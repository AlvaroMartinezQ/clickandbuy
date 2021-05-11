API internal services: 

Endpoint: user data  
	:heavy_exclamation_mark: Receives: user data as String and an email as a String.  
	:heavy_exclamation_mark: Performs: an email send to the requested user with his data. Runs it on a different system thread.  
	:heavy_exclamation_mark: Returns: a String.  
	:heavy_exclamation_mark: /legacy/user/info  

Endpoint: user orders  
	:heavy_exclamation_mark: Receives: user data as String and an email as a String.  
	:heavy_exclamation_mark: Performs: an email send to the requested user with his data. Runs it on a different system thread.  
	:heavy_exclamation_mark: Returns: a String.  
	:heavy_exclamation_mark: /ordermail/send  

WS endpoints:  
	:heavy_exclamation_mark: Connects all users to a same chat.  
	:heavy_exclamation_mark: This enpoint is used through js in the main app.  
	:heavy_exclamation_mark: /socket  
