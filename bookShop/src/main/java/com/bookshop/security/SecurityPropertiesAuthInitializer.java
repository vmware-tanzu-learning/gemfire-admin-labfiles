package com.bookshop.security;

import java.util.Properties;

import org.apache.geode.LogWriter;
import org.apache.geode.distributed.DistributedMember;
import org.apache.geode.security.AuthInitialize;
import org.apache.geode.security.AuthenticationFailedException;
/**
 * 
 * A basic AuthInitialize implementation that takes arguments from the Java start and passes them through
 * to the GemFire specific properties <code>security-username</code> and <code>security-password</code>.
 * This initializer expects the appropriate programs to be executed as follows:
 * <br><br>
 * <code>
 * java -Dusername=someuser -Dpassword=somepassword [class to execute]
 * </code>
 * <br><br>
 * This class is expected to be configured in the gemfire.properties file as:<br>
 * <code>security-client-auth-init=com.bookshop.security.SecurityPropertiesAuthInitializer</code>
 * 
 * @author msecrist
 * 
 */
public class SecurityPropertiesAuthInitializer implements AuthInitialize {

	private String envUsername;
	private String envPassword;
	
	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(LogWriter systemLogger, LogWriter securityLogger) throws AuthenticationFailedException {
		envUsername = System.getProperty("username");
		envPassword = System.getProperty("password");

	}

	@Override
	public Properties getCredentials(Properties securityProps, DistributedMember server, boolean isPeer)
			throws AuthenticationFailedException {
		Properties props = new Properties();
		props.put("security-username", envUsername);
		props.put("security-password", envPassword);
	    return props;
	}

}
