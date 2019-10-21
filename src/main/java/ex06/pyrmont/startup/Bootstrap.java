package ex06.pyrmont.startup;

import ex06.pyrmont.core.SimpleContext;
import ex06.pyrmont.core.SimpleContextLifecycleListener;
import ex06.pyrmont.core.SimpleContextMapper;
import ex06.pyrmont.core.SimpleLoader;
import ex06.pyrmont.core.SimpleWrapper;
import org.apache.catalina.Connector;
import org.apache.catalina.Context;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Loader;
import org.apache.catalina.Mapper;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.http.HttpConnector;

public final class Bootstrap {
	public static void main(String[] args) {
		Wrapper wrapper1 = getWrapper("Primitive", "PrimitiveServlet");
		Wrapper wrapper2 = getWrapper("Modern", "ModernServlet");

		Context context = new SimpleContext();
		context.addChild(wrapper1);
		context.addChild(wrapper2);

		context.addMapper(getMapper());
		((Lifecycle) context).addLifecycleListener(getListener());
		context.setLoader(getLoader());

		context.addServletMapping("/Primitive", "Primitive");
		context.addServletMapping("/Modern", "Modern");

		Connector connector = new HttpConnector();
		connector.setContainer(context);

		try {
			connector.initialize();
			((Lifecycle) connector).start();
			((Lifecycle) context).start();

			// make the application wait until we press a key.
			System.in.read();
			((Lifecycle) context).stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Loader getLoader() {
		return new SimpleLoader();
	}

	private static Wrapper getWrapper(String name, String servletClass) {
		Wrapper wrapper = new SimpleWrapper();
		wrapper.setName(name);
		wrapper.setServletClass(servletClass);
		return wrapper;
	}

	private static Mapper getMapper() {
		Mapper mapper = new SimpleContextMapper();
		mapper.setProtocol("http");
		return mapper;
	}

	private static LifecycleListener getListener() {
		return new SimpleContextLifecycleListener();
	}
}