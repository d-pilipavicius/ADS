test.jar: Manifest.txt bin/Main.class 
	C:/PROGRA~1/Java/jdk1.8.0_211/bin/jar -cvfm test.jar Manifest.txt -C bin .
	
bin/Main.class: src/Board.java src/EightBishops.java src/Algorithm.java src/Main.java
	C:/PROGRA~1/Java/jdk1.8.0_211/bin/javac -d bin src/*.java 
	
Manifest.txt:
	echo Main-Class: Main > Manifest.txt