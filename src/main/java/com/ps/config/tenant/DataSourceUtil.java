package com.ps.config.tenant;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.jboss.logging.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.ps.entities.master.GroupCompanyMaster;



public final class DataSourceUtil {

	static Logger logger = Logger.getLogger(DataSourceUtil.class);
    
    public static DataSource createAndConfigureDataSource(
    		GroupCompanyMaster masterTenant) {
		
		try {
			if(logger.isDebugEnabled()) logger.debug("In createAndConfigureDataSource method creating datasource for "
					+ "tenant-> "+masterTenant.getGroupName());
			
			ComboPooledDataSource ds = new ComboPooledDataSource();
			ds.setUser(masterTenant.getUserName());
			ds.setPassword(masterTenant.getPassword());
			
			String serverAndInstanceName = masterTenant.getServerName();
			if (masterTenant.getInstanceName() != null && !masterTenant.getInstanceName().isBlank())
				serverAndInstanceName = masterTenant.getServerName()+"\\"+masterTenant.getInstanceName();
						
			ds.setJdbcUrl("jdbc:sqlserver://"+serverAndInstanceName+";databaseName=" + masterTenant.getDatabaseName());

			if(logger.isDebugEnabled()) logger.debug("Ds connection url-> "+ds.getJdbcUrl());

			ds.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			ds.setInitialPoolSize(20);

			String tenantId = masterTenant.getGroupName();
			String tenantConnectionPoolName = tenantId + "-connection-pool";
			logger.info("Configured datasource:" + masterTenant.getGroupName() + ". Connection poolname:"
					+ tenantConnectionPoolName);
			return ds;
		} catch (PropertyVetoException e) {
			logger.error("Exception occured while creating datasource ",e);
			e.printStackTrace();

		}
		
		logger.info("Returning null datasource for tenant "+masterTenant);
		  return null;
		 
    	
		/*
		 * HikariDataSource ds = new HikariDataSource(); ds.setUsername("sa");
		 * ds.setPassword("JungleBook@123");
		 * ds.setJdbcUrl("jdbc:sqlserver://PAYLAPTOP82;databaseName="+masterTenant.
		 * getMappedDatabaseName());
		 * ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		 * 
		 * // HikariCP settings - could come from the master_tenant table but //
		 * hardcoded here for brevity // Maximum waiting time for a connection from the
		 * pool ds.setConnectionTimeout(20000);
		 * 
		 * // Minimum number of idle connections in the pool ds.setMinimumIdle(10);
		 * 
		 * // Maximum number of actual connection in the pool ds.setMaximumPoolSize(20);
		 * 
		 * // Maximum time that a connection is allowed to sit idle in the pool
		 * ds.setIdleTimeout(300000); ds.setConnectionTimeout(20000);
		 * 
		 * // Setting up a pool name for each tenant datasource String tenantId =
		 * masterTenant.getMappedDatabaseName(); String tenantConnectionPoolName =
		 * tenantId + "-connection-pool"; ds.setPoolName(tenantConnectionPoolName);
		 */
		/*
		 * LOG.info("Configured datasource:" + masterTenant.getMappedDatabaseName() +
		 * ". Connection poolname:" + tenantConnectionPoolName); return ds;
		 */
    }
}
