import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class WrongDateFormatException extends Exception implements
        ExceptionMapper<WrongDateFormatException>
{

    public WrongDateFormatException()
    {
        super("The format of the date should be yyyy-mm-dd");
    }

    public WrongDateFormatException(String string) {
        super(string);
    }

    @Override
    public Response toResponse(WrongDateFormatException exception) {
        return Response.status(400).entity(exception.getMessage())
                .type("text/plain").build();
    }
}

class ServerException extends Exception implements
        ExceptionMapper<ServerException>
{
    public ServerException()
    {
        super("A server error occurred, please try again");
    }

    public ServerException(String string)
    {
        super(string);
    }

    @Override
    public Response toResponse(ServerException exception) {
        return Response.status(500).entity(exception.getMessage()).type("text/plain").build();
    }
}
