package piotrholda.filerouter;

import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileTransferRoutesBuilder implements RoutesBuilder {

    @Value("${source.uri}")
    private String sourceUri;

    @Value("${destination.uri}")
    private String destinationUri;

    @Override
    public void addRoutesToCamelContext(CamelContext context) throws Exception {
        context.addRoutes(new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from(sourceUri)
                        .to(destinationUri);
            }

        });
    }
}
