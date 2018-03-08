/*
 * 修 改 人:  zhang_dy
 * 修改时间:  2018年1月19日 下午6:48:45
 */
package io.sunny.config;

import io.sunny.common.utils.http.SyncHttpClient;

import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.IdleConnectionEvictor;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

import javax.servlet.MultipartConfigElement;

@Configuration
public class HttpConfig {
	@Value("${http.max-total}")
	private int maxTotal;
	@Value("${http.default-max-per-route}")
	private int defaultMaxPerRoute;
	@Value("${http.max-idle-time}")
	private long maxIdleTime;

	@Bean
	public HttpClientConnectionManager httpConnectionManager() {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(maxTotal);
		cm.setDefaultMaxPerRoute(defaultMaxPerRoute);
		return cm;
	}

	@Bean(destroyMethod = "shutdown")
	public IdleConnectionEvictor idleConnectionEvictor() {
		IdleConnectionEvictor evictor = new IdleConnectionEvictor(httpConnectionManager(), maxIdleTime,
				TimeUnit.MILLISECONDS);
		evictor.start();
		return evictor;
	}

	@Bean
	public HttpClientBuilder httpClientBuilder() {
		return HttpClients.custom().setConnectionManager(httpConnectionManager());
	}

	@Bean
	public SyncHttpClient syncHttpClient() {
		return new SyncHttpClient(httpClientBuilder().build());
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("10MB");
		factory.setMaxRequestSize("10MB");
		return factory.createMultipartConfig();
	}

}
