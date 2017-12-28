#!/usr/bin/env python
'''
Created on 2013-7-3

@author: hu-t
'''

import sys
import os
import commands
import ConfigParser

GMOND_FILE = '/etc/ganglia/gmond.conf'
TEMP_FILE = '/etc/ganglia/gmond_tmep.conf'

def create_temp_file(gmondFile, tempFile, ip):
	for rLine in gmondFile:
		# find host
		if rLine.startswith("  host = "):
			tempFile.write("  host = " + ip + "\n")
		else:
			tempFile.write(rLine)

if __name__ == '__main__':
	
	# Get input parameters
	args = sys.argv
	if(args.__len__() != 2):
		print '[ganglia] No input parameters!'
		sys.exit(1)

	# change gmond file
	try:
		gmondFile = open (GMOND_FILE, "r")
		tempFile = open(TEMP_FILE, "w")
		create_temp_file(gmondFile, tempFile, args[1])
		os.rename(TEMP_FILE, GMOND_FILE)
	except Exception, e:
		print '[ganglia] Change gmond file failure!'
		sys.exit(1)
	finally:
		if(gmondFile != None):gmondFile.close()
		if(tempFile != None):tempFile.close()
	print '[ganglia] Change gmond file successful!'