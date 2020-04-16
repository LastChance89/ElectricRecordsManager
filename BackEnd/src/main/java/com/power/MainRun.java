package com.power;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import com.power.Controller.PowerController;  

// Might not need the component itself. 


//Switch back to @Component if needded. 
@SpringBootApplication
/*
 * We do not need the Component scan when we use the @SpringBoot APplication tag, this makes it a true spring application 
 * VS the faked component that it was. 	
 * @ComponentScan("main.services")//Need to move the component scan into the XML when transition to web application. 
*/
public class MainRun //implements CommandLineRunner
{
	

	/*
	@Autowired
	private PowerController pc;
	
	public static void main(String [] args){ 
		//Move this into the XML???
	//	ApplicationContext ctx=new AnnotationConfigApplicationContext(MainRun.class);
		SpringApplication application = new SpringApplication(MainRun.class);
		   
		System.out.println("Starting spring application");
		
		application.run(args);
		
		System.out.println("Complete! ");
	}
	
	@Override
	public void run(String...args){
		pc.run();
	}
*/



}
