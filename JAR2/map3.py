#!/usr/bin/env python

import sys

elementSet=set(['all', "she'll", 'just', 'being', 'when', 'over', 'both', 'including', 'yourselves', '{', '.', 'its', 'before', '$', 'also', "when's", 'herself', 'had', 'except', 'should', "he'd", 'to', 'only', "there's", 'those', 'under', 'ours', 'he', 'might', '<', 'ought', 'do', 'them', 'his', 'around', 'get', 'very', "who's", '[', "they'd", 'cannot', 'every', "you've", 'they', 'despite', 'not', 'during', 'him', 'nor', '&', "we'll", 'like', 'did', "they've", '^', 'this', 'she', 'each', 'further', 'through', 'where', '|', 'few', "why's", 'because', 'says', 'often', 'doing', "i'll", 'theirs', 'some', 'up', 'likely', 'are', 'dear', 'our', 'beyond', 'ourselves', 'out', 'what', 'said', 'for', 'since', 'while', '/', 'yet', 'below', 'behind', 'does', '(', 'above', 'between', 'got', 'neither', 'ever', 'across', '?', 'either', 'be', 'we', 'who', 'however', 'here', 'let', 'hers', 'along', 'by', 'on', 'about', 'would', 'of', 'could', "he's", 'against', "i'd", 'plus', "we've", "i'm", '~', 'or', 'among', 'own', '*', 'into', 'within', 'yourself', 'down', 'been', 'twas', 'throughout', 'your', "you'd", "you're", '"', 'from', "how's", 'her', 'whom', "it's", 'there', 'least', "he'll", 'their', 'too', 'themselves', ':', 'was', 'until', '\\', 'more', 'wants', ',', 'himself', "where's", 'that', 'concerning', 'but', 'else', ';', '@', 'with', 'than', "here's", 'must', 'me', "they're", 'myself', 'has', 'these', 'say', 'us', 'will', 'near', 'can', 'were', 'following', '>', 'my', "we'd", 'and', 'then', 'almost', 'is', 'am', 'it', 'an', 'as', 'itself', 'at', 'have', 'in', 'any', 'if', '!', 'again', '%', 'no', 'rather', "i've", 'able', 'same', 'tis', 'how', 'other', 'which', 'you', "what's", 'towards', 'may', 'after', 'upon', '#', "let's", 'most', 'such', 'yours', ']', 'why', 'a', 'off', "'", '`', 'i', "she'd", "they'll", ')', "you'll", 'without', 'so', "we're", "she's", 'the', "that's", '}', 'having', 'once'])

for line in sys.stdin:
	for word in line.strip().split():
			if word in elementSet:
				print 'yo'	
				continue;
			else:
				print '{}\t{}'.format(word, 1)
