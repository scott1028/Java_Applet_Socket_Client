# coding:utf-8

import socket,time

client = socket.socket( socket.AF_INET, socket.SOCK_DGRAM )
# 发送到本机端口17800
while True:
	client.sendto( "Hello From Python UDP:"+time.ctime(), ('127.0.0.1', 5001) )
	time.sleep(2)

client.close()