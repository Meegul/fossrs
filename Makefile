CC = javac

all:
	$(CC) -d out src/fossrs/account/XPInstance.java
	$(CC) -d out -classpath out src/fossrs/account/Skill.java
	$(CC) -d out -classpath out src/fossrs/account/OSRSAccount.java
	$(CC) -d out -classpath out src/fossrs/Test2.java

clean:
	rm -rf out/*