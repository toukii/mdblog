FROM golang

WORKDIR /app/gopath/mdblog
ENV GOPATH /app/gopath

RUN git clone --depth 1 git://github.com/shaalx/mdbg.git .

RUN go get -u github.com/shaalx/mdbgEg
RUN mv $GOPATH/bin/mdbgEg /bin/mdbgEg

EXPOSE 80

CMD ["/bin/mdbgEg"]


