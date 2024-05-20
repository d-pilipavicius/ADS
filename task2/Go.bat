if .%USERDOMAIN% == .DESKTOP-FLJJFA8 goto :myPC
path C:\PROGRA~2\Dev-Cpp\MinGW64\bin\;%path"
mingw32-make.exe
goto :stop

:myPC
mingw32-make.exe

:stop