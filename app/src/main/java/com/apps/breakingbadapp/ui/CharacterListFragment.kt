package com.apps.fullandroidcourseclassa.breakingbadapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.apps.fullandroidcourseclassa.R
import com.apps.fullandroidcourseclassa.breakingbadapi.BreakingBadApplication
import com.apps.fullandroidcourseclassa.breakingbadapi.ui.util.CharacterListAdapter

class CharacterListFragment : Fragment() {

    private val characterListViewModel: CharacterListViewModel by viewModels {
        CharacterListViewModelFactory((requireActivity().application as BreakingBadApplication).charachterRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val recyclerView = requireActivity().findViewById<RecyclerView>(R.id.rvCharacter)
        val adapter = CharacterListAdapter { character ->
            //TODO
        }
        recyclerView.adapter = adapter
        characterListViewModel.characters.observe(viewLifecycleOwner,{
            characters ->
            adapter.submitList(characters)
        })

        val refreshLayout = requireActivity().findViewById<SwipeRefreshLayout>(R.id.refresh_layout)
        refreshLayout.setOnRefreshListener {
            characterListViewModel.refreshDataFromRepository()
            Toast.makeText(requireContext(),"Data Actual",Toast.LENGTH_SHORT).show()
            refreshLayout.isRefreshing = false

        }


    }
}