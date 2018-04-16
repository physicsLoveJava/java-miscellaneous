
## The WebSocket Protocol

### Abstract
1. Enable two-way communication between client and remote host
2. Security model is the origin-base security model
3. opening handshake followed by basic message framing, layered over TCP
4. browser-based applications that need two-way communication with servers that does not rely on opening multiple HTTP connections
(using XMLHttpRequest or <iframe>s and long polling)

### Introduction
#### Background(reason)
historically, creating web application that need bidirectional communication between client and server,
always required an abuse of HTTP to poll the server for updates.

problems:
1. server has to use different tcp connections, one for sending, new one for incoming message
2. http protocol has a high overhead, for the each http header
3. client side is forced to maintain a mapping from the outgoing connections to the incoming connection to track replies

#### Protocol Overview
two parts: a handshake and the data transfer

example protocol:
```c 
//client
GET /chat HTTP/1.1
Host: server.example.com
Upgrade: websocket
Connection: Upgrade
Sec-WebSocket-Key: dGhlIHNhbXBsZSBub25jZQ==
Origin: http://example.com
Sec-WebSocket-Protocol: chat, superchat
Sec-WebSocket-Version: 13

//server
HTTP/1.1 101 Switching Protocols
Upgrade: websocket
Connection: Upgrade
Sec-WebSocket-Accept: s3pPLMBiTxaQ9kYGzzhZRbK+xOo=
Sec-WebSocket-Protocol: chat
```

1. client and server both sent their handshakes
2. handshakes was successful, data transfer part starts
3. client and server transfer data back and forth
4. a message is composed of one or more frames

frame-type:
1. textual data
2. binary data
3. control frames (protocol-level signaling)

#### Opening Handshake
opening handshake is intended to be compatible with http-based server-side and intermediaries
the WebSocket client's handshake is an http upgrade request:
```c 
GET /chat HTTP/1.1
Host: server.example.com
Upgrade: websocket
Connection: Upgrade
Sec-WebSocket-Key: dGhlIHNhbXBsZSBub25jZQ==
Origin: http://example.com
Sec-WebSocket-Protocol: chat, superchat
Sec-WebSocket-Version: 13
```
Sec-WebSocket-Protocol --> subprotocols are acceptable to the client

server has to proved to the client that it received the client's WebSocket handshake
1. Sec-WebSocket-Key
2. GUID
3. SHA-1 hash, base64-encoded return Sec-WebSocket-Accept

```c 
Sec-WebSocket-Key: dGhlIHNhbXBsZSBub25jZQ==
GUID: 258EAFA5-E914-47DA-95CA-C5AB0DC85B11
Combine: dGhlIHNhbXBsZSBub25jZQ==258EAFA5-E914-47DA-95CAC5AB0DC85B11
SHA-1: 0xb3 0x7a 0x4f 0x2c 0xc0 0x62 0x4f 0x16 0x90 0xf6
       0x46 0x06 0xcf 0x38 0x59 0x45 0xb2 0xbe 0xc4 0xea
base64-encoded: s3pPLMBiTxaQ9kYGzzhZRbK+xOo=
```

Status line: HTTP/1.1 101 Switching Protocols
Connection and Upgrade head fields complete the HTTP Upgrade:
```c 
HTTP/1.1 101 Switching Protocols
Upgrade: websocket
Connection: Upgrade
Sec-WebSocket-Accept: s3pPLMBiTxaQ9kYGzzhZRbK+xOo=
```

Sec-WebSocket-Accept && Status Code

can also use _set_cookies

#### Closing Handshake
Either peer can send a control frame with data containing a specified 
control sequence to begin the closing handshake.
Upon receiving such a frame, the other peer sends a Close frame in response
, if it hasn't already sent one.
After sending a control frame indicating the connection should be closed, a peer does not send any further data
after receiving a control frame indicating the connection should be closed, a peer discards any further data received

#### Design Philosophy
The WebSocket Protocol is designed on the principle that there should the minimal framing(the only framing that exists 
is to make the protocol frame-based instead of stream-based and to support a distinction between Unicode text and Binary frames).
It is expected that metadata would be layered on top of WebSocket by the application layer.

Conceptually, WebSocket is really just a layer on top of TCP that does the following:
1. adds a web origin-based security model for browsers
2. adds an addressing and protocol naming mechanism to support multiple services on one port and 
multiple host names on one IP address
3. layers a framing mechanism on top of TCP to get back to the IP packet mechanism that TCP is built on,
but without length limits
4. includes an additional closing handshake in-band that is designed to work in the presence of proxies
and other intermediaries

make the thing protocol, meanwhile using the http existing power such as proxy

#### Security Model
origin based browsers, attacker can't change the Sec-X headers

#### Relationship to TCP and HTTP
The WebSocket Protocol is an independent TCP-based protocol. Its only relationship to http is that
its handshake is interpreted by http servers as an upgrade request.
By default, the WebSocket Protocol uses port 80 for regular connections and port 443 for TLS.

#### Establishing a Connection
one single servers with all traffic
multiple servers to manage all traffic

#### SubProtocols Using the WebSocket Protocol
The client can request that the server use a specific subprotocol by including the Sec-WebSocket-Protocol field in 
its handshake. If specified, the server needs to include the same field and one of the selected subprotocol values in
its response.


### Conformance Requirements

#### WebSocket URIs



