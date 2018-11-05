package com.xzst.relation.mp.baseConfig;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by LHT on 2018/7/6.
 */
//@Configuration
public class HiKariCPconfig {

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String userName;
	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.connection-test-query}")
	private String connection_test_query;
	@Value("${spring.datasource.poolName}")
	private String poolName;
	@Value("${spring.datasource.hikari.autoCommit}")
	private boolean auto_commit;

	@Value("${spring.datasource.hikari.connection-timeout}")
	private int hikari_connection_timeout;

	@Value("${spring.datasource.hikari.idle-timeout}")
	private int hikari_idle_timeout;
	@Value("${spring.datasource.hikari.max-lifetime}")
	private int hikari_max_lifetime;
	@Value("${spring.datasource.hikari.maximum-pool-size}")
	private int hikari_maximum_pool_size;



	@Bean(destroyMethod = "close")
	public DataSource dataSource(){
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(driverClassName);
		hikariConfig.setJdbcUrl(url);
		hikariConfig.setUsername(userName);
		hikariConfig.setPassword(password);



		hikariConfig.setConnectionTestQuery(connection_test_query);
		hikariConfig.setPoolName(poolName);
		hikariConfig.setMaximumPoolSize(hikari_maximum_pool_size);
		hikariConfig.setConnectionTimeout(hikari_connection_timeout);
		hikariConfig.setAutoCommit(auto_commit);
		hikariConfig.setMaxLifetime(hikari_max_lifetime);
		hikariConfig.setIdleTimeout(hikari_idle_timeout);


		hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
		hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");

		HikariDataSource dataSource = new HikariDataSource(hikariConfig);

		return dataSource;
	}


}
