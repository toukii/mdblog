```go
package main

import "fmt"

func swap(a, b *int) {
	*a, *b = *b, *a
}

// 调用方法：km(arr,0,len(arr))
func km(arr []int, k, m int) {
	// fmt.Println(arr)
	if k > m {
		for i := 0; i <= m; i++ {
			fmt.Print(arr[i], " ")
		}
		fmt.Println()
	} else {
		for i := k; i <= m; i++ {
			swap(&arr[k], &arr[i])
			km(arr, k+1, m)
			swap(&arr[k], &arr[i])
		}
	}
}

func main() {
	arr := []int{1, 2, 3}
	km(arr, 0, len(arr)-1)
}

```