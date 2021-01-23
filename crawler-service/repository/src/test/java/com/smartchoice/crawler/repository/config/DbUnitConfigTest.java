package com.smartchoice.crawler.repository.config;

import com.smartchoice.crawler.repository.ProductRepository;
import com.smartchoice.crawler.repository.dataset.ProductData;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContextTest.class})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
public class DbUnitConfigTest {

    @Autowired
    protected ProductRepository productRepository;

    /** setup TEST data before running. */
    @Before
    public void setUpDataTests() {
        ProductData productData = new ProductData();
        productData.create(productRepository);
    }

    /** clean TEST data after running completely. */
    @After
    public void cleanUpDataTests() {
        productRepository.deleteAll();
    }

    @Test
    public void testSetUpDataTests() {
        final long count = productRepository.count();
    Assert.assertTrue(count > 0);
    }
}
