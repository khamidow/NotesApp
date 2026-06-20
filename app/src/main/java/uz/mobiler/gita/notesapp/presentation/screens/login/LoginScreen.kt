package uz.mobiler.gita.notesapp.presentation.screens.login

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import dev.androidbroadcast.vbpd.viewBinding
import uz.mobiler.gita.notesapp.R
import uz.mobiler.gita.notesapp.databinding.ScreenLoginBinding

class LoginScreen : Fragment(R.layout.screen_login) {

    private val binding by viewBinding(ScreenLoginBinding::bind)
    private val viewModel by viewModels<LoginViewModel> { LoginViewModelFactory() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.googleBtn.setOnClickListener {
            viewModel.loginWithGoogle(requireContext())
        }

        binding.showPassword.setOnClickListener {
            if (binding.showPassword.isChecked) {
                binding.edPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                binding.edPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            binding.edPassword.setSelection(binding.edPassword.text.length)
        }

        binding.signInBtn.setOnClickListener {
            viewModel.login(
                binding.edEmail.text.toString().trim(),
                binding.edPassword.text.toString().trim()
            )
        }

        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginScreen_to_registerScreen)
        }

        binding.edEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.edEmail.setBackgroundResource(R.drawable.ed_bcg_selected)
            } else {
                binding.edEmail.setBackgroundResource(R.drawable.ed_bcg)
            }
        }
        binding.edPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.edPassword.setBackgroundResource(R.drawable.ed_bcg_selected)
            } else {
                binding.edPassword.setBackgroundResource(R.drawable.ed_bcg)
            }
        }

        viewModel.apply {
            loadingLiveData.observe(viewLifecycleOwner) {
                if (it) binding.loadingPb.visibility = View.VISIBLE
                else binding.loadingPb.visibility = View.GONE
            }

            messageLiveData.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }

            successLiveData.observe(viewLifecycleOwner) {
                if (it) {
                    requireContext().getSharedPreferences("MyData", Context.MODE_PRIVATE).edit()
                        .putString(
                            "userId",
                            FirebaseAuth.getInstance().currentUser?.uid.toString()
                        ).commit()
                    findNavController().navigate(R.id.action_loginScreen_to_mainScreen)
                }
            }

            emailLiveData.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    binding.edEmail.setBackgroundResource(R.drawable.ed_bcg)
                    binding.emailErrorTv.visibility = View.INVISIBLE
                } else {
                    binding.edEmail.setBackgroundResource(R.drawable.ed_bcg_wrong)
                    binding.emailErrorTv.visibility = View.VISIBLE
                    binding.emailErrorTv.text = it
                }
            }
            passwordLiveData.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    binding.edPassword.setBackgroundResource(R.drawable.ed_bcg)
                    binding.passwordErrorTv.visibility = View.INVISIBLE
                } else {
                    binding.edPassword.setBackgroundResource(R.drawable.ed_bcg_wrong)
                    binding.passwordErrorTv.visibility = View.VISIBLE
                    binding.passwordErrorTv.text = it
                }
            }
        }
    }
}