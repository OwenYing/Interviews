package Resume;

public class CandyFactory {
}

/*
OOD:
    |--User
        |--primary key
        |--user type: Admin, Teacher, Individual
        |--username
        |--password
        |--email
        |--security question number & answer
    |--User Performance Data
        |--primary key
        |--performance JSON
        |--user primary key: foreign key
    |--Course
        |--primary key
        |--school name
            |--school can be a object
        |--course name
        |--grade level
        |--teacher primary key: foreign key
    |--Student
        |--primary key
        |--username: number
        |--password: 6 digit random number
        |--profile image path
        |--course primary key
    |--Student Performance Data
        |--primary key
        |--performance JSON
        |--student primary key: foreign key

Front-end: html + css + JQuery
    I. Jquery:
        1. Event based
        2. Five levels: Has lots of duplicate codes, but since each of them are slightly different, hard to encapsulate them
           Especially the events, different interaction with different css style, the problem is "if you use separate code for
           each level, there are lots of duplicated codes, but if you encapsulate them, the parameters are too many and there are
           too many if-statement"
        3. Code granularity Trade-off:
            |--If each level in single javascript:
                |--Cons: the javascript file is very long;  hard to be reused.
                |--Pros: variable passing efficiently; reduce backend workload; reduce front-back communication;
                         reduce waiting time, increase game speed
            |--If each scene in single javascript
                |--Cons: frequent interaction with backend; long waiting time; high backend workload; inefficient variable passing
                |--Pros: easy to reuse each scene; short javascript file; easy to maintain
        4. Use JQery to add CSS and html code, violates encapsulation and make it hard to maintenance. But easy to dynamically
           modify the styles and html elements.

    II. CSS:
        1. Design like tree structure, general class as root, specific class as leaves
        2. GameFrame -- relative, Others: absolute, only can be played on 1024 supportive device.

    III. html:
        1. Use templates to reuse the code

Back-end: Beans + mySQL + JSON
    I. Beans:
        1. MVC model: Model-EntityBeans, View-HTML/CSS, Controller-ManagedBeans
        2. EntityBeans --> FacadeBeans --> Controllers --> Managers;    Constants, Global Tools, Validators
    II. mySQL + JSON:
        1. Use mySQL to maintain standard database table formula
        2. Use JSON stored in mySQL to add flexibility to the backend storage
    III. Persistence API: Connect Beans and Databases

Front-end Back-end communication:
    I. Front-end to Back-end:
        |--JSF page to Beans: Expression language
        |--Javascript to Beans: hidden input form + JSON
    II. Back-end to Front-end:
        |--Beans to JSF: Expression language
        |--Beans to javascript: action listener

 */