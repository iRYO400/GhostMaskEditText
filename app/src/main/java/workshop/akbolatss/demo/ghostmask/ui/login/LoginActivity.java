package workshop.akbolatss.demo.ghostmask.ui.login;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import workshop.akbolatss.demo.ghostmask.R;
import workshop.akbolatss.demo.ghostmask.databinding.ActivityLoginBinding;
import workshop.akbolatss.demo.ghostmask.ui.login.custom.CustomPhoneMasks;
import workshop.akbolatss.library.ghostmaskedittext.api.CommonNumberMask;
import workshop.akbolatss.library.ghostmaskedittext.mask.CommonNumberMaskEnum;
import workshop.akbolatss.library.ghostmaskedittext.mask.SimpleNumberMaskEnum;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    private LoginViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        viewModel = ViewModelProviders.of(this)
                .get(LoginViewModel.class);

        observeViewModel();
        setListeners();

    }

    private void observeViewModel() {
        viewModel.country.observe(this, new Observer<CommonNumberMask>() {
            @Override
            public void onChanged(CommonNumberMask maskCountry) {
                binding.phone.setMaskEnum(maskCountry);
                binding.phone.requestFocus();
            }
        });
    }

    private void setListeners() {
        binding.countryKz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.country.setValue(CommonNumberMaskEnum.KZ);
            }
        });
        binding.countryKg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.country.setValue(SimpleNumberMaskEnum.KG);
            }
        });
        binding.countryUsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.country.setValue(CustomPhoneMasks.US);
            }
        });
    }

}
