<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8">
		<title>Search Result</title>
		
		<body id="index" onload="">
			<h1 id="search_results">Search Results</h1>
			<g:each var="person" in="${searchResult?.results}">
				<div id="name">
					${person.userRealName}
					
					<g:link controller="status" action="follow" id="${person.id}">Follow</g:link>
				</div>
			</g:each>
		</body>
		
	</head>
</html>