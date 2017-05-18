package main

import (
	"fmt"
	"os"
	"runtime/trace"
	"time"
)

func main() {
	f, err := os.Create("trace.out")
	if err != nil {
		panic(err)
	}
	defer f.Close()

	err = trace.Start(f)
	if err != nil {
		panic(err)
	}
	defer trace.Stop()

	tk := time.NewTicker(1e9)
	i := 0
	for {
		i++
		select {
		case <-tk.C:
			fmt.Print(i)
		}
		er := trace.Start(f)
		fmt.Print(er)
	}
	// Your program here
}
