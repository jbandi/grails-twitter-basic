class Status implements Serializable {

	String message
	Person person
	Date dateCreated
	
	static constraints = {
		message blank:false, size:1..140
	}
	
	transient jmsTemplate
	transient afterInsert = {
		try{
			jmsTemplate.convertAndSend("twitter", this)
		}
		catch(e){
			log.error "error sending JMS message: ${e.message}"
		}
	}

}