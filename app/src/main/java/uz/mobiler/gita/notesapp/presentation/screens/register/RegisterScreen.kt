package uz.mobiler.gita.notesapp.presentation.screens.register

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
import uz.mobiler.gita.notesapp.databinding.ScreenRegisterBinding

class RegisterScreen : Fragment(R.layout.screen_register) {

    private val binding by viewBinding(ScreenRegisterBinding::bind)
    private val viewModel by viewModels<RegisterViewModel> { RegisterViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.showPassword.setOnClickListener {
            if (binding.showPassword.isChecked) {
                binding.edPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.edConfirm.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                binding.edPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.edConfirm.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            binding.edPassword.setSelection(binding.edPassword.text.length)
            binding.edConfirm.setSelection(binding.edConfirm.text.length)
        }

        binding.signUpBtn.setOnClickListener {
            viewModel.register(
                binding.edEmail.text.toString().trim(),
                binding.edPassword.text.toString().trim(),
                binding.edConfirm.text.toString().trim()
            )
        }
        binding.tvSignIn.setOnClickListener {
            findNavController().popBackStack()
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
        binding.edConfirm.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.edConfirm.setBackgroundResource(R.drawable.ed_bcg_selected)
            } else {
                binding.edConfirm.setBackgroundResource(R.drawable.ed_bcg)
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
                    findNavController().navigate(R.id.action_registerScreen_to_mainScreen)
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
            confirmLiveData.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    binding.edConfirm.setBackgroundResource(R.drawable.ed_bcg)
                    binding.confirmErrorTv.visibility = View.INVISIBLE
                } else {
                    binding.edConfirm.setBackgroundResource(R.drawable.ed_bcg_wrong)
                    binding.confirmErrorTv.visibility = View.VISIBLE
                    binding.confirmErrorTv.text = it
                }
            }
        }

    }
}