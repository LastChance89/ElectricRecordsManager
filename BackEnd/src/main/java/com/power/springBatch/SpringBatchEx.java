package com.power.springBatch;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.support.DatabaseType;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import com.power.models.Record;

@Configuration
@EnableBatchProcessing

public class SpringBatchEx {

	/*
	 * @Autowired private JobBuilderFactory jobBuilderFactory;
	 * 
	 * @Autowired private StepBuilderFactory stepBuilderFactory;
	 * 
	 * //fix me JndiDataSourceLookup jndiLookup = new JndiDataSourceLookup();
	 * DataSource ds = jndiLookup.getDataSource("java:/mysql/power");
	 * 
	 * private final static String csvLocation =
	 * "C:\\Users\\ksmit\\workspace\\java\\messingAround\\src\\main\\resources\\input_elect.csv";
	 * 
	 * //@Todo:Make Dynamic.
	 * 
	 * @Value(csvLocation) private Resource inputResource;
	 * 
	 * @Bean (name = "readFile") public Job readInputFile(){ return
	 * jobBuilderFactory.get("readInputFile") .incrementer(new RunIdIncrementer())
	 * .start(step()) .build(); }
	 * 
	 * @Bean public Step step(){ return stepBuilderFactory.get("step")
	 * .<Record,Record> chunk(1000000) .reader(reader()) .processor(processor())
	 * .writer(writer()) .build(); }
	 * 
	 * @Bean public FlatFileItemReader<Record> reader(){ FlatFileItemReader<Record>
	 * recordReader = new FlatFileItemReader<Record>();
	 * recordReader.setLineMapper(lineMapper()); recordReader.setLinesToSkip(0);
	 * //recordReader.setResource(inputResource); recordReader.setResource(new
	 * FileSystemResource(csvLocation)); return recordReader; }
	 * 
	 * @Bean public LineMapper<Record> lineMapper(){ DefaultLineMapper<Record>
	 * lineMapper = new DefaultLineMapper<Record>(); DelimitedLineTokenizer
	 * tokenizer = new DelimitedLineTokenizer();
	 * tokenizer.setNames("account_number","type","record_date","start_time",
	 * "end_time" ,"power_usage","units","cost","note");
	 * 
	 * //tokenizer.setNames("account_number"); //tokenizer.setIncludedFields(new
	 * int[] {0}); tokenizer.setNames("type","record_date","start_time","end_time"
	 * ,"power_usage","units","cost","note"); //If we want to skip a column we can
	 * tell it to only read in specific values, //IE: Read in columns 1, 5 6 etc.
	 * tokenizer.setIncludedFields(new int[] {0,1,2,3,4,5,6,7});
	 * BeanWrapperFieldSetMapper<Record> recordFieldMapper = new
	 * BeanWrapperFieldSetMapper<Record>();
	 * recordFieldMapper.setTargetType(Record.class);
	 * lineMapper.setLineTokenizer(tokenizer);
	 * lineMapper.setFieldSetMapper(recordFieldMapper); return lineMapper;
	 * 
	 * }
	 * 
	 * @Bean public JdbcBatchItemWriter<Record> writer(){
	 * JdbcBatchItemWriter<Record> recordWriter = new JdbcBatchItemWriter<Record>();
	 * recordWriter.setDataSource(ds);
	 * //recordWriter.setSql("INSERT INTO TSTB VALUES (:account_number)");
	 * recordWriter.
	 * setSql("INSERT INTO POWER_INFO VALUES (:account_number,:type,:record_date," +
	 * ":start_time,:end_time,:power_usage,:units,:cost,:note)");
	 * recordWriter.setItemSqlParameterSourceProvider(new
	 * BeanPropertyItemSqlParameterSourceProvider<Record>()); return recordWriter; }
	 * 
	 * @Bean public ItemProcessor<Record, Record> processor(){ //return new
	 * RecordProcessor(); return null; }
	 * 
	 * @Bean public JobRepository getJobRepository() throws Exception{
	 * JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean(); //Why is
	 * this necessary? factory.setDataSource(buildDataSource());
	 * factory.setDatabaseType(DatabaseType.MYSQL.name()); //
	 * MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean();
	 * factory.setTransactionManager(new ResourcelessTransactionManager());
	 * factory.afterPropertiesSet(); return factory.getObject(); }
	 * 
	 * //@Todo: Figureout why the normal JNDI does not work.
	 * 
	 * @Bean public BasicDataSource buildDataSource(){ BasicDataSource newDS = new
	 * BasicDataSource();
	 * 
	 * newDS.setDriverClassName("com.mysql.jdbc.Driver");
	 * newDS.setUrl("jdbc:mysql://DESKTOP-KVGV4PO:3306/test");
	 * newDS.setUsername("ksmitw"); newDS.setPassword("password"); return newDS; }
	 * 
	 * 
	 * @Bean(name = "jobLauncher") public JobLauncher getJobLauncher() throws
	 * Exception { SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
	 * jobLauncher.setJobRepository(getJobRepository());
	 * jobLauncher.afterPropertiesSet(); return jobLauncher;
	 * 
	 * }
	 */

}
