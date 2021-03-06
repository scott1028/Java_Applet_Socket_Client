// 基本
#include <stdio.h>
#include <sys/types.h> 
#include <sys/socket.h>
#include <netinet/in.h>

// 額外需要 
#include <string.h>		// for bzero()
#include <iostream>		// for read()
#include <fstream>		// for read()
#include <unistd.h>		// for sleep()

/*
	For GCC3
	C++ 版的 Socket Server
	可搭配 Java Applet Socket Client 使用
*/

int main( int argc, char *argv[] )
{
	socklen_t sockfd, newsockfd, portno, clilen;
	char buffer[256];
	struct sockaddr_in serv_addr, cli_addr;
	int n;

	/* First call to socket() function */
	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if (sockfd < 0) {
		perror("ERROR opening socket");
		exit(1);
	}

	/* Initialize socket structure */
	bzero((char *) &serv_addr, sizeof(serv_addr));
	portno = 5000;
	serv_addr.sin_family = AF_INET;
	serv_addr.sin_addr.s_addr = INADDR_ANY;
	serv_addr.sin_port = htons(portno);

	/* Now bind the host address using bind() call.*/
	if (bind(sockfd, (struct sockaddr *) &serv_addr, sizeof(serv_addr)) < 0){
		 perror("ERROR on binding");
		 exit(1);
	}

	/* 
	Now start listening for the clients, here process will
	go in sleep mode and will wait for the incoming connection
	*/
	listen(sockfd,5);
	clilen = sizeof(cli_addr);

	// 進入等待連線 For-Loop
	while(true){
		/* Accept actual connection from the client */
		newsockfd = accept(sockfd, (struct sockaddr *)&cli_addr, &clilen);
		if (newsockfd < 0){
			perror("ERROR on accept");
			exit(1);
		}

		/* 如果不打算讀取資料，就把這邊註解 */
		// bzero(buffer,256);
		// n = read( newsockfd,buffer,255 );
		// if (n < 0){
		// 	perror("ERROR reading from socket");
		// 	exit(1);
		// }
		// printf("Here is the message: %s\n",buffer);

		/* 傳送資料給客戶端 */
		while(true){
			sleep(2);	// 2 秒延遲
			n = write(newsockfd,"I got your message!!!!!",18+5);		// 18:文字, 5:! 
			if (n < 0){
				perror("ERROR writing to socket");
				exit(1);
			}
		}

		// 關閉 Client 連線
		close(newsockfd);
	}
	return 0; 
}
