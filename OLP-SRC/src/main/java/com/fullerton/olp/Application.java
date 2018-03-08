package com.fullerton.olp;

import javax.persistence.EntityListeners;
import javax.xml.transform.Result;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.xml.transform.StringResult;

import com.fullerton.olp.repository.GenericDaoImpl;
import com.fullerton.olp.wsdao.bre.model.InvokeBRE;
import com.fullerton.olp.wsdao.bre.model.ObjectFactory;

/**
 * Application entry point
 */
@SpringBootApplication
//@EnableSwagger2
@EnableTransactionManagement
@EnableCaching
@EnableScheduling
@EnableAsync
//@EnableAspectJAutoProxy(proxyTargetClass = true)
//@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
@EnableJpaRepositories(basePackages = {"com.fullerton.olp.repository"},
        repositoryBaseClass = GenericDaoImpl.class
)
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
//@EnableJpaRepositories(basePackages = {"com.fullerton.olp.repository"},
//	repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class
//)
@EnableRetry
public class Application extends SpringBootServletInitializer {

    /**
     * Entry point for the application.
     *
     * @param args Command line arguments.
     * @throws Exception Thrown when an unexpected Exception is thrown from the
     *         application.
     */
    public static void main(String[] args) throws Exception {
    		System.setProperty("jsse.enableSNIExtension", "false");
        SpringApplication.run(Application.class, args);
        
        
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(Application.class);
//    }

    /**
     * Create a CacheManager implementation class to be used by Spring where
     * <code>@Cacheable</code> annotations are applied.
     *
     * @return A CacheManager instance.
     */
    @Bean
    public CacheManager cacheManager() {

        GuavaCacheManager cacheManager = new GuavaCacheManager("fullerton-olp");

        return cacheManager;
    }
     
    
    /**
     * Supports FileUploads.
     */

     @Bean
     public MultipartResolver multipartResolver() {
         return new StandardServletMultipartResolver();
     }
}


