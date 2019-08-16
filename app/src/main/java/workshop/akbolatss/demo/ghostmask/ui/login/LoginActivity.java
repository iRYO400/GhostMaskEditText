package workshop.akbolatss.demo.ghostmask.ui.login;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import workshop.akbolatss.demo.ghostmask.R;
import workshop.akbolatss.demo.ghostmask.databinding.ActivityLoginBinding;
import workshop.akbolatss.library.ghostmaskedittext.MaskCountry;
import workshop.akbolatss.library.ghostmaskedittext.MaskCountryEnum;

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
        viewModel.country.observe(this, new Observer<MaskCountry>() {
            @Override
            public void onChanged(MaskCountry maskCountry) {
                binding.phone.setMaskEnum(maskCountry);
                binding.phone.requestFocus();
            }
        });
    }

    private void setListeners() {
        binding.countryKz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.country.setValue(MaskCountryEnum.KZ);
            }
        });
        binding.countryKg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.country.setValue(MaskCountryEnum.KG);
            }
        });
    }

}
