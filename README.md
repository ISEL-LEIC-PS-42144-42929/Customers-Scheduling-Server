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
                "id": "1",
                "title": "Hard drive exchange",
                "price": "30",
                "employees": [
                    {
                        "name": "Josué Maria"
                    },
                    ... rest of the employees
                ],
                "_links": {
                    "book": {
                         "href": "/business/123456789/store/XXX/services/1/book"
                    },
                    "day_markings": {
                         "href": "/business/123456789/store/XXX/services/1/markings"
                    }
                }
            },
            ... rest of the services
        ]
        "_links": {
            "self": {
                "href": "/business/123456789/store/XXX"
            },
            "delete": {
                "href": "/business/123456789/store/XXX/delete"
            }
        }
    }
    ```
- #### **get free hours for one service in a day**
     #### HTTP Request
    
    `POST /business/{nif}/store/{name}/services/{id}/markings
    {
          "date": "01/12/2018"
    }`
// TODO

- #### **registry a booking**
     #### HTTP Request
    
    `POST /business/{nif}/store/{name}/services/{id}/book
    {
          "employee": "---",
          "date": "---",
          "client_email": "---"
    }`
// TODO

- #### **get all stores of a business**
     #### HTTP Request
    
    `GET /business/all/store
    {
          "location": "----",
          "category": "----"
    }`
// TODO

- #### **create a business**
     #### HTTP Request
    
    `POST /business
    {
          "name": "----",
          "nif": "----"
    }`
// TODO


- #### **add store to a business**
     #### HTTP Request
    
    `POST /business/{nif}/store
    {
          "name": "----",
          "country": "----",
          "city": "----",
          "street": "----",
          "zip_code": "----",
          "lot": "----",
          "zip_code": "----",
          "category": "----",
          "contact": "----",
          "owner": {
          },
          "portfolio": [
          ]
    }`
// TODO

- #### **add staff to a store**
     #### HTTP Request
    
    `POST /business/{nif}/store/{name}/staff
    {
          "name": "----",
          "email": "----",
          "contact": "----",
          "gender": "----"
    }`
// TODO

- #### **get businesses of user**
     #### HTTP Request
    
    `GET /business/{email}`
// TODO
