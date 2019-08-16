package workshop.akbolatss.library.ghostmaskedittext.api;


public interface SimpleNumberMask extends CommonNumberMask {

    @Override
    default int getNameResId() {
        return 0;
    }

    @Override
    default int getIconResId() {
        return 0;
    }

    @Override
    default String getCountryIso() {
        return null;
    }

    @Override
    default String getCountryCode() {
        return null;
    }

    @Override
    default int getDigitsCount() {
        return 0;
    }
}
