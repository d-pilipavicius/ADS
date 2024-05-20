main.jar: bin/Manifest.txt bin/Main.class 
	C:/PROGRA~1/Java/jdk1.8.0_211/bin/jar -cfm main.jar bin/Manifest.txt -C bin .
	
bin/Main.class: bin/Main.class
	C:/PROGRA~1/Java/jdk1.8.0_211/bin/javac -d bin src/*.java 
	
bin/Manifest.txt:
	echo Main-Class: Main > bin/Manifest.txt