FROM daocloud.io/library/golang:1.6.0

WORKDIR /app/gopath/mdblog
ENV GOPATH /app/gopath

RUN git clone --depth 1 git://github.com/toukii/mdblog.git . && go get -u github.com/toukii/mdbgEg && mv $GOPATH/bin/mdbgEg /bin/eg

EXPOSE 80

CMD ["/bin/eg"]


