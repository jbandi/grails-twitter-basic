// Place your Spring DSL code here
beans = {
    twitterCache(org.springframework.cache.ehcache.EhCacheFactoryBean){
		timeToLive = 1200
	}
}