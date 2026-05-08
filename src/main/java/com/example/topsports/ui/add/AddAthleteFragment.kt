package com.example.topsports.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.topsports.TopSportsApp
import com.example.topsports.data.local.entity.Athlete
import com.example.topsports.data.repository.SportsRepository
import com.example.topsports.databinding.FragmentAddAthleteBinding
import com.example.topsports.ui.profile.ProfileViewModel
import com.example.topsports.utils.SessionManager

class AddAthleteFragment : Fragment() {

    private var _binding: FragmentAddAthleteBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProfileViewModel
    private lateinit var sessionManager: SessionManager
    private var currentAthlete: Athlete? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddAthleteBinding.inflate(inflater, container, false)
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
                currentAthlete = it
                binding.etFullName.setText(it.fullName)
                binding.etAge.setText(it.age.toString())
                binding.etCity.setText(it.city)
                binding.etSport.setText(it.sport)
                binding.etYears.setText(it.yearsExperience.toString())
                binding.etBio.setText(it.bio)
            }
        }
    }

    private fun setupClickListeners() {
        binding.btnSave.setOnClickListener {
            val fullName = binding.etFullName.text.toString().trim()
            val age = binding.etAge.text.toString().trim()
            val city = binding.etCity.text.toString().trim()
            val sport = binding.etSport.text.toString().trim()
            val years = binding.etYears.text.toString().trim()
            val bio = binding.etBio.text.toString().trim()

            if (fullName.isEmpty() || age.isEmpty() || city.isEmpty() || sport.isEmpty() || years.isEmpty()) {
                Toast.makeText(requireContext(), "Completați toate câmpurile obligatorii!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            currentAthlete?.let { existing ->
                val updated = existing.copy(
                    fullName = fullName,
                    age = age.toInt(),
                    city = city,
                    sport = sport,
                    yearsExperience = years.toInt(),
                    bio = bio
                )
                viewModel.updateAthlete(updated)
                sessionManager.saveFavoriteSport(sport)
                Toast.makeText(requireContext(), "Profil actualizat!", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }

        binding.btnCancel.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
