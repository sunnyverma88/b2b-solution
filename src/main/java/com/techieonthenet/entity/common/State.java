package com.techieonthenet.entity.common;

import lombok.Getter;

@Getter
public enum State {

    AP("Andhra Pradesh"),
    AR("Arunachal Pradesh"),
    AS("Assam"),
    BR("Bihar"),
    CT("Chhattisgarh"),
    GA("Goa"),
    GJ("Gujarat"),
    HR("Haryana"),
    HP("Himachal Pradesh"),
    JK("Jammu and Kashmir"),
    JH("Jharkhand"),
    KA("Karnataka"),
    KL("Kerala"),
    MP("Madhya Pradesh"),
    MH("Maharashtra"),
    MN("Manipur"),
    ML("Meghalaya"),
    MZ("Mizoram"),
    NL("Nagaland"),
    OR("Odisha"),
    PB("Punjab"),
    RJ("Rajasthan"),
    SK("Sikkim"),
    TN("Tamil Nadu"),
    TG("Telangana"),
    TR("Tripura"),
    UT("Uttarakhand"),
    UP("Uttar Pradesh"),
    WB("West Bengal"),
    AN("Andaman and Nicobar Islands"),
    CH("Chandigarh"),
    DN("Dadra and Nagar Haveli"),
    DD("Daman and Diu"),
    DL("Delhi"),
    LD("Lakshadweep"),
    PY("Puducherry");

    String name;

    State(String name) {
        this.name = name;
    }

}
