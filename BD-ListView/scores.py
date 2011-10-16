import sys

i = 0
j = 0

while (i < 10):
	j = 0
	while (j < 10):
		if ( i + j < 10):
			print "<item>" + str(i + j) + "</item>"
		if ( i - j >= 0):
			print "<item>" + str(i + j + 10) + "</item>"
		if ( i * j < 10):
			print "<item>" + str(i + j + 20) + "</item>"
		j += 1
	i += 1
