```
<c:forEach var="item" items="${news.articles}" varStatus="status">  
	<c:choose>  
		<c:when test="${status.index <= 0}">  
			第一个
		</c:when>  
		<c:otherwise>  
			不是第一个
		</c:otherwise>  
	</c:choose>  
	${status.index + 1}
</c:forEach>
```