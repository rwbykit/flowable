package rwbykit.flowable.war3.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.util.Map;

/**
 * @description 缓存CacheNames配置
 * @author crackLu
 * @date 2018年12月28日
 * @version 0.0.1.SNAPSHOT
 */
@ConfigurationProperties(prefix = "cache")
public class CacheNamesProperties {
	private Map<String, RedisCacheConfiguration> cacheNames;

	public Map<String, RedisCacheConfiguration> getCacheNames() {
		return cacheNames;
	}

	public void setCacheNames(Map<String, RedisCacheConfiguration> cacheNames) {
		this.cacheNames = cacheNames;
	}

}
