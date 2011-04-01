import org.apache.tapestry5.ioc.annotations.Inject;

public class MyPage {

    // START SNIPPET: tapestry-rio-injection
    @Inject
    @ServiceAssociation(name = "My Service", serviceType = MyService.class)
    private MyService myService;
    // END SNIPPET: tapestry-rio-injection

}