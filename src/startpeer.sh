#!/bin/bash
if [[ $# < 8 ]]; then
	echo "Please provide port number for application."
	echo "Usage: ./startpeer port localhostname peer1 peer2 peer3 iterations eventProp byzantineProb"
	exit
fi
SERVER_CLASS="$PWD/ServerEntry.class"
if [ ! -f "$SERVER_CLASS" ]; then
	echo "server class not found on system"
	echo "please run make first."
	exit
fi
#echo "Starting RMIregistry on port $1 in the background..."
#cd server/bin
#rmiregistry $1&
#echo $PWD
#cd ../
SQLPATH= $PWD
echo "Starting Peer Server on port $1 on machine $2."
#This must be run from /bin
java -cp "."  -Djava.security.policy=policy ServerEntry $1 $2 $3 $4 $5 $6 $7 $8

