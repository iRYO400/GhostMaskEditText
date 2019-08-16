package workshop.akbolatss.demo.ghostmask.ui.login.custom;

import workshop.akbolatss.library.ghostmaskedittext.api.SimpleNumberMask;

public enum CustomPhoneMasks implements SimpleNumberMask {
    US("+1 (", "+1 (___) ___-____");

    private String countryCodeWithMisc;
    private String phoneMask;

    CustomPhoneMasks(String countryCodeWithMisc, String phoneMask) {
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
