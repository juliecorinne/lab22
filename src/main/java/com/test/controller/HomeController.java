package com.test.controller;

import com.test.coffee.coffeeOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;

@Controller
public class HomeController {

    @RequestMapping("/")

    public ModelAndView helloWorld() {
        return new
                ModelAndView("welcome", "message", "Hello World");

    }

    @RequestMapping("welcome")

    public ModelAndView helloWorld2() {
        return new
                ModelAndView("welcome2", "message", "Hello World2");

    }

    @RequestMapping("coffeeRegistration")
    public ModelAndView coffeeRegMethod(@RequestParam("FName") String fName,
                                        @RequestParam("LName") String lName,
                                        @RequestParam("eMail") String email,
                                        @RequestParam("pN") String phoneN,
                                        @RequestParam("pWord") String passW) throws ClassNotFoundException, SQLException {

        String url = "jdbc:mysql://localhost:3306/CoffeeHouse";
        String userName = "chirpus";
        String password = "grant";

        Class.forName("com.mysql.jdbc.Driver");

        Connection con = DriverManager.getConnection(url, userName, password);

        PreparedStatement pst = con.prepareStatement("insert into userInformation(firstName, lastName, email, phoneNumber, password)" + "values(?,?,?,?,?)");

        pst.setString(1, fName);
        pst.setString(2, lName);
        pst.setString(3, email);
        pst.setString(4, phoneN);
        pst.setString(5, passW);

        pst.executeUpdate();

        con.close();

        String name = "Hello " + fName;
        return new ModelAndView("viewName", "helloMessage", name);

    }

    @RequestMapping("getInfo")
    public ModelAndView getUserInfo() {
        String welcomeMessage = "Please provide your information!";

        return new ModelAndView("coffeeRegForm", "display", welcomeMessage);
    }

    @RequestMapping("order")
    public ModelAndView getUserOrder() {
        String display = "What would you like to order!";

        return new ModelAndView("coffeeOrderDisplay", "orderQ", display);
    }

    @RequestMapping("orderInfo")
    public ModelAndView displayOptions() throws ClassNotFoundException, SQLException {

        String selectCoffee = "select name, description, price, quantity from items";
        String url = "jdbc:mysql://localhost:3306/CoffeeHouse";
        String userName = "chirpus";
        String password = "grant";

        Class.forName("com.mysql.jdbc.Driver");

        Connection con = DriverManager.getConnection(url, userName, password);

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(selectCoffee);

        ArrayList<coffeeOrder> displayCoffee = new ArrayList<coffeeOrder>();

        while (rs.next() == true) {
            String name = rs.getString(1);
            String description = rs.getString(2);
            String price = rs.getString(3);
            int quantity = rs.getInt(4);
            coffeeOrder temp = new coffeeOrder(name, description, price, quantity);

            displayCoffee.add(temp);

        }

        return new ModelAndView("coffeeOrderDisplay", "coffeeList", displayCoffee);
    }

}
