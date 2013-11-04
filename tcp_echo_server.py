# coding:utf-8
# 實作 TCP Echo Server

import socket,re,sys,threading,time

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
sock.bind(("", 10080))  
sock.listen(10)

def echo(con,addr):
	while True:
		a=time.ctime();
		con.send(a);
		print a
		time.sleep(2)

while True:
	con, addr = sock.accept()
	threading.Thread(target=echo,args=(con,addr)).start();
