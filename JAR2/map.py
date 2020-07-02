#!/usr/bin/env python
import subprocess
import sys
cat=subprocess.Popen(['hdfs','dfs','-cat','wordcount/stopwords.txt'],stdout=subprocess.PIPE).communicate()[0]
out=cat.split("\n")
elementSet=set()
for stopword in out:
	elementSet.add(stopword)
for line in sys.stdin:
	for word in line.strip().split():
			if word in elementSet:
				continue;
			else:
				print '{}\t{}'.format(word, 1)
