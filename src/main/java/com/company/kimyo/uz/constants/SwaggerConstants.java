package com.company.kimyo.uz.constants;

import org.springframework.http.HttpStatusCode;

public class SwaggerConstants {



    public static final String EXAMPLE_PRODUCT_SUCCESS = """
            {
                 "success":"true",
                 "code":"0",
                 "message":"OK",
                 "data": {
                    "prodName":"Value...",
                    "prodType":"Value...",
                    "prodColor":"Value...",
                    "prodPrice":"Value...",
                    "prodAmount":"Value..."
                 }
            }
            """;


    public static final String EXAMPLE_PRODUCT_BAD_REQUEST = """
            {
                 "success":"false",
                 "code":"-1",
                 "message":"Products are not found"
            }
            """;

    public static final String EXAMPLE_PRODUCT_NOT_FOUND = """
            {
                 "success":"false",
                 "code":"-1",
                 "message":"Products are not found"
            }
            """;

    public static final String EXAMPLE_PRODUCT_INTERNAL_SERVER_ERROR = """
            {
                "success":"false",
                 "code":"-1",
                 "message":"Products are not found"
            }
            """;

    public static final String EXAMPLE_CARD_SUCCESS = """
            {
                "success":"true",
                "code":"0",
                "message":"OK",
                "data":{
                    "cardName":"Value...",
                    "cardFullName":"Value...",
                    "cardCode":"Value..."
                }
            }
            """;

    public static final String EXAMPLE_CARDS_BAD_REQUEST = """
            {
                 "success":"false",
                 "code":"-1",
                 "message":"Cards are not found"
            }
            """;

    public static final String EXAMPLE_CARDS_NOT_FOUND = """
            {
                 "success":"false",
                 "code":"-1",
                 "message":"Cards are not found"
            }
            """;

    public static final String EXAMPLE_CARDS_INTERNAL_SERVER_ERROR = """
            {
                 "success":"false",
                 "code":"-1",
                 "message":"Cards are not found"
            }
            """;


    public static final String EXAMPLE_CATEGORY_SUCCESS = """
            {
                 "success":"true",
                 "code":"0",
                 "message":"OK",
                 "data":{
                    "categoryName":"Value..."
                 }
            }
            """;

    public static final String EXAMPLE_CATEGORY_BAD_REQUEST = """
            {
                 "success":"false",
                 "code":"-1",
                 "message":"Categories are not found"
            }
            """;

    public static final String EXAMPLE_CATEGORY_NOT_FOUND = """
            {
                 "success":"false",
                 "code":"-1",
                 "message":"Categories are not found"
            }
            """;

    public static final String EXAMPLE_CATEGORY_INTERNAL_SERVER_ERROR = """
            {
                 "success":"false",
                 "code":"-1",
                 "message":"Categories are not found"
            }
            """;

    public static final String EXAMPLE_USERS_SUCCESS = """
            {
                 "success":"true",
                 "code":"0",
                 "message":"OK",
                 "data": {
                    "firstname":"Value...",
                    "lastname":"Value...",
                    "email":"Value...",
                    "password":"Value...",
                    "age":"Value..."
                 }
            }
            """;
    public static final String EXAMPLE_USERS_BAD_REQUEST = """
            {
                 "success":"false",
                 "code":"-1",
                 "message":"Users are not found"
            }
            """;

    public static final String EXAMPLE_USERS_NOT_FOUND = """
            {
                 "success":"false",
                 "code":"-1",
                 "message":"Users are not found"
            }
            """;
    public static final String EXAMPLE_USERS_INTERNAL_SERVER_ERROR = """
            {
                 "success":"false",
                 "code":"-1",
                 "message":"Users are not found"
            }
            """;

    public static final String EXAMPLE_ORDERS_SUCCESS = """
            {
                 "success":"true",
                 "code":"0",
                 "message":"OK",
                 "data": {
                    "orderDate":"Value...",
                    "totalPrice":"Value..."
                 }
            }
            """;

    public static final String EXAMPLE_ORDERS_BAD_REQUEST = """
            {
                 "success":"false",
                 "code":"-1",
                 "message":"Orders are not found"
            }
            """;

    public static final String EXAMPLE_ORDERS_NOT_FOUND = """
            {
                 "success":"false",
                 "code":"-1",
                 "message":"Orders are not found"
            }
            """;

    public static final String EXAMPLE_ORDERS_INTERNAL_SERVER_ERROR = """
            {
                 "success":"false",
                 "code":"-1",
                 "message":"Orders are not found"
            }
            """;

}
