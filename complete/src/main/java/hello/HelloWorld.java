package hello;

import java.io.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
public class HelloWorld implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(HelloWorld.class);

	@Autowired
	private TbApiKeyRepository tbApiKeyRepository;

	public static String getKey() throws Exception {

		System.out.print(new Date());
		Process process2 = Runtime.getRuntime().exec("/root/test/api-key-gen/genKey.sh");
		String keyStr = printResults(process2);
		log.info("keyStr = " + keyStr);
		return keyStr;
        }

	public static String printResults(Process process) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line = "";
		String keyStr = "";
		while ((line = reader.readLine()) != null) {
			if(line.indexOf("=>") >= 0) {
				keyStr = line.substring(line.indexOf("=>") + 2).trim();
			}
		}
		return keyStr;
        }

	private void loadApiKey() {

		log.info("loadApiKey() start...");

		String keyStr = "";

		try {
			keyStr = getKey();
		} catch(Exception e) {
		}

		TbApiKey n = new TbApiKey();
		n.setSeq(0);
		n.setApiKey(keyStr);
		tbApiKeyRepository.save(n);

		log.info("loadApiKey() end...");

	}

	private void etlApiKey() throws Exception {

		log.info("etlApiKey() start...");

		for(long i = 0; i < 100000000000L; i++) {
			loadApiKey();
			Thread.sleep(10000);
		}

		log.info("etlApiKey() end...");
	}

	public static void main(String[] args) {
		log.info(">>>>>>>>>>>>>>>>>>>");
		for(String arg:args) {
			log.info(">>>" + arg);
		}
		SpringApplication.run(HelloWorld.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		log.info("run() start...");

		for(String arg:args) {
                        log.info("!!!>>>" + arg);
                }

		String work = args[0];

		switch (work) {
			case "key" :
				etlApiKey();
				break;
		}

		log.info("run() end...");

	}
}
