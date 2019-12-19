package com.techieonthenet.entity.common;

import lombok.Getter;

/**
 * The enum State.
 */
@Getter
public enum State {

    /**
     * The Ap.
     */
    AP("Andhra Pradesh"),
    /**
     * The Ar.
     */
    AR("Arunachal Pradesh"),
    /**
     * As state.
     */
    AS("Assam"),
    /**
     * Br state.
     */
    BR("Bihar"),
    /**
     * Ct state.
     */
    CT("Chhattisgarh"),
    /**
     * Ga state.
     */
    GA("Goa"),
    /**
     * Gj state.
     */
    GJ("Gujarat"),
    /**
     * Hr state.
     */
    HR("Haryana"),
    /**
     * The Hp.
     */
    HP("Himachal Pradesh"),
    /**
     * The Jk.
     */
    JK("Jammu and Kashmir"),
    /**
     * Jh state.
     */
    JH("Jharkhand"),
    /**
     * Ka state.
     */
    KA("Karnataka"),
    /**
     * Kl state.
     */
    KL("Kerala"),
    /**
     * The Mp.
     */
    MP("Madhya Pradesh"),
    /**
     * Mh state.
     */
    MH("Maharashtra"),
    /**
     * Mn state.
     */
    MN("Manipur"),
    /**
     * Ml state.
     */
    ML("Meghalaya"),
    /**
     * Mz state.
     */
    MZ("Mizoram"),
    /**
     * Nl state.
     */
    NL("Nagaland"),
    /**
     * Or state.
     */
    OR("Odisha"),
    /**
     * Pb state.
     */
    PB("Punjab"),
    /**
     * Rj state.
     */
    RJ("Rajasthan"),
    /**
     * Sk state.
     */
    SK("Sikkim"),
    /**
     * The Tn.
     */
    TN("Tamil Nadu"),
    /**
     * Tg state.
     */
    TG("Telangana"),
    /**
     * Tr state.
     */
    TR("Tripura"),
    /**
     * Ut state.
     */
    UT("Uttarakhand"),
    /**
     * The Up.
     */
    UP("Uttar Pradesh"),
    /**
     * The Wb.
     */
    WB("West Bengal"),
    /**
     * The An.
     */
    AN("Andaman and Nicobar Islands"),
    /**
     * Ch state.
     */
    CH("Chandigarh"),
    /**
     * The Dn.
     */
    DN("Dadra and Nagar Haveli"),
    /**
     * The Dd.
     */
    DD("Daman and Diu"),
    /**
     * Dl state.
     */
    DL("Delhi"),
    /**
     * Ld state.
     */
    LD("Lakshadweep"),
    /**
     * Py state.
     */
    PY("Puducherry");

    /**
     * The Name.
     */
    String name;

    State(String name) {
        this.name = name;
    }

}
