
class BootStrap {

	def authenticateService 

     def init = { servletContext ->
	
		println "Bootstraping..."
		

		def encodedPassword = authenticateService.encodePassword("jba") 
		
		def a = new Authority(authority: "ROLE_USER", description: "Default")
		def p = new Person(username: "jba", userRealName: "Jonas", passwd: encodedPassword, enabled:true, email:"test@test.com")
		a.addToPeople(p)
		a.save()
		
		new Requestmap(url: "/status/*", configAttribute: "ROLE_USER").save()
		
		println "Bootstraped!"
     }
     def destroy = {
     }
} 