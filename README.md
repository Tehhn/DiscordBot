# DiscordBot

This is a piece of software which showcases some of Selenium functions and possibilities.
However this is not to be taken as a good example of what Selenium 'should' be used for,
it just shows what Selenium 'could' be used for. I am sure that there are much better 
options to implement my idea, as well as other examples of using Selenium in a better way.

Program is written in Java, using Selenium toolkit and consists of:

	-Navigating to the discord web application
  
	-Reading login information and desired automatic message from file "input.txt"
  
	-Automatically typing in login form, loging in to the page and actively 
	looking for the new messages
	
Problems encountered:

	-Discord's web app wasn't really built for this which often made looking for webpage 
	elements seem impossible, and element-locator techniques were used to the best of my
	knowledge and situation present.(Program will most likely break after discord's 
	update)
  
	-Some elements were 'overlapped' by other elements, so seeing which element had 
	which event handler was sometimes hard or element itself was impossible to reach,
	which i overcame with 'Actions' class, by 'simulating' how human would click, without
	caring which element has functionality .click(), but rather moving the cursor to it
	and just, you know, clicking it.
	
Setting the sofware up:

	-your input.txt file should look like this:
	
	youremail@(gmail|yahoo|outlok| ....).com
	password
	Your deisred automatic message here (example: "I'm not home right now, I'll call you when I am back")
  
  Important to note:
  
-String driverPath must be changed to navigate to webdriver on your local machine

-For this project,chrome webdriver for Chrome version 85.xx was used.
