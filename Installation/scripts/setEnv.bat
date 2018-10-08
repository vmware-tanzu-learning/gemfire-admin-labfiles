rem Based on your Java installation
rem Could also use something like: JAVA_HOME-=%ProgramFiles%\Java\jdk1.8.
set JAVA_HOME=C:\Program^ Files\Java\jdk1.8.0

rem Based on your lab installation
set COURSE_HOME=C:\GemFire-Admin-9.0.4c.RELEASE
set COURSE_RUN=%COURSE_HOME%\runLab

set GEMFIRE=%COURSE_HOME%\pivotal-gemfire-9.0.4
set PATH=%GEMFIRE%\bin;%JAVA_HOME%\bin;%COURSE_RUN%\management_scripts\windows;%PATH%

rem Set CLASSPATH mainly for client applications
set CLASSPATH=%COURSE_RUN%\bookShop-1.0.jar;%GEMFIRE%\lib\*
