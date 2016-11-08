package com.ServiceController;

//import com.Lire_tokenizer.TokenizerTrainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class ServiceApplication {

    public static void main(String[] args) {
            SpringApplication.run(ServiceApplication.class, args);
            System.out.println("Lire Services started!");
//            TokenizerTrainer trainer = new TokenizerTrainer(
//                    "src\\main\\java\\com\\Lire_tokenizer\\TrainingSets\\NewsPaperTrained-si", 
//                    "trainingSetSI_Refined.txt");
    }
        
        
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //allowing localhost to access services (WAMP)
                registry.addMapping("/**");
//                registry.addMapping("/prosody/changeprosody").allowedOrigins("http://localhost");
//                registry.addMapping("/prosody/changetiming").allowedOrigins("http://localhost");
//                registry.addMapping("/tokenize/sentenceTree").allowedOrigins("http://localhost");
//                registry.addMapping("/tokenize/directTokenize").allowedOrigins("http://localhost");
//                registry.addMapping("/download").allowedOrigins("http://localhost");
//                registry.addMapping("/login").allowedOrigins("http://localhost");
//                registry.addMapping("/getuidphp").allowedOrigins("http://localhost");
                
                //allowing localhost:8088 to access serices
//                registry.addMapping("/prosody/changeprosody").allowedOrigins("http://localhost:8088");
//                registry.addMapping("/prosody/changetiming").allowedOrigins("http://localhost:8088");
//                registry.addMapping("/tokenize/sentenceTree").allowedOrigins("http://localhost:8088");
//                registry.addMapping("/tokenize/directTokenize").allowedOrigins("http://localhost:8088");
//                registry.addMapping("/download").allowedOrigins("http://localhost:8088");
            }
        };
    }   
}
