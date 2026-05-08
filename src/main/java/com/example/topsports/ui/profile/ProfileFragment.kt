package com.example.topsports.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.topsports.R
import com.example.topsports.TopSportsApp
import com.example.topsports.data.repository.SportsRepository
import com.example.topsports.databinding.FragmentProfileBinding
import com.example.topsports.ui.main.MainActivity
import com.example.topsports.utils.SessionManager

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProfileViewModel
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sessionManager = SessionManager(requireContext())

        val db = (requireActivity().application as TopSportsApp).database
        val repository = SportsRepository(db.athleteDao())
        val factory = ProfileViewModel.Factory(repository)
        viewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]

        val username = sessionManager.getUsername()
        viewModel.loadAthleteByUsername(username)

        observeViewModel()
        setupClickListeners()
    }

    private fun observeViewModel() {
        viewModel.athlete.observe(viewLifecycleOwner) { athlete ->
            athlete?.let {
                binding.tvName.text = it.fullName
                binding.tvUsername.text = "@${it.username}"
                binding.tvSport.text = it.sport
                binding.tvCity.text = it.city
                binding.tvAge.text = "${it.age} ani"
                binding.tvExperience.text = "${it.yearsExperience} ani experiență"
                binding.tvBio.text = if (it.bio.isNotEmpty()) it.bio else "Fără bio adăugat."
            }
        }
    }

    private fun setupClickListeners() {
        binding.btnEditProfile.setOnClickListener {
            findNavController().navigate(R.id.action_profile_to_addAthlete)
        }

        binding.btnLogout.setOnClickListener {
            (requireActivity() as MainActivity).logout()
        }
    }

    override fun onResume() {
        super.onResume()
        val username = sessionManager.getUsername()
        viewModel.loadAthleteByUsername(username)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
