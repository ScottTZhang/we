JAVAC=javac
JAVA=java
CP=.:./lib/junit-4.11.jar:./lib/hamcrest-core-1.3.jar
all: WhiteElephant.class

WhiteElephant.class: WhiteElephant.java
	@$(JAVAC) WhiteElephant.java

WhiteElephantTest.class: WhiteElephant.class WhiteElephantTest.java
	@$(JAVAC) -cp $(CP) WhiteElephantTest.java

.PHONY: clean test

test: WhiteElephantTest.class
	@$(JAVA) -cp $(CP) org.junit.runner.JUnitCore WhiteElephantTest

clean:
	@rm -rf *.class
