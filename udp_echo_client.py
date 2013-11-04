# coding:utf-8

import socket,time

client = socket.socket( socket.AF_INET, socket.SOCK_DGRAM )
# 发送到本机端口17800
while True:
	client.sendto( "Hello Python", ('localhost', 5001) )
	time.sleep(2)

client.close()