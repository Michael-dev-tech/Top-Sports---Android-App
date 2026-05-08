package com.example.topsports.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.topsports.TopSportsApp
import com.example.topsports.adapter.SportAdapter
import com.example.topsports.data.remote.model.LeagueItem
import com.example.topsports.data.remote.model.SportItem
import com.example.topsports.data.repository.SportsRepository
import com.example.topsports.databinding.FragmentHomeBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel
    private lateinit var sportAdapter: SportAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = (requireActivity().application as TopSportsApp).database
        val repository = SportsRepository(db.athleteDao())
        val factory = HomeViewModel.Factory(repository)
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        setupRecyclerView()
        observeViewModel()

        binding.btnRetry.setOnClickListener {
            viewModel.loadSports()
        }
    }

    private fun setupRecyclerView() {
        sportAdapter = SportAdapter { sport ->
            onSportClicked(sport)
        }
        binding.recyclerSports.adapter = sportAdapter
    }

    private fun observeViewModel() {
        viewModel.sports.observe(viewLifecycleOwner) { sports ->
            sportAdapter.submitList(sports)
            binding.tvEmptyState.visibility = if (sports.isEmpty()) View.VISIBLE else View.GONE
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.btnRetry.visibility = View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                binding.btnRetry.visibility = View.VISIBLE
            }
        }

        viewModel.leagues.observe(viewLifecycleOwner) { leagues ->
            if (leagues.isNotEmpty()) {
                showLeaguesBottomSheet(leagues)
            }
        }
    }

    private fun onSportClicked(sport: SportItem) {
        viewModel.loadLeaguesForSport(sport.strSport)
    }

    private fun showLeaguesBottomSheet(leagues: List<LeagueItem>) {
        val dialog = BottomSheetDialog(requireContext())
        val content = StringBuilder()
        content.append("🏆 Ligi disponibile:\n\n")
        leagues.take(10).forEach { league ->
            content.append("• ${league.strLeague}")
            league.strCountry?.let { content.append(" ($it)") }
            content.append("\n")
        }
        // Folosim un layout simplu cu TextView
        val textView = android.widget.TextView(requireContext()).apply {
            text = content.toString()
            textSize = 14f
            setPadding(48, 48, 48, 48)
        }
        val scrollView = android.widget.ScrollView(requireContext()).apply {
            addView(textView)
        }
        dialog.setContentView(scrollView)
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
