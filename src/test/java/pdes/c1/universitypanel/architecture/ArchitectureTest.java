package pdes.c1.universitypanel.architecture;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

public class ArchitectureTest {

    @Test
    public void applicationControllersShouldResideInApplicationPackage() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("pdes.c1.universitypanel");
        
	    ArchRule rule = ArchRuleDefinition.classes().that().areAnnotatedWith(RestController.class)
	            .should().resideInAPackage("pdes.c1.universitypanel.controller");
    
	    rule.check(importedClasses);
    }

    @Test
    public void applicationServicesShouldResideInApplicationPackage() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("pdes.c1.universitypanel");
        
	    ArchRule rule = ArchRuleDefinition.classes().that().areAnnotatedWith(Service.class)
	            .should().resideInAPackage("pdes.c1.universitypanel.service");
    
	    rule.check(importedClasses);
    }

    @Test
    public void applicationRepositoriesShouldResideInApplicationPackage() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("pdes.c1.universitypanel");
        
	    ArchRule rule = ArchRuleDefinition.classes().that().areAnnotatedWith(Repository.class)
	            .should().resideInAPackage("pdes.c1.universitypanel.repositories");
    
	    rule.check(importedClasses);
    }
}
