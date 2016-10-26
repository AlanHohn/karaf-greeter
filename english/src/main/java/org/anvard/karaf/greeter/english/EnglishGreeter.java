package org.anvard.karaf.greeter.english;

import org.anvard.karaf.greeter.api.Greeter;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(immediate = true)
@Service
@Property(name="language", value="en-us")
public class EnglishGreeter implements Greeter {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnglishGreeter.class);

	@Override
	public String greet() {
		LOGGER.info("English greeter is making a greeting");
		return "Hello, world!";
	}

}
