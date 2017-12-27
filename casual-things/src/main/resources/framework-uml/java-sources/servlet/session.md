## session

### session tracking

1. cookies

The container sends a cookie to the client. The client will then 
return the cookie on each subsequent request to the server, associating the 
request with a session, the name of the session tracking cookie must be JSESSIONID.

2. SSL sessions

3. URL Rewriting

When a client will not accept a cookie, URL rewriting may be used by the server
as the basis for session tracking.

4. session integrity

### new session
* the client does not yet know about the session
* the client chooses not to join a session

### session attributes

* session timeout
* session last accessed times
* session attributes concurrent access

