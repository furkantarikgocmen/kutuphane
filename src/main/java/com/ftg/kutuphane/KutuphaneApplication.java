package com.ftg.kutuphane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KutuphaneApplication {

	public static void main(String[] args) {
		SpringApplication.run(KutuphaneApplication.class, args);
	}

	//Thymleaf Development Properties
//    @Autowired
//    private ThymeleafProperties properties;
//
//    @Value("${spring.thymeleaf.templates_root:}")
//    private String templatesRoot;

//    @Bean
//    public ITemplateResolver defaultTemplateResolver() {
//        FileTemplateResolver resolver = new FileTemplateResolver();
//        resolver.setSuffix(properties.getSuffix());
//        resolver.setPrefix(templatesRoot);
//        resolver.setTemplateMode(properties.getMode());
//        resolver.setCacheable(properties.isCache());
//        return resolver;
//    }
}
