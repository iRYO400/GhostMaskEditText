package workshop.akbolatss.library.ghostmaskedittext.mask;

import workshop.akbolatss.library.ghostmaskedittext.R;
import workshop.akbolatss.library.ghostmaskedittext.api.CommonNumberMask;

public enum CommonNumberMaskEnum implements CommonNumberMask {

    KZ(R.string.country_kz, R.drawable.ic_kazakhstan, "KZ", "7", "+7 (", "+7 (___) ___-__-__", 11),
    KG(R.string.country_kg, R.drawable.ic_kyrgyzstan, "KG", "996", "+996 (", "+996 (___) __-__-__", 12);

    private int nameResId;
    private int iconResId;
    private String countryIso;
    private String countryCode;
    private String countryCodeWithMisc;
    private String phoneMask;
    private int digitsCount;

    CommonNumberMaskEnum(int nameResId, int iconResId, String countryIso, String countryCode, String countryCodeWithMisc, String phoneMask, int digitsCount) {
        this.nameResId = nameResId;
        this.iconResId = iconResId;
        this.countryIso = countryIso;
        this.countryCode = countryCode;
        this.countryCodeWithMisc = countryCodeWithMisc;
        this.phoneMask = phoneMask;
        this.digitsCount = digitsCount;
    }

    @Override
    public int getNameResId() {
        return nameResId;
    }

    @Override
    public int getIconResId() {
        return iconResId;
    }

    @Override
    public String getCountryIso() {
        return countryIso;
    }

    @Override
    public String getCountryCode() {
        return countryCode;
    }

    @Override
    public String getCountryCodeWithMisc() {
        return countryCodeWithMisc;
    }

    @Override
    public String getPhoneMask() {
        return phoneMask;
    }

    @Override
    public int getDigitsCount() {
        return digitsCount;
    }
}
