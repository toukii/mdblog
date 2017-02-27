FROM daocloud.io/library/golang:1.6.0

WORKDIR /app/gopath/mdblog
ENV GOPATH /app/gopath

RUN git clone --depth 1 git://github.com/toukii/mdbg.git . && go get -u github.com/toukii/mdbgEg && mv $GOPATH/bin/mdbgEg /bin/mdbgEg

EXPOSE 80

CMD ["/bin/mdbgEg"]


