@REM ----------------------------------------------------------------------------
@REM Maven Wrapper startup batch script
@REM ----------------------------------------------------------------------------
@IF "%__MVNW_ARG0_NAME__%"=="" (SET "MVN_CMD=mvn.cmd") ELSE (SET "MVN_CMD=%__MVNW_ARG0_NAME__%")

@SET MAVEN_WRAPPER_DIR=%~dp0.mvn\wrapper
@SET WRAPPER_JAR="%MAVEN_WRAPPER_DIR%\maven-wrapper.jar"
@SET WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

@SET DOWNLOAD_URL=https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar

@SET JAVA_HOME_TO_USE=%JAVA_HOME%

FOR /F "usebackq tokens=1,2 delims==" %%A IN ("%MAVEN_WRAPPER_DIR%\maven-wrapper.properties") DO (
    IF "%%A"=="distributionUrl" SET DISTRIBUTION_URL=%%B
)

IF NOT EXIST %WRAPPER_JAR% (
    echo Downloading Maven wrapper...
    powershell -Command "Invoke-WebRequest -Uri '%DOWNLOAD_URL%' -OutFile '%MAVEN_WRAPPER_DIR%\maven-wrapper.jar'"
)

"%JAVA_HOME_TO_USE%\bin\java.exe" ^
  -classpath %WRAPPER_JAR% ^
  "-Dmaven.multiModuleProjectDirectory=%~dp0" ^
  %WRAPPER_LAUNCHER% %*
