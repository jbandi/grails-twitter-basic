import net.sf.ehcache.Element
import grails.converters.*
class StatusController {
	
	def twitterCache
	
	def index = {
		println "loading messages ..."
		def messages = twitterCache.get(principalInfo.username)?.value
		if(!messages){
			println "loading messages from db..."
			messages = findStatusMessages()
			twitterCache.put new Element(principalInfo.username, messages)
		}
		def feedOutput = {
			title = "Twitter clone RSS feed"
			description = "Twitter clone RSS feed"
			link = g.link(controller:"status", absolute:"true")
			for(m in messages) {
				entry("${m.person.userRealName} posted:") {
					link = g.link(controller: "status", absolute: "true")
					m.message
				}
			}
		}
		withFormat {
			html([messages:messages])
			xml {
				render messages as XML
			}
			rss {
				render(feedType:"rss", feedOutput)
			}
		}
	}
	
	private findStatusMessages() {
		def p = lookupPerson()
		
		def messages = Status.withCriteria {
			or {
				person {
					eq ('username', p.username)
				}
				if (p.following) {
					inList('person', p.following)
				}
			}
			maxResults 10
			order 'dateCreated', 'desc'
		}
		
		return messages
	}
	
	def update = {
		def status = new Status(params)
		status.person = lookupPerson()
		status.save()
		
		twitterCache.remove(principalInfo.username)
		
		def messages = findStatusMessages()
		
		render template:"message", var:"status", collection:messages
	}
	
	def follow = {
		def p = Person.get(params.id)
		
		if(p){
			def current = lookupPerson()
			current.addToFollowing(p)
			current.save()
		}
		redirect action:"index"
	}
	
	private lookupPerson() {
		println principalInfo
		Person.findByUsername(principalInfo.username)
	}
}