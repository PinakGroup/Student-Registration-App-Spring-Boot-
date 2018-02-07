package edu.mum;



import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.mum.domain.Entry;
import edu.mum.repository.EntryRepository;

@RunWith(SpringRunner.class) // is used to provide a bridge between Spring Boot test features and JUnit
@SpringBootTest
@DataJpaTest
public class MumSchedProjectApplicationTests {
/*
	The spring-boot-starter-test is the primary dependency 
	that contains the majority of elements required for our tests.

	The H2 DB is our in-memory database. It eliminates the need for 
	configuring and starting an actual database for test purposes.
*/	
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private EntryRepository entryRepository;
	
    @Test
	public void contextLoads() {
	}
    
    @Test
    public void whenFindByName_thenReturnEntry() {
        
    	// given
        Entry oct = new Entry("October");
        entityManager.persist(oct);
        entityManager.flush();
        
     // when
        Entry found = entryRepository.findByEntryMonth(oct.getEntryMonth());
  
     // then
        assertThat(found.getEntryMonth())
          .isEqualTo(oct.getEntryMonth());   
    }     
        
}
    
    

/*
@DataJpaTest provides some standard setup needed for testing the persistence layer:

configuring H2, an in-memory database
setting Hibernate, Spring Data, and the DataSource
performing an @EntityScan
turning on SQL logging
*/