package com.itmuch.cloud.study.config;

import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class config {
    /************************** atomikos 多数据源配置 ***************************/

/*------- db1 -------*/

    /**
     * db1的 XA datasource
     *
     * @return
     */
    @Bean
    @Primary
    @Qualifier("db1")
    public AtomikosDataSourceBean db1DataSourceBean() {
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setUniqueResourceName("db1");
        atomikosDataSourceBean.setXaDataSourceClassName(
                "com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
        Properties properties = new Properties();
        properties.put("URL", "jdbc:mysql://47.98.191.46:3306/demo");
        properties.put("user", "root");
        properties.put("password", "123456");
        atomikosDataSourceBean.setXaProperties(properties);
        return atomikosDataSourceBean;
    }

    /**
     * 构造db1 sessionFactory
     *
     * @return
     */
    @Bean
    @Autowired
    public AnnotationSessionFactoryBean sessionFactory(@Qualifier("db1") AtomikosDataSourceBean atomikosDataSourceBean) {
        AnnotationSessionFactoryBean sessionFactory = new AnnotationSessionFactoryBean();
        sessionFactory.setDataSource(atomikosDataSourceBean);
        sessionFactory.setPackagesToScan("com.itmuch.cloud.study.entity.first");
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
        properties.put("hibernate.show_sql", "false");
        properties.put("hibernate.format_sql", "format");
        properties.put("hibernate.connection.autocommit", "true");
        properties.put("hibernate.connection.url", atomikosDataSourceBean.getXaProperties().get("URL"));
        properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        sessionFactory.setHibernateProperties(properties);
        return sessionFactory;
    }

/*------- db2 -------*/

    /**
     * db2的 XA datasource
     *
     * @return
     */
    @Bean
    @Qualifier("db2")
    public AtomikosDataSourceBean db2DataSourceBean() {
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setUniqueResourceName("db2");
        atomikosDataSourceBean.setXaDataSourceClassName(
                "com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
        Properties properties = new Properties();
        properties.put("URL", "jdbc:mysql://47.98.191.46:3306/demo1");
        properties.put("user", "root");
        properties.put("password", "123456");
        atomikosDataSourceBean.setXaProperties(properties);
        return atomikosDataSourceBean;
    }

    /**
     * 构造db2 sessionFactory
     *
     * @return
     */
    @Bean
    @Autowired
    public AnnotationSessionFactoryBean db2SessionFactory(
            @Qualifier("db2") AtomikosDataSourceBean atomikosDataSourceBean) {
        AnnotationSessionFactoryBean sessionFactory = new AnnotationSessionFactoryBean();
        sessionFactory.setDataSource(atomikosDataSourceBean);
        sessionFactory.setPackagesToScan("com.itmuch.cloud.study.entity.second");
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
        properties.put("hibernate.show_sql", "false");
        properties.put("hibernate.format_sql", "format");
        properties.put("hibernate.connection.autocommit", "true");
        properties.put("hibernate.connection.url", atomikosDataSourceBean.getXaProperties().get("URL"));
        properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        sessionFactory.setHibernateProperties(properties);
        return sessionFactory;
    }

/*--------- atomikos -----------*/

    /**
     * transaction manager
     *
     * @return
     */
    @Bean(destroyMethod = "close", initMethod = "init")
    public UserTransactionManager userTransactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);
        return userTransactionManager;
    }

    /**
     * jta transactionManager
     *
     * @return
     */
    @Bean
    public JtaTransactionManager transactionManager() {
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setTransactionManager(userTransactionManager());
        return jtaTransactionManager;
    }

}
