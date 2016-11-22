#!/bin/bash
if [[ $# < 5 ]]; then
	echo "Please provide required arguments."
	echo "Usage: ./runsim.sh port localhostname peer1 peer2 peer3 "
	exit
fi
SERVER_CLASS="$PWD/ServerEntry.class"
if [ ! -f "$SERVER_CLASS" ]; then
	echo "no .class files found on system"
	echo "please run make first."
	exit
fi

numIter=1000000
byztProb=(10 1000 10000)
eventProb=(1 2 3 4 5)

#p0= $2

for i in "${byztProb[@]}" 
do
	for j in "${eventProb[@]}" 
	do
		echo "Running simulation with" $numIter "iterations, with event prob" "$j" "and bzt prob" "$i".
		./startpeer.sh $1 $2 $3 $4 $5 $numIter "$j" "$i" 
		echo "renaming logfile..."
		mv node_$2.dat $2"_"$i"_"$j
		sleep 1
	done
done
