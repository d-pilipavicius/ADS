if .%USERDOMAIN% == .DESKTOP-FLJJFA8 goto :myPC
path C:\PROGRA~2\Dev-Cpp\MinGW64\bin\;%path"
mingw32-make.exe
goto :makeRun

:myPC
make
java -jar main.jar src/Names.txt 80 > main.txt

pause

:makeRun
C:/PROGRA~1/Java/jdk1.8.0_211/bin/java -jar main.jar src/Names.txt 80 > main.txt

pause