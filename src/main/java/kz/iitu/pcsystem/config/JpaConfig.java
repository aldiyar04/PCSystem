package kz.iitu.pcsystem.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class JpaConfig {
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }

    @Bean(name="entityManagerFactory")
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setPackagesToScan("kz.iitu.pcsystem.entity");
//        factoryBean.setJpaDialect(isolationSupportJpaDialect());
        factoryBean.afterPropertiesSet();
        return factoryBean.getNativeEntityManagerFactory();
    }

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSource = DataSourceBuilder.create();
        dataSource.driverClassName("org.postgresql.Driver");
        dataSource.url("jdbc:postgresql://localhost:5432/pcsystem");
        dataSource.username("postgres");
        dataSource.password("pass");
        return dataSource.build();
    }

    private Properties hibernateProperties() {
        Properties props = new Properties();

        props.put("hibernate.hbm2ddl.auto", "create");

        // Caching
//        props.put("hibernate.cache.use_second_level_cache", true);
//        props.put("hibernate.cache.use_query_cache", true);
//        props.put("hibernate.cache.region.factory_class", "org.hibernate.cache.jcache.JCacheRegionFactory");

        // Fetching
        props.put("hibernate.jdbc.batch_size", 10);
        props.put("hibernate.jdbc.fetch_size", 50);

        // Show SQL
        props.put("hibernate.format_sql", true);
        props.put("hibernate.use_sql_comments", true);
        props.put("hibernate.show_sql", true);

        return props;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }
}
