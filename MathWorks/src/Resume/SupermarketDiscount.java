package Resume;

public class SupermarketDiscount {
}

/*
OOD:
    |--User
        |--primary key
        |--username
        |--password
        |--City
        |--Area
        |--Phone
        |--Email
    |--Market
        |--primary key
        |--Name
        |--License
        |--City
        |--Area
        |--Picture
    |--Manager
        |--primary key
        |--Name
        |--Password
        |--Phone
        |--Email
        |--Market: belong to which market
    |--Item
        |--primary key
        |--Name
        |--Original Price
        |--Current Price
        |--Discount
        |--Product
        |--Market
        |--Market Address
    |--Area
        |--primary key
        |--Area Name

Front-end: JSP + css + Javascript
    I. JSP:
        1. <form action="GoodsAdd" method="POST">


Back-end: mySQL + MyEclipse
    I. Beans:
        1. MVC model: Model-Domains, View-HTML/CSS, Controller-Services
        2. DBUtil.getConnection() --> Dao(SQL) --> Domain(Entities) --> service --> servlet(accept requests)

Front-end Back-end communication:
    I. Front-end to Back-end:
        |--doGet()
        |--doPost()
    II. Back-end to Front-end:
        |--request.getSession().setAttribute("managerName", na);

 */
