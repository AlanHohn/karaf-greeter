package org.anvard.karaf.greeter.french;

import org.anvard.karaf.greeter.api.Greeter;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(immediate = true)
@Service
@Property(name="language", value="fr")
public class FrenchGreeter implements Greeter {

    private static final Logger LOGGER = LoggerFactory.getLogger(FrenchGreeter.class);

	@Override
	public String greet() {
		LOGGER.info("Le 'greeter' en francais!");
		return "Bonjour tout le monde!";
	}

}
