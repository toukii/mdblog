package main

import (
	"fmt"
	"net/http"
	"time"
)

var (
	ticker *time.Ticker
)

func init() {
	ticker = time.NewTicker(5e9)
}

func main1() {
	http.HandleFunc("/", tickerHandler)
	http.ListenAndServe(":8080", nil)
}

func tickerHandler(rw http.ResponseWriter, req *http.Request) {
	select {
	case <-ticker.C:
		fmt.Println("中奖了", time.Now())
		break
	default:
		// fmt.Print("呵")
	}
}

func main() {
	for {
		go func() {
			select {
			case <-ticker.C:
				fmt.Println("\nbingo", time.Now())
			default:
				// fmt.Print(".")
			}
		}()
		time.Sleep(1e7)
	}
}
