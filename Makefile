CC = javac
DEBUG = -Xlint
FLAGS = 

debug:
	$(CC) $(DEBUG) $(FLAGS) -d out src/fossrs/account/XPInstance.java
	$(CC) $(DEBUG) $(FLAGS) -d out -classpath out src/fossrs/account/Skill.java
	$(CC) $(DEBUG) $(FLAGS) -d out -classpath out src/fossrs/account/OSRSAccount.java
	$(CC) $(DEBUG) $(FLAGS) -d out -classpath out src/fossrs/account/AccountManager.java
	$(CC) $(DEBUG) $(FLAGS) -d out -classpath out src/fossrs/Test.java
	$(CC) $(DEBUG) $(FLAGS) -d out -classpath out src/fossrs/Test2.java
	$(CC) $(DEBUG) $(FLAGS) -d out -classpath out src/fossrs/ListAndSaveAccounts.java
	$(CC) $(DEBUG) $(FLAGS) -d out -classpath out src/fossrs/ShowSavedAccountsHistories.java

all:
	$(CC) $(FLAGS) -d out src/fossrs/account/XPInstance.java
	$(CC) $(FLAGS) -d out -classpath out src/fossrs/account/Skill.java
	$(CC) $(FLAGS) -d out -classpath out src/fossrs/account/OSRSAccount.java
	$(CC) $(FLAGS) -d out -classpath out src/fossrs/account/AccountManager.java
	$(CC) $(FLAGS) -d out -classpath out src/fossrs/Test.java
	$(CC) $(FLAGS) -d out -classpath out src/fossrs/Test2.java
	$(CC) $(FLAGS) -d out -classpath out src/fossrs/ListAndSaveAccounts.java
	$(CC) $(FLAGS) -d out -classpath out src/fossrs/ShowSavedAccountsHistories.java

clean:
	rm -rf out/*