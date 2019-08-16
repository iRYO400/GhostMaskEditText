package workshop.akbolatss.demo.ghostmask.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import workshop.akbolatss.demo.ghostmask.ui.login.custom.CustomPhoneMasks;
import workshop.akbolatss.library.ghostmaskedittext.api.CommonNumberMask;

public class LoginViewModel extends ViewModel {

    MutableLiveData<CommonNumberMask> country = new MutableLiveData<>();

    public LoginViewModel() {
        country.setValue(CustomPhoneMasks.US);
    }
}
