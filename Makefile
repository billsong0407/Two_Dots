######################################
# Author: 	Joost Vandorp, S. Smith	 #
# Revised: 	Thursday, Feb 24, 2017	 #
# Description:	"MAKEFILE"		 #
######################################

# Assumes JUnit is installed
# Assumes CLASSPATH has been set for Junit

JFLAGS = -g
JCLASS = -cp ./src:.:junit-4.5.jar
#JCLASS = -cp ./src:.:$(CLASSPATH):/Users/billsong/Desktop/junit-4.5.jar
#JCLASS = -cp ./src:.:$(CLASSPATH):/usr/share/java/junit4-4.5.jar # on mills
JC = javac
JVM = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $(JCLASS) $*.java

CLASSES = \
	src/BoardT.java \
	src/CellType.java \
	src/UserInterface.java \
	src/GameController.java \
	src/EndCondition.java \
	src/EndByMoves.java \
	src/EndByTime.java \
	src/TestBoardT.java \
	src/TestEndByMoves.java \
	src/TestEndByTime.java \
	src/AllTests.java \

MAIN = AllTests

default: classes

classes: $(CLASSES:.java=.class)

doc:
	doxygen doxConfig
	cd latex && $(MAKE)

test: src/$(MAIN).class
	$(JVM) $(JCLASS) org.junit.runner.JUnitCore src.$(MAIN)

demo: src/Demo.java
	$(JC) $(JCLASS) $(JFLAGS) src/Demo.java
	$(JVM) $(JCLASS) Demo

clean:
	rm -rf html
	rm -rf latex
	cd src
	rm **/*.class
