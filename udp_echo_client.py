# coding:utf-8

import socket

client = socket.socket( socket.AF_INET, socket.SOCK_DGRAM )
# 发送到本机端口17800
while True:
	client.sendto( "Hello Python", ('localhost', 5000) )

client.close()