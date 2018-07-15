Customer’s Scheduling API Documentation
=======================================

Table of Contents

-   [Client](#_client)
    -   [Insert client](#_insert_client)
    -   [Get client](#_get_client)
    -   [Get client’s books](#_get_client_s_books)
    -   [Get client’s stores](#_get_client_s_stores)
    -   [Delete client](#_delete_client)
-   [Owner](#_owner)
    -   [Insert owner](#_insert_owner)
    -   [Get owner](#_get_owner)
    -   [Delete owner](#_delete_owner)

[Client](#_client) {#_client}
------------------

### [Insert client](#_insert_client) {#_insert_client}

Used to create a client.

request

``` {.highlightjs .highlight .nowrap}
POST /person/owner HTTP/1.1
Content-Type: application/json;charset=UTF-8
Authorization: Bearer unittest
Host: localhost:8080
Content-Length: 44

{"nif":"123456789","email":"tstclientemail"}
```

Request fields

  Path      Type       Description
  --------- ---------- ------------------------
  `email`   `String`   The email of the owner
  `nif`     `String`   The nif of the owner

response

``` {.highlightjs .highlight .nowrap}
HTTP/1.1 201 Created
Content-Type: application/hal+json;charset=UTF-8
Content-Length: 378

{"person":{"nif":"123456789","client":{"email":"tstclientemail","name":"tstclientname","contact":null,"gender":0}},"_links":{"get":{"href":"http://localhost:8080/person/owner/tstclientemail"},"insert":{"href":"http://localhost:8080/person/owner"},"stores":{"href":"http://localhost:8080/person/owner/tstclientemail/stores"},"self":{"href":"http://localhost:8080/person/owner"}}}
```

Response fields

  Path                      Type       Description
  ------------------------- ---------- ---------------------------------------------------
  `person`                  `Object`   Owner info
  `person.client.email`     `String`   Owner’s email
  `person.nif`              `String`   Owner’s nif
  `person.client.name`      `String`   Owner’s name
  `person.client.contact`   `Null`     Owner’s contact
  `person.client.gender`    `Number`   Owner’s gender
  `_links`                  `Object`   Hypermedia navigation and some actions hyperlinks
  `_links.get.href`         `String`   Hyperlink to get owner info
  `_links.insert.href`      `String`   Hyperlink to create owner
  `_links.stores.href`      `String`   Hyperlink to get owner’s stores
  `_links.self.href`        `String`   Hyperlink to get same resource

### [Get client](#_get_client) {#_get_client}

Used to get a client.

request

``` {.highlightjs .highlight .nowrap}
GET /person/client/tstclient HTTP/1.1
Authorization: Bearer unittest
Accept: application/hal+json;charset=UTF-8
Host: localhost:8080
```

response

``` {.highlightjs .highlight .nowrap}
HTTP/1.1 200 OK
Content-Type: application/hal+json;charset=UTF-8
ETag: "02ed3faf6ff85a4e370924cc8ec36d757"
Content-Length: 836

{"person":{"email":"tstclient","name":"tstclientname","contact":"tstclientemail","gender":1},"accepted":null,"_links":{"get":{"href":"http://localhost:8080/person/client/tstclient"},"insert":{"href":"http://localhost:8080/person/client"},"pendent_requests":{"href":"http://localhost:8080/person/client/tstclient/pendentrequests"},"stores":{"href":"http://localhost:8080/person/client/tstclient/stores"},"set_store":{"href":"http://localhost:8080/store/{nif}/client/tstclient","templated":true},"score":{"href":"http://localhost:8080/store/{nif}/client/tstclient/score","templated":true},"accept":{"href":"http://localhost:8080/store/{nif}/client/tstclient","templated":true},"decline_client":{"href":"http://localhost:8080/store/{nif}/client/tstclient","templated":true},"self":{"href":"http://localhost:8080/person/client/tstclient"}}}
```

Response fields

  Path         Type       Description
  ------------ ---------- ---------------------------------------------------------------------------------------------------------
  `person`     `Object`   Person resources, described on insert client example
  `accepted`   `Null`     Boolean value with indicates if the person is accepted on store, when the store’s clients are requested
  `_links`     `Object`   Links to other resources, described on insert client example

-   Get client’s pendent requests

Used to get a collection of stores whose haven’t accepted the client
yet.

request

``` {.highlightjs .highlight .nowrap}
GET /person/client/tstclient/pendentrequests HTTP/1.1
Authorization: Bearer unittest
Accept: application/hal+json;charset=UTF-8
Host: localhost:8080
```

response

``` {.highlightjs .highlight .nowrap}
HTTP/1.1 200 OK
Content-Type: application/hal+json;charset=UTF-8
ETag: "015af7dd8aaaa3e73933f768428ec345a"
Content-Length: 129

{"_embedded":{"storeResourceList":[]},"_links":{"self":{"href":"http://localhost:8080/person/client/tstclient/pendentrequests"}}}
```

Response fields

  Path                            Type       Description
  ------------------------------- ---------- ------------------------------------------------------------
  `_embedded.storeResourceList`   `Array`    Collection of stores whose haven’t accepted the client yet
  `_links`                        `Object`   Links to other resources

### [Get client’s books](#_get_client_s_books) {#_get_client_s_books}

Used to get a collection of schedulled books of client.

request

``` {.highlightjs .highlight .nowrap}
GET /person/client/tstclient/books HTTP/1.1
Authorization: Bearer unittest
Accept: application/hal+json;charset=UTF-8
Host: localhost:8080
```

response

``` {.highlightjs .highlight .nowrap}
HTTP/1.1 200 OK
Content-Type: application/hal+json;charset=UTF-8
ETag: "081d52c60cc31bc16aed2e015df1bb813"
Content-Length: 121

{"_embedded":{"bookingResourceList":[]},"_links":{"self":{"href":"http://localhost:8080/person/client/tstclient/books"}}}
```

Response fields

  Path                              Type       Description
  --------------------------------- ---------- ------------------------------------------
  `_embedded.bookingResourceList`   `Array`    Collection of schedulled books of client
  `_links`                          `Object`   Links to self and other resources

### [Get client’s stores](#_get_client_s_stores) {#_get_client_s_stores}

Used to get a collection client’s stores whose have accepted the client
already.

request

``` {.highlightjs .highlight .nowrap}
GET /person/client/tstclient/stores HTTP/1.1
Authorization: Bearer unittest
Accept: application/hal+json;charset=UTF-8
Host: localhost:8080
```

response

``` {.highlightjs .highlight .nowrap}
HTTP/1.1 200 OK
Content-Type: application/hal+json;charset=UTF-8
ETag: "0ab699a519fa329af4f7a7701c5d05c51"
Content-Length: 120

{"_embedded":{"storeResourceList":[]},"_links":{"self":{"href":"http://localhost:8080/person/client/tstclient/stores"}}}
```

Response fields

  Path                            Type       Description
  ------------------------------- ---------- ---------------------------------------------------
  `_embedded.storeResourceList`   `Array`    Collection of client’s stores whose have accepted
  `_links`                        `Object`   Links to self and other resources

### [Delete client](#_delete_client) {#_delete_client}

Used to delete a registered client.

request

``` {.highlightjs .highlight .nowrap}
DELETE /person/client/tstclient HTTP/1.1
Authorization: Bearer unittest
Accept: application/hal+json;charset=UTF-8
Host: localhost:8080
```

response

``` {.highlightjs .highlight .nowrap}
HTTP/1.1 200 OK
Content-Type: application/hal+json;charset=UTF-8
Content-Length: 843

{"person":{"email":"tstclient","name":"tstclientname","contact":"tstclientemail","gender":1},"accepted":null,"_links":{"get":{"href":"http://localhost:8080/person/client/tstclient"},"insert":{"href":"http://localhost:8080/person/client"},"pendent_requests":{"href":"http://localhost:8080/person/client/tstclient/pendentrequests"},"stores":{"href":"http://localhost:8080/person/client/tstclient/stores"},"set_store":{"href":"http://localhost:8080/store/{nif}/client/tstclient","templated":true},"score":{"href":"http://localhost:8080/store/{nif}/client/tstclient/score","templated":true},"accept":{"href":"http://localhost:8080/store/{nif}/client/tstclient","templated":true},"decline_client":{"href":"http://localhost:8080/store/{nif}/client/tstclient","templated":true},"self":{"href":"http://localhost:8080/person/client/tstclient/stores"}}}
```

Response fields

  Path       Type       Description
  ---------- ---------- -----------------------------------
  `person`   `Object`   Person’s object that was deleted
  `_links`   `Object`   Links to self and other resources

[Owner](#_owner) {#_owner}
----------------

### [Insert owner](#_insert_owner) {#_insert_owner}

Used to create a owner.

request

``` {.highlightjs .highlight .nowrap}
POST /person/owner HTTP/1.1
Content-Type: application/json;charset=UTF-8
Authorization: Bearer unittest
Host: localhost:8080
Content-Length: 44

{"nif":"123456789","email":"tstclientemail"}
```

Request fields

  Path      Type       Description
  --------- ---------- ------------------------
  `email`   `String`   The email of the owner
  `nif`     `String`   The nif of the owner

response

``` {.highlightjs .highlight .nowrap}
HTTP/1.1 201 Created
Content-Type: application/hal+json;charset=UTF-8
Content-Length: 378

{"person":{"nif":"123456789","client":{"email":"tstclientemail","name":"tstclientname","contact":null,"gender":0}},"_links":{"get":{"href":"http://localhost:8080/person/owner/tstclientemail"},"insert":{"href":"http://localhost:8080/person/owner"},"stores":{"href":"http://localhost:8080/person/owner/tstclientemail/stores"},"self":{"href":"http://localhost:8080/person/owner"}}}
```

Response fields

  Path                   Type       Description
  ---------------------- ---------- ---------------------------------------------------
  `person.nif`           `String`   Owner’s nif
  `person`               `Object`   Owner info
  `person.client`        `Object`   Correspondent client
  `_links`               `Object`   Hypermedia navigation and some actions hyperlinks
  `_links.get.href`      `String`   Hyperlink to get owner info
  `_links.insert.href`   `String`   Hyperlink to create owner
  `_links.stores.href`   `String`   Hyperlink to get owner’s stores
  `_links.self.href`     `String`   Hyperlink to get same resource

### [Get owner](#_get_owner) {#_get_owner}

Used to get a owner.

request

``` {.highlightjs .highlight .nowrap}
GET /person/owner/tstclientemail HTTP/1.1
Authorization: Bearer unittest
Accept: application/hal+json;charset=UTF-8
Host: localhost:8080
```

response

``` {.highlightjs .highlight .nowrap}
HTTP/1.1 200 OK
Content-Type: application/hal+json;charset=UTF-8
ETag: "006fdf250fe122b8917bc3493b2c6f664"
Content-Length: 393

{"person":{"nif":"123456789","client":{"email":"tstclientemail","name":"tstclientname","contact":null,"gender":0}},"_links":{"get":{"href":"http://localhost:8080/person/owner/tstclientemail"},"insert":{"href":"http://localhost:8080/person/owner"},"stores":{"href":"http://localhost:8080/person/owner/tstclientemail/stores"},"self":{"href":"http://localhost:8080/person/owner/tstclientemail"}}}
```

Response fields

  Path       Type       Description
  ---------- ---------- -----------------------------------------------------------------------
  `person`   `Object`   Person resources, described on insert client example
  `_links`   `Object`   Links to self and other resources, described on insert client example

### [Delete owner](#_delete_owner) {#_delete_owner}

Used to delete a registered owner.

request

``` {.highlightjs .highlight .nowrap}
DELETE /person/owner/tstclientemail HTTP/1.1
Authorization: Bearer unittest
Accept: application/hal+json;charset=UTF-8
Host: localhost:8080
```

response

``` {.highlightjs .highlight .nowrap}
HTTP/1.1 200 OK
Content-Type: application/hal+json;charset=UTF-8
Content-Length: 843

{"person":{"email":"tstclient","name":"tstclientname","contact":"tstclientemail","gender":1},"accepted":null,"_links":{"get":{"href":"http://localhost:8080/person/client/tstclient"},"insert":{"href":"http://localhost:8080/person/client"},"pendent_requests":{"href":"http://localhost:8080/person/client/tstclient/pendentrequests"},"stores":{"href":"http://localhost:8080/person/client/tstclient/stores"},"set_store":{"href":"http://localhost:8080/store/{nif}/client/tstclient","templated":true},"score":{"href":"http://localhost:8080/store/{nif}/client/tstclient/score","templated":true},"accept":{"href":"http://localhost:8080/store/{nif}/client/tstclient","templated":true},"decline_client":{"href":"http://localhost:8080/store/{nif}/client/tstclient","templated":true},"self":{"href":"http://localhost:8080/person/client/tstclient/stores"}}}
```

Response fields

  Path       Type       Description
  ---------- ---------- -----------------------------------
  `person`   `Object`   Person’s object that was deleted
  `_links`   `Object`   Links to self and other resources

Last updated 2018-07-15 20:02:37 +01:00
