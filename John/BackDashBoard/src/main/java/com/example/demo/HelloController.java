package com.example.demo;


import com.example.demo.Google.Google;
import com.example.demo.Google.Youtube;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
public class HelloController {

//    @RequestMapping("/hello")
//    public String index() {
//        String te=null;
//        try {
//
//            JdbcLogin test=new JdbcLogin();
//
//            Statement stmt= test.run().createStatement();
//            ResultSet rs= stmt.executeQuery("SELECT * FROM user");
//            while (rs.next()) {
//                System.out.print("ID : ");
//                System.out.print(rs.getString(1));
//                System.out.print(", Nom : ");
//                System.out.print(rs.getString(2));
//                System.out.print(", prenom : ");
//                System.out.print(rs.getString(3));
//                System.out.print(", Mail : ");
//                System.out.print(rs.getString(4));
//                System.out.println(" ");
//            }
//            rs.close();
//            test.close();
//
//            te="Greetings from Spring Boot!";
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//
//        }
//        return te;
//    }

    @GetMapping("/google/accessToken")
    public void helloWorld(@RequestParam("code") String code) throws IOException, JSONException {
        new Google().recupToken(code);

        //return new Youtube().Widget(new Google().recupToken(code),"UCWeg2Pkate69NFdBeuRFTAw");
    }

    @GetMapping("/google/youtube")
    public void widget(@RequestParam("code") String code,@RequestParam("id_youtube") String idYoutube) throws IOException, JSONException {
        Youtube n=new Youtube();
        n.recupToken(code);
         n.Widget("UCWeg2Pkate69NFdBeuRFTAw");
      //  return w;
    }


    @GetMapping("/restricted")
    public String restricted() {

        return "Connecté !!!!!";
    }



    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        System.out.println(principal.getAttributes());

      //  System.out.println((principal.getAttribute("name").toString()));
        //return Collections.singletonMap("name", principal.getAttributes());
        return null;
    }
}