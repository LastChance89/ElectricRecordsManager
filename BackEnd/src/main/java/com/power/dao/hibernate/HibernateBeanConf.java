package com.power.dao.hibernate;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.power.models.Client;
import com.power.models.Record;
import com.sec.model.User;




@Configuration
@EnableTransactionManagement
public class HibernateBeanConf {
	
	@Autowired
	private DataSource dataSource;
	
    @Autowired
    private ApplicationContext context;
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean(); 
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setConfigLocation(context.getResource("classpath:hibernate.cfg.xml"));
		sessionFactory.setAnnotatedClasses(Client.class,Record.class,User.class); 
		//sessionFactory.setPackagesToScan("com.power.models"); 
		return sessionFactory;
	}
	
	@Bean 
	public PlatformTransactionManager hibernateTransactionMgr() {
		HibernateTransactionManager transactionMgr = new HibernateTransactionManager();
		transactionMgr.setSessionFactory(sessionFactory().getObject());
		return transactionMgr;
	}

	
}
