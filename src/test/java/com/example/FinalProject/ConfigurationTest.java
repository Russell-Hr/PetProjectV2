package com.example.FinalProject;


import com.example.FinalProject.command.configuration.HibernateConfiguration;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.persistence.metamodel.EntityType;
import java.util.Set;

@SpringJUnitConfig(HibernateConfiguration.class)
public class ConfigurationTest {

    @Autowired
    public SessionFactory sessionFactory;

    @Test
    public void testConfigurationWorks() {
        Set<EntityType<?>> entities = sessionFactory.getMetamodel().getEntities();
        for (EntityType<?> entity : entities) {
            System.out.println(entity.getName());
        }
    }
}
