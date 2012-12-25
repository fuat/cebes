package de.cesar.portal;

import org.eclipse.jetty.ajp.Ajp13SocketConnector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;


/**
 * Test jetty start class for development
 *
 * @author Fuat Atabey
 */
public class JettyStart {

    public static void main(final String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("JettyStart <httpport> [ajpport] [sslport]");
            return;
        }
        //Determine spring profiles via "cindy.environment" parameter
        System.setProperty("cindy.environment", "LOCAL");

        //WHEN YOU ACTIVATE THIS PROFILE YOU MUST NOT HAVE ANY DATASOURCE ACTIVATED
//        System.setProperty("cindy.environment", "INTEGRATIONTEST");

        //Set spring profiles directly (not using mechanism from above)
        //System.setProperty("spring.profiles.active", "common,local,override");

        System.setProperty("catalina.base", "");

        //Set production mode
//        System.setProperty("configuration.type", RuntimeConfigurationType.DEPLOYMENT.name());

        Server server = new Server(Integer.parseInt(args[0]));
        WebAppContext context = new WebAppContext();
        context.setContextPath("/cebes");
        context.setResourceBase("src/main/webapp/");
        context.setDescriptor("src/main/webapp/WEB-INF/web.xml");
        context.setParentLoaderPriority(true);
        server.setHandler(context);

        Ajp13SocketConnector ajpConnector = new Ajp13SocketConnector();
        int ajpport = 8009;
        if( args.length == 2){
            ajpport = Integer.parseInt(args[1]);
        }
        ajpConnector.setPort(ajpport);
        server.addConnector(ajpConnector);

        /*
        SslContextFactory sslContextFactory = new SslContextFactory("src/test/java/de/o2/portal/ui/runner/jetty_keystore_password_cinderella");
        sslContextFactory.setKeyStorePassword("cinderella");
        sslContextFactory.setKeyManagerPassword("cinderella");
        SslSocketConnector sslConnector = new SslSocketConnector(sslContextFactory);
        int sslPort = 8443;
        if( args.length == 3){
            sslPort = Integer.parseInt(args[2]);
        }
        sslConnector.setPort(sslPort);
        server.addConnector(sslConnector);
        */
        try {
            System.out.println(">>> STARTING O2 PORTAL, PRESS ANY KEY TO STOP");
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(100);
        }
    }

}
