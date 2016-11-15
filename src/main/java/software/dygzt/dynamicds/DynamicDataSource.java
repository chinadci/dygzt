package software.dygzt.dynamicds;

import software.dygzt.dynamicds.CustomerContextHolder;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author lb
 */
public class DynamicDataSource extends AbstractRoutingDataSource{
	@Override
	protected Object determineCurrentLookupKey() {
		return CustomerContextHolder.getCustomerType();
	}
}
