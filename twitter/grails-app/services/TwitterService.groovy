class TwitterService {

    boolean transactional = true
	static expose = ['jms']
	static destination = "twitter"
	
	def twitterCache
	
    def onMessage(obj) {
		
		Person p = obj.person
		def following = Person.withCriteria{
			projections { property "username"}
			following {
				eq('username', p.username)
			}
		}
		
		for(username in following){
			twitterCache.remove(username)
		}
    }
}
