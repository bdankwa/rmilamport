
BIN_DIR = ./
SERVER_DIR = ./
COMMON_DIR = ./

SERVER_SRC = $(SERVER_DIR)Comms.java $(SERVER_DIR)CommsImpl.java $(SERVER_DIR)Message.java $(SERVER_DIR)RMIComms.java \
			 $(SERVER_DIR)RMIServer.java $(SERVER_DIR)RPOInterface.java $(SERVER_DIR)ServerEntry.java \
			 $(SERVER_DIR)Event.java $(SERVER_DIR)InternalEvent.java $(SERVER_DIR)LogicalClock.java \
			 $(SERVER_DIR)Packet.java $(SERVER_DIR)ReceiveEvent.java $(SERVER_DIR)SendEvent.java
			 

COMMON_SRC = $(COMMON_DIR)SFTPFile.java

CLASSES = $(SERVER_SRC)

JFLAGS = -classpath "." -g -d $(BIN_DIR)
JC = javac

.SUFFIXES:.java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	rm -fr $(BIN_DIR)/*.class $(BIN_DIR)/*.class