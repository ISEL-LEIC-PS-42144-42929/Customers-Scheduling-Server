# **Documentation for Customers-Scheduling HTTP API**

### Person
- #### **get client by email**
     #### HTTP Request
    
    `GET person/{email}/client`
    
    #### Response 
    The requested Client Resource

    #### Example: get client by email
    
     **Request**
    ```
    GET /person/example@gmail.com/client HTTP/1.1
    Content-Type: application/json
    ```
     **Response**
    The successful response returns 200 OK status code and a payload similar to the following:
    
    ```
    {
        "email": "example@gmail.com",
        "name": "example",
        "contact": "959479308",
        "gender": "1",
        "_links": {
            "self": {
                "href": "/person/example@gmail.com/client"
            },
            "delete": {
                "href": "/person/example@gmail.com/client"
            },
            "register": {
                "href": "/person/client"
            }
        },
        "_embedded": {
            "client_business": 
                {
                    "href": "/business"
                }
        },
    }
    ```
    
    
    ### Business
    - #### **get business by NIF**
     #### HTTP Request
    
    `GET /business/{nif}/stores`
    
    #### Response 
    All stores of the business with the gaven NIF.

    #### Example: get business by NIF
    
     **Request**
    ```
    GET /business/123456789/stores HTTP/1.1
    Content-Type: application/json
    ```
     **Response**
    The successful response returns 200 OK status code and a payload similar to the following:
    
    ```
    {
        "nif": 123456789,
        "stores": [ 
            {
                "name": "XXX",
                "score": "3.4",
                "city": "Lisbon",
                "street": "Rua Dom Augusto",
                "_links": {
                    "self": {
                        "href": "/business/123456789/store/XXX"
                    } 
                }
            },
            ...
        ],
        "_links": {
            "self": {
                "href": "/business/123456789/stores"
            }
        }
    }
    ```
    
    - #### **get store by name and business NIF**
     #### HTTP Request
    
    `GET /business/{nif}/store/{name}`
    
    #### Response 
    The store with the gaven name and business NIF. The embedded field contains the services this store offers.

    #### Example: get store by name and business NIF
    
     **Request**
    ```
    GET /business/123456789/store/XXX HTTP/1.1
    Content-Type: application/json
    ```
     **Response**
    The successful response returns 200 OK status code and a payload similar to the following:
    
    ```
    {
        "name": "XXX",
        "score": "3.4",
        "country": "Portugal",
        "city": "Lisbon",
        "street": "Rua Dom Augusto",
        "lot": "149",
        "zip_code": "2690-124",
        "category": "tech",
        "business_nif": "123456789",
        "contact": "+351969478302",
        "_embedded": [
            {
                "title": "Hard drive exchange",
                "price": "30",
                "employees": [
                    {
                        "name": "Josué Maria"
                    },
                    ...
                ],
                "_links": {
                }
            }
        ]
        "_links": {
            "self": {
                "href": "/business/123456789/store/XXX"
            }
        }
    }
    ```
    
