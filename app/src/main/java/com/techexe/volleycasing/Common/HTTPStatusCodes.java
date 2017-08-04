package com.techexe.volleycasing.Common;

/**
 * Created by Jaimin patel on 31/7/17.
 */

public class HTTPStatusCodes {


    /*
    200 OK - Response to a successful GET, PUT, PATCH or DELETE. Can also be used for a POST that doesn't result in a creation.
    201 Created - Response to a POST that results in a creation. Should be combined with a Location header pointing to the location of the new resource
    204 No Content - Response to a successful request that won't be returning a body (like a DELETE request)
    304 Not Modified - Used when HTTP caching headers are in play
    400 Bad Request - The request is malformed, such as if the body does not parse
    401 Unauthorized - When no or invalid authentication details are provided. Also useful to trigger an auth popup if the API is used from a browser
    403 Forbidden - When authentication succeeded but authenticated user doesn't have access to the resource
    404 Not Found - When a non-existent resource is requested
    405 Method Not Allowed - When an HTTP method is being requested that isn't allowed for the authenticated user
    410 Gone - Indicates that the resource at this end point is no longer available. Useful as a blanket response for old API versions
    415 Unsupported Media Type - If incorrect content type was provided as part of the request
    422 Unprocessable Entity - Used for validation errors
    429 Too Many Requests - When a request is rejected due to rate limiting
     */

    public static final int CODE_200 = 200;
    public static final int CODE_201 = 201;
    public static final int CODE_204 = 204;
    public static final int CODE_304 = 304;
    public static final int CODE_400 = 400;
    public static final int CODE_401 = 401;
    public static final int CODE_403 = 403;
    public static final int CODE_404 = 404;
    public static final int CODE_405 = 405;
    public static final int CODE_410 = 410;
    public static final int CODE_415 = 415;
    public static final int CODE_422 = 422;
    public static final int CODE_429 = 429;
    public static final int CODE_503 = 503;

}
