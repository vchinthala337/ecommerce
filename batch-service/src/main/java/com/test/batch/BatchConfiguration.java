package com.test.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.test.dto.OrderDTO;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public FlatFileItemReader<OrderDTO> reader() {
		return new FlatFileItemReaderBuilder<OrderDTO>().name("OrderReader").resource(new ClassPathResource("data.csv"))
				.delimited()
				.names(new String[] { "orderStatus", "customerId", "orderItemName", "orderItemQty", "orderSubtotal",
						"orderTax", "orderShippingCharges", "orderPaymentMethod", "orderPaymentDate",
						"orderPaymentConfirmationNumber", "orderBillingAddressline1", "orderBillingAddressline2",
						"orderBillingCity", "orderBillingState", "orderBillingZip" })
				.fieldSetMapper(new BeanWrapperFieldSetMapper<OrderDTO>() {
					{
						setTargetType(OrderDTO.class);
					}
				}).build();
	}

	@Bean
	public OrderItemProcessor processor() {
		return new OrderItemProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<OrderDTO> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<OrderDTO>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO people (order_status, order_customer_id, order_item_name, order_item_qty, order_subtotal, order_tax, order_shipping_charges, order_total, order_payment_method, order_payment_date, order_payment_confirmation_number, order_billing_addressline1, order_billing_addressline2, order_billing_city, order_billing_state, order_billing_zip) VALUES "
						+ "(:orderStatus, :customerId, :orderItemName, :orderItemQty, :orderSubtotal, :orderTax, :orderShippingCharges, :orderTotal, :orderPaymentMethod, :orderPaymentDate, :orderPaymentConfirmationNumber, :orderBillingAddressline1, :orderBillingAddressline2, :orderBillingCity, :orderBillingState, :orderBillingZip)")
				.dataSource(dataSource).build();
	}
	
	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
	  return jobBuilderFactory.get("importorderJob")
	    .incrementer(new RunIdIncrementer())
	    .listener(listener)
	    .flow(step1)
	    .end()
	    .build();
	}

	@Bean
	public Step step1(JdbcBatchItemWriter<OrderDTO> writer) {
	  return stepBuilderFactory.get("step1")
	    .<OrderDTO, OrderDTO> chunk(10)
	    .reader(reader())
	    .processor(processor())
	    .writer(writer)
	    .build();
	}
	
}