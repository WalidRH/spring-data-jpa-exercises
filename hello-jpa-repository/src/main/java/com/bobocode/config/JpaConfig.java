package com.bobocode.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

/**
 * This class provides spring configuration for {@link javax.persistence.EntityManagerFactory} bean.
 * <p>
 * todo: 1. PLEASE NOTE, THAT SOME REQUIRED STEPS ARE OMITTED IN THE TODO LIST AND YOU HAVE TO DO IT ON YOUR OWN
 * <p>
 * todo: 2. Configure {@link DataSource} bean
 * todo: 3. Configure {@link JpaVendorAdapter} bean
 * todo: 3. Configure {@link javax.persistence.EntityManagerFactory} bean with name "entityManagerFactory"
 * todo: 4. Enable JPA repository, set appropriate package using annotation property "basePackages"
 */
@Configuration

/**
 * @EnableJpaRepostories annotation enables JPA repositories. It will scan the package of the annotated configuration class for Spring Data repositories by default.
 *
 *  If we had a main class and a @SpringBootApplication annotation in it and all the Jpa repositories are in the sub packages of the main package
 *  then we don't need to add @EnableJpaRepostories [ for more details check: https://springbootdev.com/2017/11/13/what-are-the-uses-of-entityscan-and-enablejparepositories-annotations/ ]
 */
@EnableJpaRepositories(basePackages = "com.bobocode.dao")
public class JpaConfig {
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.H2);
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        return vendorAdapter;

    }
    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localContainerEMF(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        // todo: create and configure required bean
        // todo: set package "com.bobocode.model" to scan for JPA entities
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setJpaVendorAdapter(jpaVendorAdapter);
        throw new UnsupportedOperationException("Application won't start until you provide configs");
    }
}
