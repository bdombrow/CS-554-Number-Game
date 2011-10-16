import sys

i = 0
j = 0

while (i < 10):
	j = 0
	while (j < 10):
		if ( i + j < 10):
			print "<item>" + str(i) + "+" + str(j) + "=" + str(i+j) + "</item>"
		if ( i - j >= 0):
			print "<item>" + str(i) + "-" + str(j) + "=" + str(i-j) + "</item>"
		if ( i * j < 10):
			print "<item>" + str(i) + "*" + str(j) + "=" + str(i*j) + "</item>"
		j += 1
	i += 1
