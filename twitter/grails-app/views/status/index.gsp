<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8">
		<meta name="layout" content="main">
		<title>What are you doing?</title>
		<g:javascript library="prototype"></g:javascript>
	</head>
	
	<body id="index">
		
		<div id="searchBox">
			<g:form controller="searchable">
				<strong>Find People: </strong>
				<g:textField name="q"></g:textField>
			</g:form>
		</div>
		
		<h1 id="what_are_you_doing?">What are you doing?</h1>
		
		<div id="updateForm">
			<g:formRemote name="messageForm" update="messages" url="[action:'update']">
			<g:textArea name="message"></g:textArea>
			<br/>
			<g:submitButton name="Update"></g:submitButton>
			</g:formRemote>
		</div>
		<div id="messages">
			<g:render template="message" var="status" collection="${messages}"></g:render>
		</div>
	</body>
</html>