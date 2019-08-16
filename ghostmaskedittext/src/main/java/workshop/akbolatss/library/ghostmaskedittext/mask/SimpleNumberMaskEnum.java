package workshop.akbolatss.library.ghostmaskedittext.mask;

import workshop.akbolatss.library.ghostmaskedittext.api.SimpleNumberMask;

public enum SimpleNumberMaskEnum implements SimpleNumberMask {

    KZ("+7 (", "+7 (___) ___-__-__"),
    KG("+996 (", "+996 (___) __-__-__");

    private String countryCodeWithMisc;
    private String phoneMask;

    SimpleNumberMaskEnum(String countryCodeWithMisc, String phoneMask) {
        this.countryCodeWithMisc = countryCodeWithMisc;
        this.phoneMask = phoneMask;
    }

    @Override
    public String getCountryCodeWithMisc() {
        return countryCodeWithMisc;
    }

    @Override
    public String getPhoneMask() {
        return phoneMask;
    }

}
