// package nus.project.BeerMarket.model;

// import org.springframework.jdbc.support.rowset.SqlRowSet;
// import org.springframework.util.MultiValueMap;

// public class Register {
    
//     private String username;
//     private String password;
//     private String confirmPassword;

//     public String getUsername() {
//         return username;
//     }
//     public void setUsername(String username) {
//         this.username = username;
//     }
//     public String getPassword() {
//         return password;
//     }
//     public void setPassword(String password) {
//         this.password = password;
//     }
//     public String getConfirmPassword() {
//         return confirmPassword;
//     }
//     public void setConfirmPassword(String confirmPassword) {
//         this.confirmPassword = confirmPassword;
//     }

//     public static Register create(MultiValueMap<String, String> body) {
//         Register r = new Register();
//         r.setUsername(body.getFirst("username"));
//         r.setPassword(body.getFirst("password"));
//         r.setConfirmPassword("confirmpassword");

//         return r;
//     }

//     public static Register create(SqlRowSet result) {
//         Register r = new Register();
//         r.setUsername(result.getString("username"));
//         r.setPassword(result.getString("password"));
//         r.setConfirmPassword(result.getString("confirmpassword"));

//         return r;
//     }
// }
