
package com.ps.config.tenant;

import org.jboss.logging.Logger;

/**
 * When the end user submits the login form, the tenant id is required to
 * determine which database to connect to. This needs to be captured in the
 * spring security authentication mechanism, specifically in the
 * {@link UsernamePasswordAuthenticationFilter} implemented by
 * {@link CustomAuthenticationFilter}. This tenant id is then required by the
 * {@link CurrentTenantIdentifierResolver} implemeted by the
 * {@link CurrentTenantIdentifierResolverImpl}
 * 
 * <br/>
 * <br/>
 * <b>Explanation:</b> Thread Local can be considered as a scope of access, like
 * a request scope or session scope. It’s a thread scope. You can set any object
 * in Thread Local and this object will be global and local to the specific
 * thread which is accessing this object. Global and local at the same time? :
 * 
 * <ul>
 * <li>Values stored in Thread Local are global to the thread, meaning that they
 * can be accessed from anywhere inside that thread. If a thread calls methods
 * from several classes, then all the methods can see the Thread Local variable
 * set by other methods (because they are executing in same thread). The value
 * need not be passed explicitly. It’s like how you use global variables.</li>
 * <li>Values stored in Thread Local are local to the thread, meaning that each
 * thread will have it’s own Thread Local variable. One thread can not
 * access/modify other thread’s Thread Local variables.</li>
 * </ul>
 */
public class TenantContextHolder {

	static Logger logger = Logger.getLogger(TenantContextHolder.class);
	
    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public static void setTenantId(String tenant) {    	
    	if(logger.isDebugEnabled()) logger.debug("In TenantContextHolder setting current tenantId-> "+tenant);
        CONTEXT.set(tenant);
    }

    public static String getTenant() {
    	if(logger.isDebugEnabled()) logger.debug("In TenantContextHolder getTenant current tenant is-> "+CONTEXT.get());
        return CONTEXT.get();
    }

    public static void clear() {
    	if(logger.isDebugEnabled()) logger.debug("In TenantContextHolder clearing tenant info from context");
        CONTEXT.remove();
    }
}