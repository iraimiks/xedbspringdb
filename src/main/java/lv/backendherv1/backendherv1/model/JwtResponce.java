package lv.backendherv1.backendherv1.model;


import java.io.Serializable;

public class JwtResponce implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public JwtResponce(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return jwttoken;
    }
}
