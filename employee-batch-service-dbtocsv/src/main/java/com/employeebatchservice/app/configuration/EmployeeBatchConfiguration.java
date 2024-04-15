package com.employeebatchservice.app.configuration;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import com.employeebatchservice.app.entity.Employee;

@Configuration
@EnableBatchProcessing
public class EmployeeBatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcCursorItemReader<Employee> reader() {
        JdbcCursorItemReader<Employee> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql("SELECT id, name, salary, age FROM employee");
        reader.setRowMapper(new BeanPropertyRowMapper<>(Employee.class));
        return reader;
    }

    @Bean
	public EmployeeProcessor processor() {
		return new EmployeeProcessor();
	}

    @Bean
    public FlatFileItemWriter<Employee> writer() {
        FlatFileItemWriter<Employee> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("src/main/resources/employees.csv"));
        
        writer.setHeaderCallback(writers -> writers.write("Id, Name, Salary, Age"));
        
        writer.setLineAggregator(new DelimitedLineAggregator<Employee>() {{
            setDelimiter(",");
            setFieldExtractor(new BeanWrapperFieldExtractor<Employee>() {{
                setNames(new String[]{"id", "name", "salary", "age"});
            }});
        }});
        return writer;
    }

  
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Employee, Employee>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job exportEmployeeJob() {
        return jobBuilderFactory.get("export-employees")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();
    }
}
