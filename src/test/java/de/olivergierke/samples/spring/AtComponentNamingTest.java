package de.olivergierke.samples.spring;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;


/**
 * @author Oliver Gierke
 */
public class AtComponentNamingTest {

    @Test
    public void explictlyDefinedBeanTrumpsScannedIfNameMatches() throws Exception {

        DefaultListableBeanFactory factory =
                new XmlBeanFactory(
                        new ClassPathResource("component-naming.xml"));

        String[] beans = factory.getBeanNamesForType(FooBar.class);
        assertThat(beans.length, is(1));

        BeanDefinition definition = factory.getBeanDefinition(beans[0]);
        assertThat(definition.getPropertyValues().getPropertyValue("foo"),
                is(notNullValue()));
    }
}
