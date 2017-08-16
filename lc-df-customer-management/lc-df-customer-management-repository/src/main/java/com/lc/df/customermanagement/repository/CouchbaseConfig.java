/**
 * 
 */
package com.lc.df.customermanagement.repository;

import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

/**
 * @author mallrao
 *
 */
//@Configuration
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {
	
		@Override
	    protected List<String> getBootstrapHosts() {
	        return Collections.singletonList("127.0.0.1");
	    }

	    @Override
	    protected String getBucketName() {
	        return "Customer";
	    }

	    @Override
	    protected String getBucketPassword() {
	        return "";
	    }

}
