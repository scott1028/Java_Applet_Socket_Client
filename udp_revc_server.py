# coding:utf-8

import socket,time

server = socket.socket( socket.AF_INET, socket.SOCK_DGRAM )
server.bind(('127.0.0.1',5001))

while True:
		data, addr=server.recvfrom(500)
		if  len(data)>0:print data
		time.sleep(2)

server.close()
