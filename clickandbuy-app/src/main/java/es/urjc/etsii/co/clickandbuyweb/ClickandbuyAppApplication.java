package es.urjc.etsii.co.clickandbuyweb;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;


@EnableCaching
@SpringBootApplication
@EnableHazelcastHttpSession
public class ClickandbuyAppApplication {
	
	private static final Log LOG = LogFactory.getLog(ClickandbuyAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ClickandbuyAppApplication.class, args);
	}
	
	@Bean
    public CacheManager cacheManager(HazelcastInstance hazelcastInstance) {
    	LOG.info("Activating cache...");
    	return new HazelcastCacheManager(hazelcastInstance);
    }
	
	@Bean
	public Config config() {
		Config config = new Config();
		JoinConfig joinConfig = config.getNetworkConfig().getJoin();
		joinConfig.getMulticastConfig().setEnabled(false);
		List<String> ipList=new ArrayList<String>();
		ipList.add("172.18.0.5");
		ipList.add("172.18.0.6");
		joinConfig.getTcpIpConfig().setEnabled(true).setMembers(ipList);
		return config;
	}

}