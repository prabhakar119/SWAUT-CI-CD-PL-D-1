#getting base image ubuntu
FROM ubuntu

# Set Maintainer
MAINTAINER karthikreddy reddykarthikc@yahoo.com
RUN apt-get update
CMD ["echo", "Hello world..! from my first docker image"]

#set a Health Check
HEALTHCHECK --interval=5s \
			--timeout=5s \
			CMD curl -f http://127.0.0.1:7000 || exit 1

#tell docker what port to expose
EXPOSE 7000	

