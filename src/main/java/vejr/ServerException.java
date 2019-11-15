package vejr;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ServerException extends Exception implements
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