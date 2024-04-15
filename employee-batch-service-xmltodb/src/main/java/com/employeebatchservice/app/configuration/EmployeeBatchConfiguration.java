package com.employeebatchservice.app.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import com.employeebatchservice.app.entity.Employee;
import com.employeebatchservice.app.repository.EmployeeRepository;
import java.io.File;

@Configuration
@EnableBatchProcessing
public class EmployeeBatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private EmployeeRepository employeeRepository;
  
    @Bean
    public StaxEventItemReader<Employee> itemReader() {
        StaxEventItemReader<Employee> xmlFileReader = new StaxEventItemReader<>();
        xmlFileReader.setResource(new FileSystemResource(new File("src/main/resources/EmployeeData.xml")));
        xmlFileReader.setFragmentRootElementName("employee");
        xmlFileReader.setUnmarshaller(employeeUnmarshaller());
        return xmlFileReader;
    }
    
    @Bean
    public Jaxb2Marshaller employeeUnmarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Employee.class);
        return marshaller;
    }

    @Bean
    public EmployeeProcessor processor() {
        return new EmployeeProcessor();
    }

    @Bean
    public RepositoryItemWriter<Employee> writer() {
        RepositoryItemWriter<Employee> writer = new RepositoryItemWriter<>();
        writer.setRepository(employeeRepository);
        writer.setMethodName("save");
        return writer;
    }


    @Bean
    public Step step1(StaxEventItemReader<Employee> itemReader) {
        return stepBuilderFactory.get("step1").<Employee, Employee>chunk(10)
                .reader(itemReader)
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job runJob(StaxEventItemReader<Employee> itemReader) {
        return jobBuilderFactory.get("import-employees")
        						.flow(step1(itemReader))
        						.end()
        						.build();
    }
}
